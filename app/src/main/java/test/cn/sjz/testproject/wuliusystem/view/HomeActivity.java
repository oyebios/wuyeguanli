package test.cn.sjz.testproject.wuliusystem.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.wuliusystem.http.Api;
import test.cn.sjz.testproject.wuliusystem.http.HttpManager;
import test.cn.sjz.testproject.wuliusystem.http.requestbody.HandEntryBody;
import test.cn.sjz.testproject.zbar.CaptureActivity;

public class HomeActivity extends BaseActivity {

    private EditText mEtCode;
    private EditText mEtName1,mEtPhone1,mEtAddr1;
    private EditText mEtName2,mEtPhone2,mEtAddr2;
    private TextView mBtnCommit ,mBtnScan;
    private long exitTime;

    String sname, sphone,saddress ;
    String rname, rphone,raddress ;
    String code;

    private HttpManager httpManager ;
    @Override
    public int getLayoutID() {
        return R.layout.activity_hand_entry;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mEtCode = (EditText)findViewById(R.id.et_code);

        mEtName1 = (EditText)findViewById(R.id.et_sender_name);
        mEtPhone1 = (EditText)findViewById(R.id.et_sender_phone);
        mEtAddr1 = (EditText)findViewById(R.id.et_sender_address);

        mEtName2 = (EditText)findViewById(R.id.et_rec_name);
        mEtPhone2 = (EditText)findViewById(R.id.et_rec_phone);
        mEtAddr2 = (EditText)findViewById(R.id.et_rec_address);

        mBtnCommit = (TextView)findViewById(R.id.btn_commit);
        mBtnScan = (TextView)findViewById(R.id.tv_scan);

    }

    @Override
    public void initDate() {
        httpManager = new HttpManager(this,handler);
    }

    @Override
    public void iniListener() {
        mBtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
            }
        });
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = mEtCode.getText().toString().trim();

                sname = mEtName1.getText().toString().trim();
                sphone = mEtPhone1.getText().toString().trim();
                saddress = mEtAddr1.getText().toString().trim();

                rname = mEtName2.getText().toString().trim();
                rphone = mEtPhone2.getText().toString().trim();
                raddress = mEtAddr2.getText().toString().trim();

                if (code.equals("") ||sname.equals("") ||sphone.equals("") ||saddress.equals("") ||rname.equals("") ||rphone.equals("") ||raddress.equals("")){
                    Toast.makeText(HomeActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this ,AddTrackActivity.class);
                    intent.putExtra("code",code);
                    startActivity(intent);
                }else {
                    HandEntryBody body = new HandEntryBody();
                    body.setCode(code);
                    body.setSender(sname);
                    body.setSenderMobile(sphone);
                    body.setSenderAddress(saddress);
                    body.setRecipient(rname);
                    body.setRecipientMobile(rphone);
                    body.setRecipientAddress(raddress);
                    httpManager.handentry(body);
                }
            }
        });
    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(HomeActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 222);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222){
            // 扫描二维码回传
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    //获取扫描结果
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString(CaptureActivity.EXTRA_STRING);
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Api.FAILED:
                case Api.AUTO_ENTRY_F:
                case Api.HAND_ENTRY_F:
                    String failed=(String )msg.obj;
                    Toast.makeText(HomeActivity.this,failed , Toast.LENGTH_SHORT).show();
                    break;
                case Api.HAND_ENTRY_S:
                case Api.AUTO_ENTRY_S:
                    Intent intent = new Intent(HomeActivity.this ,AddTrackActivity.class);
                    intent.putExtra("code",code);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };



    @Override
    protected void onResume() {
        super.onResume();
    }

    /** 监听手机按键 */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if ((keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else if ((System.currentTimeMillis() - exitTime) > 0) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        }
    }
    /**
     *
     * @param permissions
     * @since 2.5.0
     *
     */
    private void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class,
                            int.class});

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23){
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer)checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean)shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
            switch (requestCode) {
                case 1:
                    if (paramArrayOfInt.length > 0 && paramArrayOfInt[0] == PackageManager.PERMISSION_GRANTED) {
                        goScan();
                    } else {
                        Toast.makeText(this, "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PERMISSON_REQUESTCODE:
                    if (!verifyPermissions(paramArrayOfInt)) {
                        showMissingPermissionDialog();
                        isNeedCheck = false;
                    }
                    break;
                default:
            }

    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限。\\n请点击\"设置\"-\"权限\"-打开所需权限。");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
    protected String[] needPermissions = {
            Manifest.permission.CAMERA
    };
    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;



}
