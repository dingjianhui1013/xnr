/**
 *2015年1月31日 下午2:08:39
 */
package com.xnradmin.vo.common.quesion;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.quesion.Quesion;

/**
 * @author: liubin
 *
 */
public class QuesionVO implements Serializable {

	private Quesion quesion;

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

	public Quesion getQuesion() {
		return quesion;
	}

	public CommonStaff getCreateStaff() {
		return createStaff;
	}

	public CommonStaff getLastModifStaff() {
		return lastModifStaff;
	}

	public void setQuesion(Quesion quesion) {
		this.quesion = quesion;
	}

	public void setCreateStaff(CommonStaff createStaff) {
		this.createStaff = createStaff;
	}

	public void setLastModifStaff(CommonStaff lastModifStaff) {
		this.lastModifStaff = lastModifStaff;
	}
}
