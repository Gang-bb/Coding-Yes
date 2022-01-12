import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

/**
 * 云之家群机器人对接测试
 *
 * @author Gangbb
 * @date 2022/1/4
 **/
public class Test {
    public static final String ROBOT_URL
            = "https://www.yunzhijia.com/gateway/robot/webhook/send?yzjtype=0&yzjtoken=b51bc31a20684cdb86c42ebc4b548a4a";

    public static void main(String[] args) {
        RobotParamDto param = new RobotParamDto();
        param.setAppName("Java客户端测试");
        param.setTitle("龙城路高速项目-风险监管");
        param.setLightAppId("");
        param.setThumbUrl("https://img-blog.csdnimg.cn/b738abdc68e549c39a8e6ffa65535a20.png");
        param.setWebpageUrl("https://img-blog.csdnimg.cn/b738abdc68e549c39a8e6ffa65535a20.png");
        param.setCustomStyle(0);
        param.setContent("1小时前   来自：项目经理张三\n作业内容：龙门吊安装\n风       险：地锚没有按标准化要求设置\n管控措施：1.按要求设置地锚，没有地锚不能进行安拆施工;(地锚为1200mm立方体水泥混凝土，预埋钢筋环为25圆钢)。\n");


        //链式构建请求
//        String result2 = HttpRequest.post(ROBOT_URL)
//                .header("accept", "*/*")
//                .header("accept-encoding", "gzip,deflate,br")
//                .header("accept-language", "zh-CN,zh;q=0.9")
//                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
//                .contentType("application/json")
//                .charset("utf-8")
//                .body(JSONUtil.toJsonStr(robot))
//                .timeout(20000)//超时，毫秒
//                .execute().body();
//        System.out.println(result2);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>(1);
        stringObjectHashMap.put("content", "1小时前");

        RobotDto robot = new RobotDto();
        robot.setContent("https://img-blog.csdnimg.cn/b738abdc68e549c39a8e6ffa65535a20.png");
        robot.setMsgType(1);
        robot.setParam(param);

        System.out.println(JSONUtil.toJsonPrettyStr(robot));
        String result1 = HttpRequest.post(ROBOT_URL)
                .body(JSONUtil.toJsonStr(robot), "application/json")
                .timeout(20000)//超时，毫秒
                .execute().body();
        System.out.println(result1);


    }
}