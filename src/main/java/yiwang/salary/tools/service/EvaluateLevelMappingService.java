package yiwang.salary.tools.service;

import yiwang.salary.tools.dto.LevelMapping;
import yiwang.salary.tools.vo.EvaluateComp;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: EvaluateLevelMapping
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2021-01-03 11:55
 */
public interface EvaluateLevelMappingService {

    EvaluateComp addEvaluateComp(EvaluateComp evaluateComp);

    List<EvaluateComp> getEvaluateComp(String companyName, Integer companyId);

    List<LevelMapping> evaluateLevel(LevelMapping levelMapping);
}
