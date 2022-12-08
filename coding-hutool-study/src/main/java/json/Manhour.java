package json;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author manas
 * @since 2022-05-27
 */
@Getter
@Setter
@Accessors(chain = true)
public class Manhour {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 关联项目id
     */
    private Integer projectId;

    /**
     * 关联项目名称(冗余)
     */
    private String projectName;

    /**
     * 关联用户id
     */
    private Long userId;

    /**
     * 关联用户名字(冗余)
     */
    private String userNickName;

    /**
     * 关联任务id
     */
    private Integer taskId;

    /**
     * 关联任务名称
     */
    private String taskName;

    /**
     * 关联任务进度
     */
    private String taskProgress;

    /**
     * 记录时间
     */
    private LocalDateTime recordDate;

    /**
     * 工时(小时)
     */
    private Integer workHour;

    /**
     * 工作内容
     */
    private String workContent;

    /**
     * 工时审批状态(字典：manhour_status)
     */
    private String manhourStatus;

    /**
     * 最后审批工时(小时)
     */
    private Integer approvalWorkHour;

    /**
     * 最后审批意见
     */
    private String approvalOpinion;

    /**
     * 最后审批人id
     */
    private Integer approvalUserId;

    /**
     * 最后审批人名字(冗余)
     */
    private String approvalUserNickName;

    /**
     * 最后审批审批时间
     */
    private LocalDateTime approvalTime;


}
