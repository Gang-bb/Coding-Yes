import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stream测试
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
}
