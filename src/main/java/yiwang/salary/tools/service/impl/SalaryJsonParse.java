package yiwang.salary.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yiwang.salary.tools.dto.SalaryCoreInfo;
import yiwang.salary.tools.dto.SalaryDto;
import yiwang.salary.tools.mapper.SalaryMapper;
import yiwang.salary.tools.security.Aes;
import yiwang.salary.tools.service.SalaryService;
import yiwang.salary.tools.util.FileLoad;
import yiwang.salary.tools.util.Md5Util;
import yiwang.salary.tools.util.SalaryConstant;
import yiwang.salary.tools.vo.Salary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: SalaryJsonParse
 * @projectName PerfectSite
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-08-10 22:41
 */

@Component
public class SalaryJsonParse implements SalaryService {

	@Autowired
	private SalaryMapper salaryMapper;


	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileLoad fileLoad;

	public List<Map<String,String>> parseSalary() {
		String salaryPath = "salary/salaryInfo.json";
		String content = fileLoad.loadContent(salaryPath);
		JSONArray array = JSON.parseArray(content);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		array.forEach(e -> {
			Map<String, String> map = new HashedMap();
			JSONObject obj = JSON.parseObject(e.toString());
			String company = obj.getString(SalaryConstant.COMPANY);
			String title = obj.getString(SalaryConstant.TITLE);
			String level = obj.getString(SalaryConstant.LEVEL);
			String yearOfExp = obj.getString(SalaryConstant.YEAR_OF_EXP);
			String yearInCome = obj.getString(SalaryConstant.YEAR_IN_COME);
			String bounsComp = obj.getString(SalaryConstant.BOUNS_COMP);
			String baseComp = obj.getString(SalaryConstant.BASE_COMP);
			String totalComp = obj.getString(SalaryConstant.TOTAL_COMP);
			String baseOfMonth = obj.getString(SalaryConstant.BASE_MONTH_COMP);
			String stockComp = obj.getString(SalaryConstant.STOCK_COMP);
			String degree = obj.getString(SalaryConstant.DEGREE);
			String location = obj.getString(SalaryConstant.LOCATION);
			String hireType = obj.getString(SalaryConstant.HIRE_TYPE);
			String hours = obj.getString(SalaryConstant.HOURS);
			map.put(SalaryConstant.COMPANY, company);
			map.put(SalaryConstant.TITLE, title);
			map.put(SalaryConstant.LEVEL, level);
			map.put(SalaryConstant.YEAR_OF_EXP, yearOfExp);
			map.put(SalaryConstant.YEAR_IN_COME, yearInCome);
			map.put(SalaryConstant.BOUNS_COMP, bounsComp);
			map.put(SalaryConstant.BASE_COMP, baseComp);
			map.put(SalaryConstant.TOTAL_COMP, totalComp);
			map.put(SalaryConstant.STOCK_COMP, stockComp);
			map.put(SalaryConstant.DEGREE, degree);
			map.put(SalaryConstant.LOCATION, location);
			map.put(SalaryConstant.HIRE_TYPE, hireType);
			map.put(SalaryConstant.HOURS, hours);
			map.put(SalaryConstant.BASE_MONTH_COMP, baseOfMonth);
			list.add(map);
		});
		return list;
	}

