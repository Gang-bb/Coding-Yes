import com.gangbb.model.dao.StudentMapper;
import com.gangbb.model.pojo.Student;
import com.gangbb.utlis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : TestStudent
 * @Description :
 * @Date : 2021/2/9 9:44
 */
public class TestStudent {
    @Test
    public void getStudent(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            //**方式一：按照查询嵌套处理**
            //List<Student> studentList = studentMapper.getStudent();
            //**方式二：按照结果嵌套处理**
            List<Student> studentList = studentMapper.getStudent2();

            for (int i = 0; i < studentList.size(); i++) {
                Student student =  studentList.get(i);
                System.out.println(student);
            }
        } finally {
            sqlSession.close();
        }

    }
}
