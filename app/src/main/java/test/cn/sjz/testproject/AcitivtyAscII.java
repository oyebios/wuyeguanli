package test.cn.sjz.testproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.File;
import java.io.IOException;

import static test.cn.sjz.testproject.base.ARouterPath.ACTIVITY_ASCII;

/**
 * Created by lwd on 2019/4/17.
 */

@Route(path = ACTIVITY_ASCII)
public class AcitivtyAscII extends Activity{
    static String ascii = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\\\"^`'.";
    static String base = "@#&$%*o!;.";//小帅丶使用这个字符
    //main方法调用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher,options);
        load(bitmap);
    }


    /**
     * 图片转字符
     * @param bi BufferedImage图片
     * @throws IOException
     */
    public static void load(Bitmap bi) {
        try {
            int width = bi.getWidth();
            int height = bi.getHeight();
            System.out.println(width);
            System.out.println(height);
            String result = "";
            for (int i = 0; i < height; i += 2) {
                for (int j = 0; j < width; j++) {
                    int pixel = bi.getPixel(j, i); // 下面三行代码将一个数字转换为RGB数字
                    int red = (pixel & 0xff0000) >> 16;
                    int green = (pixel & 0xff00) >> 8;
                    int blue = (pixel & 0xff);
                    float gray = 0.299f * red + 0.578f * green + 0.114f * blue;
                    int index = Math.round(gray * (ascii.length() + 1) / 255);
                    result += index >= ascii.length() ? " " : String.valueOf(ascii.charAt(index));
                }
                result += "\r\n";
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("图片转字符异常"+e.getMessage());
        }
    }
}
