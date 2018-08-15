package com.mihua.frameproject.socket.socketbroadcast;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/05/29
 *     desc   :
 * </pre>
 */
public class CommandInfo {


    /**
     * code : 1
     * msg : 交易成功
     * data : {"needPayMoney":18}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * needPayMoney : 18
         * hasPayMoney : 20
         * needPayoutMoney : 2
         * hasPayoutMoney : 2
         * unPayoutMoney : 0
         */

        private String needPayMoney;

        public String getNeedPayMoney() {
            return needPayMoney;
        }

        public void setNeedPayMoney(String needPayMoney) {
            this.needPayMoney = needPayMoney;
        }

    }


}
