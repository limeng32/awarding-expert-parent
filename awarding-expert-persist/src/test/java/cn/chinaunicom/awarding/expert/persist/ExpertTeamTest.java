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
public class ExpertTeamTest {

	@Autowired
	private ExpertTeamService expertTeamService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private AwardService awardService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/expertTeam/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/cn/chinaunicom/awarding/expert/persist/expertTeam/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/expertTeam/testSelect.result.xml")
	public void testSelect() {
		ExpertTeam expertTeam = expertTeamService.select("e");
		Assert.assertEquals("name", expertTeam.getName());
		Assert.assertEquals("taskName", expertTeam.getTask().getName());
		Assert.assertEquals("name", expertTeam.getAward().getName());

		expertTeam.setName("name1");
		expertTeamService.update(expertTeam);
		Task task = taskService.select("t");
		expertTeamService.loadTask(task, new ExpertTeam());
		Assert.assertEquals(2, task.getExpertTeam().size());

		Award award = awardService.select("a");
		expertTeamService.loadAward(award, new ExpertTeam());
		Assert.assertEquals(1, award.getExpertTeam().size());

		ExpertTeam expertTeam3 = expertTeamService.select("e3");
		expertTeamService.delete(expertTeam3);
	}
}
