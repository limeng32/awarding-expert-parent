package cn.chinaunicom.awarding.expert.persist;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import cn.chinaunicom.awarding.expert.enums.TaskExpertStatus;
import cn.chinaunicom.awarding.project.persist.Task;
import cn.chinaunicom.awarding.project.persist.TaskService;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.FlatXmlDataSetLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:expert-persist-test.xml",
		"classpath:project-service.xml", "classpath:account-service.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class)
public class TaskExpertTest {

	@Autowired
	private ExpertService expertService;

	@Autowired
	private TaskExpertService taskExpertService;

	@Autowired
	private TaskService taskService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/taskExpertTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/cn/chinaunicom/awarding/expert/persist/taskExpertTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/taskExpertTest/testSelect.result.xml")
	public void testSelect() {
		Expert expert = expertService.select("e");
		taskExpertService.loadExpert(expert, new TaskExpert());
		Assert.assertEquals(1, expert.getTaskExpert().size());

		Task task = taskService.select("t");
		taskExpertService.loadTask(task, new TaskExpert());
		Assert.assertEquals(1, task.getTaskExpert().size());

		TaskExpert taskExpert = taskExpertService.select("te");
		Assert.assertEquals(TaskExpertStatus.invite, taskExpert.getStatus());
		Expert expert2 = expertService.select("e2");
		taskExpert.setExpert(expert2);
		Task task2 = taskService.select("t2");
		taskExpert.setTask(task2);
		taskExpert.setStatus(TaskExpertStatus.email);
		taskExpertService.update(taskExpert);

		TaskExpert taskExpert2 = taskExpertService.select("te2");
		taskExpertService.delete(taskExpert2);
	}
}
