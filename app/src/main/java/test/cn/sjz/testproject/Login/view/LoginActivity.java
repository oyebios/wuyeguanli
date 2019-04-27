package test.cn.sjz.testproject.Login.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import test.cn.sjz.testproject.Login.contract.IContractLogin;
import test.cn.sjz.testproject.Login.presenter.IPresenterLogin;
import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.Utils.PreferUtil;
import test.cn.sjz.testproject.base.ARouterPath;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.model.Parcelable.TestData;
import test.cn.sjz.testproject.model.Parcelable.UserInfo;
import test.cn.sjz.testproject.model.Serializable.LocalUserInfo;

/**
 * Created by lwd on 2019/3/14.
 */
@Route(path = ARouterPath.ACTIVITY_LOGIN)
public class LoginActivity extends BaseActivity implements View.OnClickListener,IContractLogin.IViewLogin{

    private TestData testData = new TestData();
    private IContractLogin.IPresnter iPresnter;
    private EditText et_username, et_pw;
    private Button btn_login,btn_reset;
    int index = 0;

    private String username,pw;
    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        et_username = (EditText) findViewById(R.id.et_username);
        et_pw = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_reset = (Button) findViewById(R.id.btn_reset);


    }

    @Override
    public void initDate() {
        iPresnter = new IPresenterLogin(this);

    }

    @Override
    public void iniListener() {
        btn_login.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                index += 1;
                username = et_username.getText().toString().trim();
                pw = et_pw.getText().toString().trim();
                if (username.equals("")||pw.equals("")){
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    LocalUserInfo userInfo = new LocalUserInfo();
                    userInfo.setPassWord(pw);
                    userInfo.setUserName(username);
                    iPresnter.LoginIn(userInfo);
                }
                break;
            case R.id.btn_reset:
                PreferUtil.getInstance().putInt("pwErrorTimes",0);
                PreferUtil.getInstance().putString("userName","");
                btn_reset.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void showLoginSuccess(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        ARouter.getInstance().build(ARouterPath.ACTIVITY_FICTION).navigation();
    }

    @Override
    public void showLoginFailed(boolean isShowReset) {
        Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
        if (isShowReset){
            btn_reset.setVisibility(View.VISIBLE);
        }else btn_reset.setVisibility(View.GONE);

    }

    @Override
    public void setPresenter(IContractLogin.IPresnter iPresnter) {
        this.iPresnter = iPresnter;
    }
}
