package cn.chinaunicom.awarding.expert.web;

import java.util.Collection;

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

import cn.chinaunicom.awarding.expert.condition.ExpertCondition;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.ExpertService;
import cn.chinaunicom.awarding.project.condition.TaskCondition;
import cn.chinaunicom.awarding.project.enums.ProjectPhase;
import cn.chinaunicom.awarding.project.enums.TaskStatus;
import cn.chinaunicom.awarding.project.persist.Task;
import cn.chinaunicom.awarding.project.persist.TaskService;

@Controller
public class ExpertCommonController {

	@Autowired
	private ExpertService expertService;

	@Autowired
	private TaskService taskService;

	public static final String UNIQUE_VIEW_NAME = "__unique_view_name";

	@RequestMapping(method = { RequestMethod.GET }, value = "/group")
	public String group(HttpServletRequest request) {
		return "account/group";
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/expert")
	public String expert(HttpServletRequest request) {
		return "account/expert";
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/expertNew")
	public String expertNew(HttpServletRequest request) {
		return "account/expertNew";
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/initExpertInvite")
	public String initExpertInvite(HttpServletRequest request, ModelMap mm) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		TaskCondition tc = new TaskCondition();
		tc.setLimiter((new PageParam(1, 1)));
		tc.setSorter(new SortParam(new Order("begin",
				Conditionable.Sequence.desc)));
		tc.setStatus(TaskStatus.ongoing);
		Collection<Task> taskC = taskService.selectAll(tc);
		Task[] tasks = taskC.toArray(new Task[taskC.size()]);
		ExpertCondition ec = new ExpertCondition();
		ec.setLimiter(new PageParam(1, 10));
		Collection<Expert> expertC = expertService.selectAll(ec);
		Page<Expert> page = new Page<>(expertC, ec.getLimiter());
		Object[] ret = new Object[2];
		ret[0] = tasks[0];
		ret[1] = page;
		callback.setData(ret);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/listExpert")
	public String listExpert(HttpServletRequest request,
			HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		if (pageNo == null) {
			pageNo = 1;
		}
		ExpertCondition ec = new ExpertCondition();
		ec.setLimiter(new PageParam(pageNo, 10));
		Collection<Expert> expertC = expertService.selectAll(ec);
		Page<Expert> page = new Page<>(expertC, ec.getLimiter());
		callback.setData(page);
		return UNIQUE_VIEW_NAME;
	}
}
