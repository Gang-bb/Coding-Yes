package com.gangbb.model.dao;

import com.gangbb.model.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : Gangbb
 * @ClassName : BlogMapper
 * @Description :
 * @Date : 2021/2/9 13:19
 */
public interface BlogMapper {

    List<Blog> getBlogsIf(Map map);

    List<Blog> getBlogsChoose(Map map);

    int updateBlog(Map map);

    List<Blog> getBlogForeach(Map map);
}
