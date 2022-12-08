package json;
import cn.hutool.json.JSONUtil;

import java.time.LocalDateTime;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class Test {
    public static void main(String[] args) {
        Manhour manhour = new Manhour();
        manhour.setId(0);
        manhour.setProjectId(0);
        manhour.setProjectName("");
        manhour.setUserId(0L);
        manhour.setUserNickName("");
        manhour.setTaskId(0);
        manhour.setTaskName("");
        manhour.setTaskProgress("");
        manhour.setRecordDate(LocalDateTime.now());
        manhour.setWorkHour(0);
        manhour.setWorkContent("");
        manhour.setManhourStatus("");
        manhour.setApprovalWorkHour(0);
        manhour.setApprovalOpinion("");
        manhour.setApprovalUserId(0);
        manhour.setApprovalUserNickName("");
        manhour.setApprovalTime(LocalDateTime.now());
        System.out.println(JSONUtil.toJsonStr(manhour));

    }
}
