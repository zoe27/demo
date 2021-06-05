package tools.mapper;

import org.apache.ibatis.annotations.Param;
import yiwang.salary.tools.vo.EvaluateComp;

import java.util.List;

public interface EvaluateCompMapper {
    int insert(EvaluateComp record);

    List<EvaluateComp> selectAll();

    List<EvaluateComp> selectByCompany(@Param("companyName") String companyName, @Param("companyId") Integer companyId);
}