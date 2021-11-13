package com.example.smartcity.Json;

import java.util.List;

public class Category {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":1,"liveName":"手机话费","imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021 /05/08/95c51b0c-9674-4784-8011-324d02bd3487.pn"},{"id":2,"liveName":"水费","imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021 /05/08/72163ab8-832a-45e0-8d06-44546294affb.png"},{"id":3,"liveName":"电费","imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021 /05/08/55867f4c-461a-42b2-9822-9c4cbbf563ba.png"},{"id":4,"liveName":"燃气费","imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021 /05/08/7ddace97-4688-4260-8a1d-231070af5bf0.png"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * liveName : 手机话费
         * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021 /05/08/95c51b0c-9674-4784-8011-324d02bd3487.pn
         */

        private int id;
        private String liveName;
        private String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLiveName() {
            return liveName;
        }

        public void setLiveName(String liveName) {
            this.liveName = liveName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
