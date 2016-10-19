/**
 * 2013年9月29日 下午4:11:16
 */
package com.xnradmin.constant;


/**
 * @autohr: bin_liu
 */
public enum ScriptResultCode {

    SUCCESS(0), SCRIPT_ERROR(-1), DB_ERROR(
            -2), UNKNOW_ERROR(-3), OTHER_ERROR(-4), QUEUE_PREMT_TYPE(
            6), QUEUE_COMMIT_TYPE(7);

    private int type;

    public int getType(){
        return type;
    }
    
    

    public void setType(int type){
        this.type = type;
    }

    private ScriptResultCode(int type){
        this.type = type;
    }

    
}
