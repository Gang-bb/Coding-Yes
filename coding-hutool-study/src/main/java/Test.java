import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;

/**
 * @Author LiangYiXiang
 * @Description TODO
 * @Date 2021/11/15
 **/
public class Test {

    public static void main(String[] args) {
        OsInfo osInfo = SystemUtil.getOsInfo();
        System.out.println(osInfo.isWindows());
    }
}