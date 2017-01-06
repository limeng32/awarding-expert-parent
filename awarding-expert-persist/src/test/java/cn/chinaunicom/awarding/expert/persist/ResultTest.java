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
public class ResultTest {

	@Autowired
	private AcceptionService acceptionService;

	@Autowired
	private AwardService awardService;

	@Autowired
	private ResultService resultService;

	@Autowired
	private TaskService taskService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/resultTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/resultTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/resultTest/testSelect.result.xml")
	public void testSelect() {
		Acception acception = acceptionService.select("a");
		resultService.loadAcception(acception, new Result());
		Assert.assertEquals(1, acception.getResult().size());

		Award award = awardService.select("aw");
		resultService.loadAward(award, new Result());
		Assert.assertEquals(1, award.getResult().size());

		Task task = taskService.select("t");
		resultService.loadTask(task, new Result());
		Assert.assertEquals(1, task.getResult().size());

		Result result = resultService.select("r");

		Acception acception2 = acceptionService.select("a2");
		result.setAcception(acception2);
		Award award2 = awardService.select("aw2");
		result.setAward(award2);
		Task task2 = taskService.select("t2");
		result.setTask(task2);
		resultService.update(result);

		Result result2 = resultService.select("r2");
		resultService.delete(result2);
	}
}
