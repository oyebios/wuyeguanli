package test.cn.sjz.testproject.wuliusystem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.Utils.PreferUtil;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.wuliusystem.http.Api;
import test.cn.sjz.testproject.wuliusystem.http.HttpManager;
import test.cn.sjz.testproject.wuliusystem.http.bean.UserBean;
import test.cn.sjz.testproject.wuliusystem.http.requestbody.LoginBody;

/**
 * Explain  登录
 */

public class LoginActivity extends BaseActivity {
    private EditText mEtUserName,mEtPwd;
    private TextView mBtnLogin;
    private String username,pwd;
    HttpManager httpManager;
    @Override
    public int getLayoutID() {
        return R.layout.activity_login_in;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mEtPwd = (EditText)findViewById(R.id.et_pwd);
        mEtUserName = (EditText)findViewById(R.id.et_username);
        mBtnLogin = (TextView)findViewById(R.id.btn_login);
    }

    @Override
    public void initDate() {
         httpManager = new HttpManager(this,handler);

        username =   PreferUtil.getInstance().getString("userName","");
        pwd = PreferUtil.getInstance().getString("passWord","");
        if (!username.equals("")&&!pwd.equals("")){
            mEtUserName.setText(username);
            mEtPwd.setText(pwd);
            httpManager.login(new LoginBody(username,pwd));
        }
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
        httpManager.login(loginBody);
    }
    @SuppressLint("HandlerLeak")
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
                    UserBean userBean = (UserBean) msg.obj;
                    PreferUtil.getInstance().putString("userName",username);
                    PreferUtil.getInstance().putString("passWord",pwd);
                    PreferUtil.getInstance().putLong("createtime",userBean.getCreateTime());
                    PreferUtil.getInstance().putInt("uid",userBean.getId());
                    Intent intent = new Intent(LoginActivity.this ,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };
}
