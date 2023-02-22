package utils;

import lombok.Data;

/**
 * t_meeting_sign表 对应实体对象
 *
 * @author lyx
 * @date 2022-07-11
 **/
@Data
public class MeetingSign {

    private static final long serialVersionUID=1L;

    /**
     * 会议签到记录id
     */
    private Long meetingSignId;
    /**
     * 关联会议id;(t_meeting.id)
     */
    private Long meetingId;
    /**
     * 关联用户id;(sys_user.id)
     */
    private Long sysUserId;
    /**
     * 关联用户名称
     */
    private String sysUserName;

}
