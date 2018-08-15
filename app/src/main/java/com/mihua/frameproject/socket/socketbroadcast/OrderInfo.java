package com.mihua.frameproject.socket.socketbroadcast;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/05/22
 *     desc   :
 * </pre>
 */
public class OrderInfo {


    /**
     * code : 1
     * msg : 交易成功
     * data : {"needPayMoney":18,"hasPayMoney":20,"needPayoutMoney":2,"hasPayoutMoney":2,"unPayoutMoney":0}
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

    @Override
    public String toString() {
        return "OrderInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * needPayMoney : 18
         * hasPayMoney : 20
         * needPayoutMoney : 2
         * hasPayoutMoney : 2
         * unPayoutMoney : 0
         */

        private int needPayMoney;
        private int hasPayMoney;
        private int needPayoutMoney;
        private int hasPayoutMoney;
        private int unPayoutMoney;

        public int getNeedPayMoney() {
            return needPayMoney;
        }

        public void setNeedPayMoney(int needPayMoney) {
            this.needPayMoney = needPayMoney;
        }

        public int getHasPayMoney() {
            return hasPayMoney;
        }

        public void setHasPayMoney(int hasPayMoney) {
            this.hasPayMoney = hasPayMoney;
        }

        public int getNeedPayoutMoney() {
            return needPayoutMoney;
        }

        public void setNeedPayoutMoney(int needPayoutMoney) {
            this.needPayoutMoney = needPayoutMoney;
        }

        public int getHasPayoutMoney() {
            return hasPayoutMoney;
        }

        public void setHasPayoutMoney(int hasPayoutMoney) {
            this.hasPayoutMoney = hasPayoutMoney;
        }

        public int getUnPayoutMoney() {
            return unPayoutMoney;
        }

        public void setUnPayoutMoney(int unPayoutMoney) {
            this.unPayoutMoney = unPayoutMoney;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "needPayMoney=" + needPayMoney +
                    ", hasPayMoney=" + hasPayMoney +
                    ", needPayoutMoney=" + needPayoutMoney +
                    ", hasPayoutMoney=" + hasPayoutMoney +
                    ", unPayoutMoney=" + unPayoutMoney +
                    '}';
        }
    }
}
