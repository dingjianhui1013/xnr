/**
 * 2014年1月24日 上午9:43:22
 */
package com.xnradmin.client.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.codehaus.commons.compiler.CompileException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.uuid.UUIDGener;
import com.xnradmin.core.service.CommonPortService;
import com.xnradmin.core.service.ScriptService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.EncodeDecodeUtil;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.dto.ScriptDTO;
import com.xnradmin.dto.client.SyncDTOAck;
import com.xnradmin.po.CommonPort;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.client.script.ScriptVO;

/**
 * @author: bin_liu
 */
@Service("InterfaceService")
public class InterfaceService{

    private Log exLog = Log4jUtil.getLog("ex");

    private Log log = Log4jUtil.getLog("client");

    @Autowired
    private CommonPortService portService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ScriptService scriptService;

    public void commonInterface(HttpServletRequest request,
            HttpServletResponse response) throws IOException,JSONException{
        JSONObject out = new JSONObject();
        SyncDTOAck res = null;
        try{
            String jsonSource = toJson(request);
            log.debug("in: "+jsonSource);
            JSONObject in = new JSONObject(jsonSource);

            String action = in.get("action").toString();
            List<CommonPort> l = portService.findByName(action);
            if(l == null || l.size() <= 0){
                Status status = statusService.find(SyncDTOAck.class,"status",
                        "未找到接口");
                out.put("status",status.getId().toString());
                out.put("statusDesc",status.getStatusName());
                response.getWriter().print(
                        EncodeDecodeUtil.encode(out.toString()));
            }

            res = getAck(in);

        }catch(CompileException e){
            Status status = statusService.find(SyncDTOAck.class,"status",
                    "脚本编译异常");
            out.put("status",status.getId().toString());
            out.put("statusDesc",status.getStatusName());
            response.getWriter().print(EncodeDecodeUtil.encode(out.toString()));
        }catch(ClassNotFoundException e){
            Status status = statusService.find(SyncDTOAck.class,"status",
                    "调用类未找到");
            out.put("status",status.getId().toString());
            out.put("statusDesc",status.getStatusName());
            response.getWriter().print(EncodeDecodeUtil.encode(out.toString()));
        }

        catch(IOException e){
            Status status = statusService.find(SyncDTOAck.class,"status","成功");
            out.put("status",status.getId().toString());
            out.put("statusDesc",status.getStatusName());
            response.getWriter().print(EncodeDecodeUtil.encode(out.toString()));
        }catch(JSONException e){
            Status status = statusService.find(SyncDTOAck.class,"status",
                    "传入JSON格式有误");
            out.put("status",status.getId().toString());
            out.put("statusDesc",status.getStatusName());
            response.getWriter().print(EncodeDecodeUtil.encode(out.toString()));
        }
        // out.put("action","userReg");
        //
        // // 用户信息
        // JSONArray clientUserInfo = new JSONArray();
        // // 用户设备号
        // JSONObject userUdid = new JSONObject();
        // userUdid.put("userUdid","11111111");
        // clientUserInfo.put(userUdid);
        // // 用户手机号
        // JSONObject userMobile = new JSONObject();
        // userMobile.put("userMobile","13999999999");
        // clientUserInfo.put(userMobile);
        //
        // out.put("clientUserInfo",clientUserInfo);
        // out.put("linkid",UUIDGener.getUUID());
        // out.put("valCode","1234");
        log.debug("out: "+res);
        response.getWriter().print(EncodeDecodeUtil.encode(res.getJsonOut()));
    }

    private String toJson(HttpServletRequest request) throws IOException{
        // String source = request.getInputStream().toString();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                request.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String currentLine = "";
        while( ( currentLine = br.readLine() ) != null){
            sb.append(currentLine);
        }
        String source = sb.toString();
        return EncodeDecodeUtil.decode(source);
    }

    private SyncDTOAck getAck(JSONObject json) throws JSONException,
            CompileException,ClassNotFoundException,IOException{
        SyncDTOAck d = new SyncDTOAck();
        JSONObject out = new JSONObject();
        String action = json.get("action").toString();

        CommonPort port = portService.findByNameOne(action);
        ScriptDTO sdto = SpringBase.getScriptDTO();

        ScriptVO v = scriptService.findByClassName(port.getScriptClassName());
        Object o = scriptService.executeMethod(sdto.getScriptMethods()[0],
                v.getScriptdto(),json);
        if(o == null)
            return null;
        return (SyncDTOAck) o;
    }
}
