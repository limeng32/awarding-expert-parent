package cn.chinaunicom.awarding.expert.web;

import java.util.Calendar;
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

import cn.chinaunicom.awarding.expert.condition.ExpertCondition;
import cn.chinaunicom.awarding.expert.condition.TaskExpertCondition;
import cn.chinaunicom.awarding.expert.enums.TaskExpertStatus;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.ExpertService;
import cn.chinaunicom.awarding.expert.persist.TaskExpert;
import cn.chinaunicom.awarding.expert.persist.TaskExpertService;
import cn.chinaunicom.awarding.project.condition.TaskCondition;
import cn.chinaunicom.awarding.project.enums.TaskStatus;
import cn.chinaunicom.awarding.project.persist.Task;
import cn.chinaunicom.awarding.project.persist.TaskService;

@Controller
public class ExpertCommonController {

	@Autowired
	private ExpertService expertService;

	@Autowired
	private TaskExpertService taskExpertService;

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
		TaskExpert tec = new TaskExpert();
		tec.setTask(tc);
		for (Expert e : expertC) {
			taskExpertService.loadExpert(e, tec);
		}
		Page<Expert> page = new Page<>(expertC, ec.getLimiter());

		Object[] ret = new Object[2];
		ret[0] = tasks[0];
		ret[1] = page;
		callback.setData(ret);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/initExpertInvite2")
	public String initExpertInvite2(HttpServletRequest request, ModelMap mm) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		TaskCondition tc = new TaskCondition();
		tc.setLimiter((new PageParam(1, 1)));
		tc.setSorter(new SortParam(new Order("begin",
				Conditionable.Sequence.desc)));
		tc.setStatus(TaskStatus.ongoing);
		TaskExpertCondition tec2 = new TaskExpertCondition();
		tec2.setTask(tc);
		tec2.setSorter(new SortParam(new Order("timeStamp",
				Conditionable.Sequence.asc)));
		tec2.setLimiter(new PageParam(1, 10));
		Collection<TaskExpert> taskExpertC = taskExpertService.selectAll(tec2);

		Collection<Expert> expertC = new LinkedHashSet<>();
		for (TaskExpert taskExpert : taskExpertC) {
			expertC.add(taskExpert.getExpert());
		}
		Page<Expert> page = new Page<>(expertC, tec2.getLimiter());

		callback.setData(page);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/listExpert")
	public String listExpert(HttpServletRequest request,
			HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);

