package com.edu.lzjtu.shopping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.lzjtu.shopping.R;
import com.edu.lzjtu.shopping.db.ShopDB;
import com.edu.lzjtu.shopping.model.User;

public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editusername);
        password = (EditText) findViewById(R.id.editpassword);
        login = (Button) findViewById(R.id.btnlogin);
        register = (Button) findViewById(R.id.btnregister);

        final User user = new User();
        user.setName(username.getText().toString());
        user.setPassword(password.getText().toString());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopDB shopDB = ShopDB.getInstance(Login.this);
                if(shopDB.findUser(user)){
                   // String
                }else{
                    Toast.makeText(Login.this, "用户名密码错误或不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
