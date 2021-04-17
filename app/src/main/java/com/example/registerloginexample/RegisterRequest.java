package com.example.registerloginexample;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    //서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://lsy219.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPass, String userName, int userAge, Response.Listener<String> listener){ //생성자
        super(Method.POST, URL, listener,null); //서버전송 방식은 POST
        map = new HashMap<>();
        map.put("userID",userID); //put(key, 실제문자열값) <string,string> 형태
        map.put("userPassword", userPass);
        map.put("userName", userName);
        map.put("userAge", userAge + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