	/*{
			"date":"2020-08-04",
			"baseMonthComp":34000,
			"country":"domestic",
			"jobCategory":"",
			"role":"",
			"gender":"???",
			"stockComp":0,
			"focus":[
				"??????"
    		],
			"industry":"IT",
			"title":"???????????????",
			"yrOfExp":6,
			"bonusComp":102000,
			"offerType":"",
			"yrInCom":3,
			"company":"??????",
			"department":"????????????",
			"baseComp":408000,
			"iat":1596547354,
			"curTimestamp":1596547357.997798,
			"timestamp":"2020-08-0413:22:37.997790",
			"hours":"",
			"level":"D7",
			"advancedSchool":"",
			"hireType":"??????",
			"degree":"??????",
			"bachelorSchool":"",
			"location":"??????",
			"comment":"????????????????????????",
			"_id":"5f29611dc488a530608ae313",
			"totalComp":510000,
			"bonusCompMulti":3,
			"baseMonthCompMulti":12
	}*/
	public void translateSalary(){
		String salaryPath = "salary/salaryInfo.json";
		String content = fileLoad.loadContent(salaryPath);
		JSONArray array = JSON.parseArray(content);
		array.forEach(e -> {
			JSONObject obj = JSON.parseObject(e.toString());
			String company = obj.getString(SalaryConstant.COMPANY);
			String title = obj.getString(SalaryConstant.TITLE);
			String level = obj.getString(SalaryConstant.LEVEL);
			String yearOfExp = obj.getString(SalaryConstant.YEAR_OF_EXP);
			String yearInCome = obj.getString(SalaryConstant.YEAR_IN_COME);
			String bounsComp = obj.getString(SalaryConstant.BOUNS_COMP);
			String baseComp = obj.getString(SalaryConstant.BASE_COMP);
			String totalComp = obj.getString(SalaryConstant.TOTAL_COMP);
			if (StringUtils.isEmpty(obj.getString(SalaryConstant.BASE_MONTH_COMP))) {
				String baseOfMonth = Float.parseFloat(baseComp) / 12 + "";
				obj.put(SalaryConstant.BASE_MONTH_COMP, baseOfMonth);
			}
			String baseOfMonth = String.format("???%.1fk", Float.parseFloat(obj.getString(SalaryConstant.BASE_MONTH_COMP)) / 1000);
			String stockComp = obj.getString(SalaryConstant.STOCK_COMP);
			String degree = obj.getString(SalaryConstant.DEGREE);
			String location = obj.getString(SalaryConstant.LOCATION);
			String hireType = obj.getString(SalaryConstant.HIRE_TYPE);
			String hours = obj.getString(SalaryConstant.HOURS);

			salaryDeal(company, title, level, yearOfExp, yearInCome, bounsComp, baseComp, totalComp, baseOfMonth, stockComp, degree, location, hireType, hours);
		});
	}



	@Override
	public int saveSalary(SalaryDto salaryDto) {

		SalaryCoreInfo salaryCoreInfo = new SalaryCoreInfo();
		salaryCoreInfo.setBaseComp(salaryDto.getBaseComp());
		salaryCoreInfo.setBaseOfMont(salaryDto.getBaseOfMont());
		salaryCoreInfo.setBounsComp(salaryDto.getBounsComp());
		salaryCoreInfo.setStockComp(salaryDto.getStockComp());
		salaryCoreInfo.setTotalComp(salaryDto.getTotalComp());
		String salaryCoreInfoString = Aes.aesEncrypt(JSON.toJSONString(salaryCoreInfo));

		String md5Inpuf = String.format("%s%s%s%s%s%s%s%s", salaryDto.getCompany(), salaryDto.getTitle(), salaryDto.getLevel(),
				salaryDto.getYearOfExp(),salaryDto.getDegree(),salaryDto.getLocation(),salaryDto.getCollege(), salaryCoreInfo);
		String md5Info = Md5Util.MD51(md5Inpuf);

		Salary salary = new Salary(salaryDto.getCompany(), salaryDto.getTitle(),
				salaryDto.getLevel(), Float.parseFloat(salaryDto.getYearOfExp()),
				Float.parseFloat(StringUtils.isEmpty(salaryDto.getYearInCome()) ? "0" : salaryDto.getYearInCome()),
				0f,0f,0f,0f,0,
				salaryDto.getDegree(), salaryDto.getLocation(),
				"??????".equals(salaryDto.getHireType()) ? 0 : 1, salaryDto.getHours(),
				salaryDto.getCollege(), salaryCoreInfoString, md5Info);
		log.info("{}", salary);
		return salaryMapper.insert(salary);
	}

	@Override
	public List<Salary> selectByContion(String condition, Integer begin, Integer limit) {
		List<Salary> salaries = Lists.newArrayList();
		if (StringUtils.isEmpty(condition)){
			salaries = salaryMapper.selectAllPage(begin, limit);
		}else{
			salaries = salaryMapper.selectByCondition(condition, begin, limit);
		}
		// salaries = salaries.stream().collect(Collectors.toList());
		return salaries;
	}

	@Override
	public List<Salary> getAll() {
		return salaryMapper.selectAll();
	}

	@Override
	public List<String> getCompay() {
		List<Object> list =  salaryMapper.getCompany();
		List<String> listStr = list.stream().map(Object::toString).collect(Collectors.toList());
		return listStr;
	}


