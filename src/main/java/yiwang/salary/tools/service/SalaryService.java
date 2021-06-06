/**
 * @Description: TODO
 * @author 作者
 * @version 创建时间：2020年8月17日 下午10:24:34
 * SalaryService.java
 * @since JDK 1.8
 * @version V1.0
 * Copyright (c) 2020, zoe27@126.com All Rights Reserved.
 * 
 */
package yiwang.salary.tools.service;

import yiwang.salary.tools.dto.SalaryDto;
import yiwang.salary.tools.vo.Salary;

import java.util.List;

public interface SalaryService {

    int saveSalary(SalaryDto salaryDto);

    List<Salary> selectByContion(String condition, Integer begin, Integer limit);

    List<Salary> getAll();

    List<String> getCompay();
}

