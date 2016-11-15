/**
 *2015年1月31日 下午2:10:09
 */
package com.xnradmin.vo.common.quesion;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.quesion.QuesionLog;

/**
 * @author: liubin
 *
 */
public class QuesionLogVO implements Serializable {

	private QuesionLog quesionLog;

	private CommonStaff createStaff;

	private CommonStaff lastModifStaff;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public QuesionLog getQuesionLog() {
		return quesionLog;
	}

	public CommonStaff getCreateStaff() {
		return createStaff;
	}

	public CommonStaff getLastModifStaff() {
		return lastModifStaff;
	}

	public void setQuesionLog(QuesionLog quesionLog) {
		this.quesionLog = quesionLog;
	}

	public void setCreateStaff(CommonStaff createStaff) {
		this.createStaff = createStaff;
	}

	public void setLastModifStaff(CommonStaff lastModifStaff) {
		this.lastModifStaff = lastModifStaff;
	}
}
