package yiwang.salary.tools.mapper;

import org.apache.ibatis.annotations.Param;
import yiwang.salary.tools.vo.Comment;

import java.util.List;

public interface CommentMapper {
    int insert(Comment record);

    List<Comment> selectAll();

    List<Comment> selectAllBySalaryId(@Param("salaryId") Integer salaryId);
}