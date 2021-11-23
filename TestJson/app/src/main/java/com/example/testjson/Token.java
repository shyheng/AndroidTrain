package com.example.testjson;

public class Token {

    /**
     * msg : 操作成功
     * code : 200
     * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjBkMjI3Mjg0LTQ3MjItNDllYS04NGYxLWFjMzk3ZjdmZjg3MyJ9.ubhw_QFpSFGTb_twIpADhlhS1MibXzRMjy9VFWUCeqdBzj0SZSaEquh__guBhGjDQAIiek8i229EwKr4n6UFmA
     */

    private String msg;
    private int code;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
