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

import cn.chinaunicom.awarding.expert.enums.AwardLevel;
import cn.chinaunicom.awarding.expert.enums.VoteStatus;
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
public class VoteTest {

	@Autowired
	private AcceptionService acceptionService;

	@Autowired
	private AwardService awardService;

	@Autowired
	private ExpertService expertService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private VoteService voteService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/voteTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/cn/chinaunicom/awarding/expert/persist/voteTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/voteTest/testSelect.result.xml")
	public void testSelect() {
		Vote vote = voteService.select("v");
		Assert.assertEquals("v", vote.getId());
		Assert.assertEquals(AwardLevel.first, vote.getLevel());
		Assert.assertEquals(VoteStatus.group, vote.getStatus());

		Task task = taskService.select("t");
		voteService.loadTask(task, new Vote());
		Assert.assertEquals(1, task.getVote().size());

		Expert expert = expertService.select("e");
		voteService.loadExpert(expert, new Vote());
		Assert.assertEquals(1, expert.getVote().size());

		Acception acception = acceptionService.select("a");
		voteService.loadAcception(acception, new Vote());
		Assert.assertEquals(1, acception.getVote().size());

		Award award = awardService.select("a");
		voteService.loadAward(award, new Vote());
		Assert.assertEquals(1, award.getVote().size());

		Vote vote2 = voteService.select("v2");
		voteService.delete(vote2);

		Task task2 = taskService.select("t2");
		vote.setTask(task2);
		voteService.update(vote);

		Expert expert2 = expertService.select("e2");
		vote.setExpert(expert2);
		voteService.update(vote);

		Acception acception2 = acceptionService.select("a2");
		vote.setAcception(acception2);
		voteService.update(vote);

		Award award2 = awardService.select("a2");
		vote.setAward(award2);
		vote.setLevel(AwardLevel.second);
		vote.setStatus(VoteStatus.central);
		voteService.update(vote);
	}
}
