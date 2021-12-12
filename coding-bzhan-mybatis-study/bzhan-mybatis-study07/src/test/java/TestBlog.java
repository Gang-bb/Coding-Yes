import com.gangbb.model.dao.BlogMapper;
import com.gangbb.model.pojo.Blog;
import com.gangbb.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Gangbb
 * @ClassName : TestBlog
 * @Description :
 * @Date : 2021/2/9 13:22
 */
public class TestBlog {
    @Test
    public void getBlogs(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();
            map.put("author", "Gangbb");
            List<Blog> blogList = blogMapper.getBlogsIf(map);
            for (int i = 0; i < blogList.size(); i++) {
                Blog blog =  blogList.get(i);
                System.out.println(blog);
            }
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void BlogChoose(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();
            //下面两个随意填入条件测试
            //map.put("title", "学习如何找女朋友");
            //map.put("author", "Gangbb");
            List<Blog> blogList = blogMapper.getBlogsChoose(map);
            for (Blog blog : blogList) {
                System.out.println(blog);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void UpdateBlog(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();
            map.put("id", 3);
            //map.put("title", "学习如何找女朋友2");
            map.put("author", "Gangbb2");
            int updateBlog = blogMapper.updateBlog(map);
            System.out.println(updateBlog);
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void BlogForeach(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Map map = new HashMap();
            ArrayList<Integer> ids = new ArrayList<Integer>();
            ids.add(1);
            map.put("ids", ids);
            List<Blog> blogList = blogMapper.getBlogForeach(map);
            for (Blog blog : blogList) {
                System.out.println(blog);
            }
        } finally {
            sqlSession.close();
        }
    }
}
