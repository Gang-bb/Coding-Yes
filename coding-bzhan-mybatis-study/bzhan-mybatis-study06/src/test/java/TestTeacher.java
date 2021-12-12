import com.gangbb.model.dao.StudentMapper;
import com.gangbb.model.dao.TeacherMapper;
import com.gangbb.model.pojo.Student;
import com.gangbb.utlis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : TestTeacher
 * @Description :
 * @Date : 2021/2/9 10:36
 */
public class TestTeacher {
    /**
     * 一对多模型
     */
    @Test
    public void getTeacherOne(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
            //按照结果嵌套处理
            //System.out.println(teacherMapper.getTeacher(1));
            //按照查询嵌套处理
            System.out.println(teacherMapper.getTeacher2(1));
        } finally {
            sqlSession.close();
        }
    }
}
