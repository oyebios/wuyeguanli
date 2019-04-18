package test.cn.sjz.testproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
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
    private FrameLayout fgContent;
    private Fragment fragmentMain ;
    private View rl_menu;
    private RelativeLayout rlLeftMenu;


    @Override
    public int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fgContent = (FrameLayout) findViewById(R.id.content_layout);
        rl_menu = getLayoutInflater().inflate(R.layout.layout_left_menu,null);
        rlLeftMenu = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
        fragmentMain = new FragmentMain();
        FragmentManager fragmentManager  = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_layout,fragmentMain).commit();


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rlLeftMenu.addView(rl_menu,params);

    }

    @Override
    public void initDate() {

    }

    @Override
    public void iniListener() {

    }

}
