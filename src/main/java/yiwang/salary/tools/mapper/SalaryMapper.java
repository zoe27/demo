package yiwang.salary.tools.mapper;

import org.apache.ibatis.annotations.Param;
import yiwang.salary.tools.vo.Salary;

import java.util.List;

public interface SalaryMapper {
    int insert(Salary record);

    List<Salary> selectAll();

    //    @Select("select * from salary limit #{begin}, #{limit}")
    List<Salary> selectAllPage(@Param("begin") Integer begin, @Param("limit") Integer limit);

    List<Salary> selectByCondition(@Param("condition") String condition, @Param("begin") Integer begin, @Param("limit") Integer limit);

    List<Object> getCompany();
}