package urlImg;

import cn.hutool.Hutool;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 下载网络图片测试
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class Test {
    public static void main(String[] args) {
        InputStream stream = URLUtil.getStream(URLUtil.url("http://www.ccflow.org/ccbpmLogo.png"));
        System.out.println(stream);
        FileUtil.writeFromStream(stream, "P:\\idea\\RuoYi-Vue-Plus\\1.png");

        byte[] bytes = HttpUtil.downloadBytes("http://www.ccflow.org/ccbpmLogo.png");
        System.out.println(Arrays.toString(bytes));
    }
}
