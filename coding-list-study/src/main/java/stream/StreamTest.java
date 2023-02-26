package stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stream测试 <用户id：<议案id：议案投票结果>>
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class StreamTest {
    public static void main(String[] args) {
        List<IndependentMemberApproval> list = new ArrayList<>();
        list.add(new IndependentMemberApproval(1L, 1L, 1));
        list.add(new IndependentMemberApproval(1L, 2L, 1));

        list.add(new IndependentMemberApproval(2L, 1L, 1));
        list.add(new IndependentMemberApproval(2L, 2L, 0));
        list.add(new IndependentMemberApproval(2L, 3L, 0));

        list.add(new IndependentMemberApproval(3L, 1L, 1));
        list.add(new IndependentMemberApproval(3L, 2L, 0));

        Map<Long, Map<Long, Integer>> memberApprovalMap = list.stream()
                .collect(Collectors.groupingBy(IndependentMemberApproval::getSysUserId,
                        Collectors.toMap(IndependentMemberApproval::getMeetingBillId, IndependentMemberApproval::getIsApproval)));

        System.out.println(memberApprovalMap);
    }

    static class IndependentMemberApproval {

        /**
         * 独董事前认可记录id
         */
        private Long independentMemberApprovalId;
        /**
         * 关联用户id;(sys_user.id)
         */
        private Long sysUserId;
        /**
         * 关联用户名称
         */
        private String sysUserName;
        /**
         * 关联会议id;(t_meeting.id)
         */
        private Long meetingId;
        /**
         * 会议名称
         */
        private String meetingName;
        /**
         * 关联会议议案id;(t_meeting_bill.id)
         */
        private Long meetingBillId;
        /**
         * 议案标题
         */
        private String billTittle;
        /**
         * 是否认可;(字典：common_yes_no)
         */
        private Integer isApproval;
        /**
         * 关联议案附件id;(如果不认可,关联的认可材料)
         */
        private Long billEnclosureId;

        public IndependentMemberApproval(Long sysUserId, Long meetingBillId, Integer isApproval) {
            this.sysUserId = sysUserId;
            this.meetingBillId = meetingBillId;
            this.isApproval = isApproval;
        }

        public Long getIndependentMemberApprovalId() {
            return independentMemberApprovalId;
        }

        public void setIndependentMemberApprovalId(Long independentMemberApprovalId) {
            this.independentMemberApprovalId = independentMemberApprovalId;
        }

        public Long getSysUserId() {
            return sysUserId;
        }

        public void setSysUserId(Long sysUserId) {
            this.sysUserId = sysUserId;
        }

        public String getSysUserName() {
            return sysUserName;
        }

        public void setSysUserName(String sysUserName) {
            this.sysUserName = sysUserName;
        }

        public Long getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(Long meetingId) {
            this.meetingId = meetingId;
        }

        public String getMeetingName() {
            return meetingName;
        }

        public void setMeetingName(String meetingName) {
            this.meetingName = meetingName;
        }

        public Long getMeetingBillId() {
            return meetingBillId;
        }

        public void setMeetingBillId(Long meetingBillId) {
            this.meetingBillId = meetingBillId;
        }

        public String getBillTittle() {
            return billTittle;
        }

        public void setBillTittle(String billTittle) {
            this.billTittle = billTittle;
        }

        public Integer getIsApproval() {
            return isApproval;
        }

        public void setIsApproval(Integer isApproval) {
            this.isApproval = isApproval;
        }

        public Long getBillEnclosureId() {
            return billEnclosureId;
        }

        public void setBillEnclosureId(Long billEnclosureId) {
            this.billEnclosureId = billEnclosureId;
        }
    }
}
