package cn.chinaunicom.awarding.expert.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import limeng32.mirage.util.config.Callback;
import limeng32.mybatis.mybatisPlugin.cachePlugin.Conditionable;
import limeng32.mybatis.mybatisPlugin.cachePlugin.Order;
import limeng32.mybatis.mybatisPlugin.cachePlugin.PageParam;
import limeng32.mybatis.mybatisPlugin.cachePlugin.SortParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.chinaunicom.awarding.project.condition.TaskCondition;
import cn.chinaunicom.awarding.project.enums.TaskStatus;
import cn.chinaunicom.awarding.project.persist.Task;
import cn.chinaunicom.awarding.project.persist.TaskService;

@Controller
public class ExpertCommonController {

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

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/listExpertWithTask")
	public String listExpertWithTask(HttpServletRequest request, ModelMap mm) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		TaskCondition tc = new TaskCondition();
		tc.setLimiter((new PageParam(1, 1)));
		tc.setSorter(new SortParam(new Order("begin",
				Conditionable.Sequence.desc)));
		tc.setStatus(TaskStatus.ongoing);
		Collection<Task> taskC = taskService.selectAll(tc);
		Task[] tasks = taskC.toArray(new Task[taskC.size()]);
		callback.setData(tasks);
		return UNIQUE_VIEW_NAME;
	}
}
