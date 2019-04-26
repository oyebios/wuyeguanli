package test.cn.sjz.testproject.testsystem.view;

import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseFragment;

/**
 * Created by lwd on 2019/4/25.
 */

public class MeFragment extends BaseFragment {
    private MapView mv_map;
    private AMap aMap;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        if (aMap == null) {
            aMap = mv_map.getMap();
        }
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        mv_map=(MapView)v.findViewById(R.id.mv_map) ;
        mv_map.onCreate(savedInstanceState);
    }

    @Override
    protected void initListener() {

    }
}
