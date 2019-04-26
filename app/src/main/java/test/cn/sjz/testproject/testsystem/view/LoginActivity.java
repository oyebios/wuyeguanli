package test.cn.sjz.testproject.testsystem.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.testsystem.http.HttpManager;
import test.cn.sjz.testproject.testsystem.http.Api;
import test.cn.sjz.testproject.testsystem.http.requestbody.LoginBody;

/**
 * Explain  登录
 */

public class LoginActivity extends BaseActivity {
    private EditText mEtUserName,mEtPwd;
    private TextView mBtnLogin;
    private String username,pwd;
    @Override
    public int getLayoutID() {
        return R.layout.activity_login_in;
    }

    @Override
    public void initView() {
        mEtPwd = (EditText)findViewById(R.id.et_pwd);
        mEtUserName = (EditText)findViewById(R.id.et_username);
        mBtnLogin = (TextView)findViewById(R.id.btn_login);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void iniListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test","click");
                username = mEtUserName.getText().toString().trim();
                pwd = mEtPwd.getText().toString().trim();
                if (username == null ||username.equals("")||pwd == null || pwd.equals("")){
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("test","login");
                    LoginBody loginBody = new LoginBody(username,pwd);
                    login(loginBody);
                }
            }
        });
    }

    private void login(LoginBody loginBody) {
        HttpManager httpManager = new HttpManager(this,handler);
        httpManager.login(loginBody);
    }
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Api.FAILED:
                case Api.LOGIN_FAILED:
                    String failed=(String )msg.obj;
                    Toast.makeText(LoginActivity.this,failed , Toast.LENGTH_SHORT).show();
                    break;
                case Api.LOGIN_SUCCESS:
                    Intent intent = new Intent(LoginActivity.this ,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };
}
