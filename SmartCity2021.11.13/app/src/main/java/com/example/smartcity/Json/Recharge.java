package com.example.smartcity.Json;

public class Recharge {

    /**
     * msg : 充值成功
     * code : 200
     * data : {"id":2,"title":"话费充值","phonenumber":"13800000000","rechargeAmount":50,"paymentAmount":49,"paymentType":"电子支付","rechargeTime":"2021-05-10 13:43:36","userId":2}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * title : 话费充值
         * phonenumber : 13800000000
         * rechargeAmount : 50
         * paymentAmount : 49
         * paymentType : 电子支付
         * rechargeTime : 2021-05-10 13:43:36
         * userId : 2
         */

        private int id;
        private String title;
        private String phonenumber;
        private int rechargeAmount;
        private int paymentAmount;
        private String paymentType;
        private String rechargeTime;
        private int userId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public int getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(int rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public int getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(int paymentAmount) {
            this.paymentAmount = paymentAmount;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getRechargeTime() {
            return rechargeTime;
        }

        public void setRechargeTime(String rechargeTime) {
            this.rechargeTime = rechargeTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
