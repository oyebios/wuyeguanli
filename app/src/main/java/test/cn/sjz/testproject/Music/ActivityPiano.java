package test.cn.sjz.testproject.Music;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.IOException;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.ARouterPath;
import test.cn.sjz.testproject.base.baseview.BaseActivity;

/**
 * Created by lwd on 2019/4/23.
 */
@Route(path = ARouterPath.ACTIVITY_PIANO)
public class ActivityPiano extends BaseActivity {
    MediaPlayer mediaPlayer;

    @Override
    public int getLayoutID() {
        return R.layout.avtivity_piano;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public void initDate() {
        mediaPlayer = MediaPlayer.create(this,R.raw.testdata2);
        try {
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void iniListener() {

    }
}
