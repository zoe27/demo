package yiwang.salary.tools.service.impl;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yiwang.salary.tools.mapper.CommentMapper;
import yiwang.salary.tools.service.CommentService;
import yiwang.salary.tools.vo.Comment;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: CommentServiceImpl
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2021-01-03 11:32
 */
@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public Comment addComment(Comment comment) {
        if (null == comment.getSalaryId()){
            return null;
        }
        comment.setAddTime(new Date());
        commentMapper.insert(comment);
        return comment;
    }

    @Override
    public List<Comment> getCommentBySalaryId(Integer salaryId) {
        if (null == salaryId){
            return Lists.newArrayList();
        }
        List<Comment> list = commentMapper.selectAllBySalaryId(salaryId);
        return list;
    }
}