		TaskCondition tc = new TaskCondition();
		tc.setLimiter((new PageParam(1, 1)));
		tc.setSorter(new SortParam(new Order("begin",
				Conditionable.Sequence.desc)));
		tc.setStatus(TaskStatus.ongoing);
		if (pageNo == null) {
			pageNo = 1;
		}
		ExpertCondition ec = new ExpertCondition();
		ec.setLimiter(new PageParam(pageNo, 10));
		Collection<Expert> expertC = expertService.selectAll(ec);
		TaskExpert tec = new TaskExpert();
		tec.setTask(tc);
		for (Expert e : expertC) {
			taskExpertService.loadExpert(e, tec);
		}
		Page<Expert> page = new Page<>(expertC, ec.getLimiter());
		callback.setData(page);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/listInvitedExpert")
	public String listInvitedExpert(HttpServletRequest request,
			HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		TaskCondition tc = new TaskCondition();
		tc.setLimiter((new PageParam(1, 1)));
		tc.setSorter(new SortParam(new Order("begin",
				Conditionable.Sequence.desc)));
		tc.setStatus(TaskStatus.ongoing);
		TaskExpertCondition tec2 = new TaskExpertCondition();
		tec2.setSorter(new SortParam(new Order("timeStamp",
				Conditionable.Sequence.asc)));
		tec2.setTask(tc);
		if (pageNo == null) {
			pageNo = 1;
		}
		tec2.setLimiter(new PageParam(pageNo, 10));
		Collection<TaskExpert> taskExpertC = taskExpertService.selectAll(tec2);

		Collection<Expert> expertC = new LinkedHashSet<>();
		for (TaskExpert taskExpert : taskExpertC) {
			expertC.add(taskExpert.getExpert());
		}
		Page<Expert> page = new Page<>(expertC, tec2.getLimiter());
		callback.setData(page);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/unInviteExpert")
	public String unInviteExpert(HttpServletRequest request,
			HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "expertId") String expertId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		TaskCondition tc = new TaskCondition();
		tc.setLimiter((new PageParam(1, 1)));
		tc.setSorter(new SortParam(new Order("begin",
				Conditionable.Sequence.desc)));
		tc.setStatus(TaskStatus.ongoing);

		TaskExpert tec = new TaskExpert();
		Expert ec = new Expert();
		ec.setId(expertId);
		tec.setExpert(ec);
		tec.setTask(tc);
		Collection<TaskExpert> taskExpertC = taskExpertService.selectAll(tec);
		if (taskExpertC.size() == 1) {
			for (TaskExpert taskExpert : taskExpertC) {
				taskExpertService.delete(taskExpert);
			}
		}

		TaskExpertCondition tec2 = new TaskExpertCondition();
		tec2.setSorter(new SortParam(new Order("timeStamp",
				Conditionable.Sequence.asc)));
		tec2.setTask(tc);
		if (pageNo == null) {
			pageNo = 1;
		}
		Collection<TaskExpert> taskExpertC2 = null;
		do {
			tec2.setLimiter(new PageParam(pageNo, 10));
			taskExpertC2 = taskExpertService.selectAll(tec2);
		} while (pageNo-- > tec2.getLimiter().getMaxPageNum());
		Collection<Expert> expertC = new LinkedHashSet<>();
		for (TaskExpert taskExpert : taskExpertC2) {
			expertC.add(taskExpert.getExpert());
		}
		Page<Expert> page = new Page<>(expertC, tec2.getLimiter());
		callback.setData(page);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/inviteExpert")
	public String inviteExpert(HttpServletRequest request,
			HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "expertId") String expertId,
			@RequestParam(value = "taskId") String taskId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		Task tc = new Task();
		tc.setId(taskId);
		Expert ec = new Expert();
		ec.setId(expertId);
		TaskExpert taskExpert = new TaskExpert();
		taskExpert.setExpert(ec);
		taskExpert.setTask(tc);
		if (taskExpertService.count(taskExpert) == 0) {
			taskExpert.setStatus(TaskExpertStatus.invite);
			taskExpert.setTimeStamp(Calendar.getInstance().getTime());
			taskExpertService.insert(taskExpert);
			TaskExpertCondition tec = new TaskExpertCondition();
			tec.setTask(tc);
			tec.setLimiter(new PageParam(pageNo == null ? 1 : pageNo, 10));
			tec.setSorter(new SortParam(new Order("timeStamp",
					Conditionable.Sequence.asc)));
			Collection<TaskExpert> taskExpertC = taskExpertService
					.selectAll(tec);
			if (!pageNo.equals(tec.getLimiter().getMaxPageNum())) {
				tec.setLimiter(new PageParam(tec.getLimiter().getMaxPageNum(),
						10));
				taskExpertC = taskExpertService.selectAll(tec);
			}
			Collection<Expert> expertC = new LinkedHashSet<>();
			for (TaskExpert temp : taskExpertC) {
				expertC.add(temp.getExpert());
			}
			Page<Expert> page = new Page<>(expertC, tec.getLimiter());
			callback.setData(page);
			callback.setFlag(true);
		} else {
			callback.setFlag(false);
		}
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/emailExpert")
	public String emailExpert(HttpServletRequest request,
			HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "expertId") String expertId,
			@RequestParam(value = "taskId") String taskId) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		Expert ec = new Expert();
		ec.setId(expertId);
		Task tc = new Task();
		tc.setId(taskId);
		TaskExpert tec = new TaskExpert();
		tec.setExpert(ec);
		tec.setTask(tc);
		Collection<TaskExpert> taskExpertC = taskExpertService.selectAll(tec);
		if (taskExpertC.size() == 1) {
			for (TaskExpert taskExpert : taskExpertC) {
				taskExpert.setStatus(TaskExpertStatus.email);
				if (taskExpertService.update(taskExpert) == 1) {
					callback.setFlag(true);
				}
			}
		}
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/expert/confirmExpert")
	public String confirmExpert(HttpServletRequest request,
			HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "expertId") String expertId,
			@RequestParam(value = "taskId") String taskId) {
		Callback callback = new Callback();
		mm.addAttribute("_content", callback);
		Expert ec = new Expert();
		ec.setId(expertId);
		Task tc = new Task();
		tc.setId(taskId);
		TaskExpert tec = new TaskExpert();
		tec.setExpert(ec);
		tec.setTask(tc);
		Collection<TaskExpert> taskExpertC = taskExpertService.selectAll(tec);
		if (taskExpertC.size() == 1) {
			for (TaskExpert taskExpert : taskExpertC) {
				taskExpert.setStatus(TaskExpertStatus.confirm);
				if (taskExpertService.update(taskExpert) == 1) {
					callback.setFlag(true);
				}
			}
		}
		return UNIQUE_VIEW_NAME;
	}
}
