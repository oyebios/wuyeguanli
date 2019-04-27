package test.cn.sjz.testproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import test.cn.sjz.testproject.base.baseview.BaseActivity;

/**
 * Created by lwd on 2019/4/18.
 */

public class ActivityMain extends BaseActivity {
    private DrawerLayout drawerLayout;
    private Fragment fragmentMain ;
    private View rl_menu;
    private RelativeLayout rlLeftMenu;

    private LinearLayout llTitle;
    private CheckBox checkBox;


    @Override
    public int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        rl_menu = getLayoutInflater().inflate(R.layout.layout_left_menu,null);
        rlLeftMenu = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
        fragmentMain = new FragmentMain();
        FragmentManager fragmentManager  = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.vp_content,fragmentMain).commit();


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rlLeftMenu.addView(rl_menu,params);


        llTitle = (LinearLayout)findViewById(R.id.ll_title);
        checkBox = (CheckBox)findViewById(R.id.cb_test);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void iniListener() {
        llTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean l  = checkBox.isChecked();
                checkBox.setChecked(!l);
            }
        });
    }

}
