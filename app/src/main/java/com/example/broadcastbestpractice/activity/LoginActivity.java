package com.example.broadcastbestpractice.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.broadcastbestpractice.R;
import com.example.broadcastbestpractice.util.BaseActivity;

public class LoginActivity extends BaseActivity {
    private TextView userNameTextView;
    private SharedPreferences prfe;
    private SharedPreferences.Editor editor;
    private TextView pswTextView;
    private Button loginButton;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameTextView=(TextView)findViewById(R.id.user_name);
        checkBox=(CheckBox)findViewById(R.id.remember_pass);
        pswTextView=(TextView)findViewById(R.id.psw);
        loginButton=(Button)findViewById(R.id.login_button);
        prfe= PreferenceManager.getDefaultSharedPreferences(this);
        String userName =prfe.getString("userName","");
        String passWorld=prfe.getString("passWord","");
        userNameTextView.setText(userName);
        pswTextView.setText(passWorld);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=userNameTextView.getText().toString();
                String psw=pswTextView.getText().toString();
                if(userName.equals("admin")&&psw.equals("123")){
                    editor=prfe.edit();
                    if(checkBox.isChecked()){
                        editor.putString("userName",userName);
                        editor.putString("passWord",psw);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,"userName or psw is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
