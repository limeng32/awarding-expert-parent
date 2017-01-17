package cn.chinaunicom.awarding.expert.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import limeng32.mirage.util.config.Callback;
import limeng32.mybatis.mybatisPlugin.cachePlugin.Conditionable;
import limeng32.mybatis.mybatisPlugin.cachePlugin.Order;
import limeng32.mybatis.mybatisPlugin.cachePlugin.Page;
import limeng32.mybatis.mybatisPlugin.cachePlugin.PageParam;
import limeng32.mybatis.mybatisPlugin.cachePlugin.SortParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.chinaunicom.awarding.account.condition.AccountCondition;
import cn.chinaunicom.awarding.account.enums.Company;
import cn.chinaunicom.awarding.account.enums.CompanyBean;
import cn.chinaunicom.awarding.account.enums.CompanyType;
import cn.chinaunicom.awarding.project.condition.ProjectCondition;
import cn.chinaunicom.awarding.project.enums.ProjectPhase;
import cn.chinaunicom.awarding.project.persist.Project;
import cn.chinaunicom.awarding.project.persist.ProjectService;

@Controller
public class GroupCommonController {

	@Autowired
	private ProjectService projectService;

	private static final String UNIQUE_VIEW_NAME = "__unique_view_name";

	@RequestMapping(method = { RequestMethod.POST }, value = "/group/listProject")
	public String listProject(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap mm,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "phase", required = false) ProjectPhase phase,
			@RequestParam(value = "company", required = false) Company company,
			@RequestParam(value = "companyType", required = false) CompanyType companyType) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);

		if (pageNo == null) {
			pageNo = 1;
		}
		ProjectCondition condition = new ProjectCondition();
		condition.setLimiter(new PageParam(pageNo, 5));
		condition.setSorter(new SortParam(new Order("editTime",
				Conditionable.Sequence.desc)));
		condition.setPhase(phase);
		AccountCondition ac = new AccountCondition();

		if (companyType != null) {
			Collection<Company> tempC = Company.sortByCompanyType(companyType);
			ac.setCompanyIn((ArrayList<Company>) tempC);
		}

		ac.setCompany(company);
		condition.setAccount(ac);
		Collection<Project> projectC = projectService.selectAll(condition);
		Page<Project> page = new Page<>(projectC, condition.getLimiter());
		callback.setData(page);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/group/listCompanyType")
	public String listCompanyType(HttpServletRequest request,
			HttpServletResponse response, ModelMap mm) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		CompanyType[] companyTypes = CompanyType.values();
		Collection<?>[] ret = new Collection<?>[companyTypes.length];
		int i = 0;
		for (CompanyType companyType : companyTypes) {
			Collection<Company> tempC = Company.sortByCompanyType(companyType);
			Collection<CompanyBean> companyC = new LinkedHashSet<>();
			for (Company temp : tempC) {
				companyC.add(temp.toBean());
			}
			ret[i] = companyC;
			i++;
		}
		callback.setData(ret);
		return UNIQUE_VIEW_NAME;
	}
}