	/*{
		"totalyearlycompensation":"???604000.0",
			"level":"16",
			"bonus":"2??????;",
			"ck":6155,
			"degree":"??????",
			"bk":-0.5,
			"base_month":"???36.0k",
			"comp_id":3396,
			"yoe":"5???",
			"tags":"?????? ?????? 5??? 16 ???????????? ??????5??? ??????16 ??????5??? ????????????5???",
			"bu":"",
			"company":"??????",
			"location":"??????",
			"comp_type":"??????Offer",
			"pk":60.7,
			"position":"??????",
			"stock":"",
			"others":"?????????10w??????",
			"timestamp":"11/8/2020 17:26"
	}*/
	public void parseSalaryOffer(){
		String salaryPath = "salary/salary1.json";
		String content = fileLoad.loadContent(salaryPath);
		JSONArray array = JSON.parseArray(content);
		AtomicInteger i = new AtomicInteger(1);
		array.forEach(e -> {
			JSONObject obj = JSON.parseObject(e.toString());
			String companyName = decodeUnicode(obj.getString("ck"), obj.getString("company"));

			float tmp = Math.round(obj.getFloatValue("totalyearlycompensation") - obj.getFloatValue("pk"));
			String s = ("???" + (1e3 * tmp)).toString();//.replace("/\B(?=(\d{3})+(?!\d))/g", ",");
			float tmp_r = (Math.round(obj.getFloatValue("base_month") - obj.getFloatValue("bk")) * 10) / 10;
			String r = tmp_r == 0 ? "-" : "???" + tmp_r + "k";

			String tags = decodeUnicode(obj.getString("ck"), obj.getString("tags"));
			obj.replace("tags", tags);
			obj.replace("company", companyName);
			obj.replace("totalyearlycompensation", s);
			obj.replace("base_month", r);


			String company = companyName;
			String title = obj.getString(SalaryConstant.POSITION);
			String level = obj.getString(SalaryConstant.LEVEL);
			String yearOfExp = StringUtils.isEmpty(obj.getString("yoe")) ? "0": obj.getString("yoe").replace("???", "");
			yearOfExp = StringUtils.equalsIgnoreCase("?????????", yearOfExp) ? "0" : yearOfExp;
			String yearInCome = "";// obj.getString(SalaryConstant.YEAR_IN_COME);
			String bounsComp = obj.getString("bonus");
			String baseComp = "";//obj.getString(SalaryConstant.BASE_COMP);
			String totalComp = s ;//obj.getString("totalyearlycompensation");
			String baseOfMonth = r; //obj.getString("base_month");
			String stockComp = obj.getString("stock");
			String degree = obj.getString(SalaryConstant.DEGREE);
			String location = obj.getString(SalaryConstant.LOCATION);
			String hireType = obj.getString("comp_type");
			String hours = "";// obj.getString(SalaryConstant.HOURS);

			salaryDeal(company, title, level, yearOfExp, yearInCome, bounsComp, baseComp, totalComp, baseOfMonth, stockComp, degree, location, hireType, hours);
		});

	}

	private void salaryDeal(String company, String title, String level,
							String yearOfExp, String yearInCome, String bounsComp,
							String baseComp, String totalComp, String baseOfMonth,
							String stockComp, String degree, String location, String hireType, String hours) {
		try{
			SalaryCoreInfo coreInfo = new SalaryCoreInfo(bounsComp, baseComp, totalComp, baseOfMonth, stockComp);
			String salaryCoreInfo = Aes.aesEncrypt(JSON.toJSONString(coreInfo));

			String md5Inpuf = String.format("%s%s%s%s%s%s%s%s", company, title, level,
					yearOfExp,degree,location,"", salaryCoreInfo);
			String md5Info = Md5Util.MD51(md5Inpuf);

			Salary salary = new Salary(company, title, level, Float.parseFloat(yearOfExp),
					Float.parseFloat(StringUtils.isEmpty(yearInCome) ? "0" : yearInCome),
					0f, 0f, 0f, 0f, 0, degree, location,
					"??????".equals(hireType) ? 0 : 1, hours, "", salaryCoreInfo, md5Info);
			salaryMapper.insert(salary);
		}catch (Exception e){
			log.error("add date error, {}", e.getMessage());
		}

	}

	private String decodeUnicode(String key, String content) {
		char[] tc;
		tc = content.toCharArray();
		for (int i = 0; i < tc.length; i++) {
			int tmp = tc[i] - Integer.valueOf(key);
			if (tmp < 0) {
				tmp = tmp + 65536;
			}
			tc[i] = (char)tmp;
		}
		content = String.valueOf(tc);
		return content;
	}

}
