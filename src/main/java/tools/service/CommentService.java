package tools.service;

import yiwang.salary.tools.vo.Comment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: CommentService
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2021-01-03 11:31
 */
public interface CommentService {

    Comment addComment(Comment comment);

    List<Comment> getCommentBySalaryId(Integer salaryId);
}
