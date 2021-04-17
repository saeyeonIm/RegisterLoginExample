package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_id, et_pass, et_name, et_age;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티 시작 시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //아이디 값 찾아주기
        et_id =findViewById(R.id.et_id);
        et_pass =findViewById(R.id.et_pass);
        et_name =findViewById(R.id.et_name);
        et_age =findViewById(R.id.et_age);

        //회원가입 버튼 클릭 시 수행
        // 배터리가 다되셨나용?ㅋㅋㅋㅋ 죄송해요!ㅜㅜㅜ 잠시마 아니면 컴퓨터로 보톡으로할까요 ?
        //네네!!
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 현재 입력되어 있는 값을 (get)가져온다
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());

                //제이슨 오브젝트를 활용하여 실제적인 회원가입 요청하기
                //서버에 전송할 때 일반 String 불가능하므로 제이슨 오브젝트 형태로 바꿔줌/운반책
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);//response로 서버 통신 성공 여부 받기
                            boolean success = jsonObject.getBoolean("success");
                            if (success){ //회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                                finish(); // activity 파괴

//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); //(출발Activity, 이동할 Activity)
//                                startActivity(intent);
                            } else{ //회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 실패하셨습니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //생성자 직접 사용
                //서버로 Volley를 이용해서 요청을 함
                RegisterRequest registerRequest = new RegisterRequest(userID, userPass, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

        //회원가입 버튼을 눌렀을 때 각각의 데이터를 서버(Register Request)로 전송




    }
}