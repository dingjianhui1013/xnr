/**
 * 2013-8-4 下午2:47:54
 */
package com.xnradmin.core.util;


import org.apache.log4j.Logger;

import com.cntinker.util.StringHelper;

/**
 * @autohr: bin_liu
 */
public class SpeedLimiter{
	private static Logger log = Logger.getLogger(SpeedLimiter.class);
    // 速度控制的检查周期，间隔x秒
    private int cycTime;

    // 每秒执行次数
    private int cycNum;

    // 前一次检测时间
    private long lastTime = 0;

    // 计数器
    private int currNum = 0;

    public SpeedLimiter(){
        this(20);
    }

    public SpeedLimiter(int cnum){
        this(cnum,10);
    }

    public SpeedLimiter(int cnum,int ctime){
        this.cycNum = cnum;
        this.cycTime = ctime;
    }

    /**
     * 限制一段时间内发送次数
     */
    public void checkLimit(){
        if(this.currNum == 0){
            this.lastTime = System.currentTimeMillis();
            this.currNum ++ ;
            if(this.currNum > this.cycNum * this.cycTime){
                try{
                    Thread.sleep(this.lastTime + this.cycTime * 1000
                            - System.currentTimeMillis());
                }catch(InterruptedException ie){
                }finally{
                    this.currNum = 0;
                }
            }else{
                this.currNum ++ ;
            }
        }else{
            if(System.currentTimeMillis() < this.lastTime + this.cycTime * 1000){
                if(this.currNum >= this.cycNum * this.cycTime){
                    try{
                        Thread.sleep(this.lastTime + this.cycTime * 1000
                                - System.currentTimeMillis());
                    }catch(InterruptedException ie){
                    }finally{
                        this.currNum = 0;
                    }
                }else{
                    this.currNum ++ ;
                }
            }else{
                this.currNum = 0;
            }

        }

    }

    public static void main(String[] args){

        // 测试用
        SpeedLimiter sl1 = new SpeedLimiter(3,1);
        int i = 0;
        while(i <= 100){
            log.debug(i ++ + " time:" + StringHelper.getSystime());
            sl1.checkLimit();
        }

    }
}
