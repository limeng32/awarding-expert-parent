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
public class ExpertAvoidsTest {

	@Autowired
	private AcceptionService acceptionService;

	@Autowired
	private ExpertService expertService;

	@Autowired
	private ExpertAvoidsService expertAvoidsService;

	@Autowired
	private TaskService taskService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/expertAvoidsTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/cn/chinaunicom/awarding/expert/persist/expertAvoidsTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/expertAvoidsTest/testSelect.result.xml")
	public void testSelect() {
		Acception acception = acceptionService.select("a");
		expertAvoidsService.loadAcception(acception, new ExpertAvoids());
		Assert.assertEquals(1, acception.getExpertAvoids().size());

		Expert expert = expertService.select("e");
		expertAvoidsService.loadExpert(expert, new ExpertAvoids());
		Assert.assertEquals(1, expert.getExpertAvoids().size());

		Task task = taskService.select("t");
		expertAvoidsService.loadTask(task, new ExpertAvoids());
		Assert.assertEquals(1, task.getExpertAvoids().size());

		ExpertAvoids expertAvoids = expertAvoidsService.select("e");
		Acception acception2 = acceptionService.select("a2");
		expertAvoids.setAcception(acception2);
		Expert expert2 = expertService.select("e2");
		expertAvoids.setExpert(expert2);
		Task task2 = taskService.select("t2");
		expertAvoids.setTask(task2);
		expertAvoidsService.update(expertAvoids);

		ExpertAvoids expertAvoids2 = expertAvoidsService.select("e2");
		expertAvoidsService.delete(expertAvoids2);
	}
}
