package com.example.smartcity.Json;

import java.util.List;

public class Record {

    /**
     * total : 2
     * rows : [{"id":15,"title":"话费充值","phonenumber":"13800000000","rechargeAmount":20,"paymentAmount":20,"paymentType":"电子支付","rechargeTime":"2021-05-10 13:51:52","userId":2}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 15
         * title : 话费充值
         * phonenumber : 13800000000
         * rechargeAmount : 20
         * paymentAmount : 20
         * paymentType : 电子支付
         * rechargeTime : 2021-05-10 13:51:52
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
