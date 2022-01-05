/**
 * 对接云之家请求参数
 *
 * @author Gangbb
 * @date 2022/1/4
 **/
public class RobotDto {

    private String content;
    private int msgType;
    private RobotParamDto param;

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
    public int getMsgType() {
        return msgType;
    }

    public RobotParamDto getParam() {
        return param;
    }

    public void setParam(RobotParamDto param) {
        this.param = param;
    }
}