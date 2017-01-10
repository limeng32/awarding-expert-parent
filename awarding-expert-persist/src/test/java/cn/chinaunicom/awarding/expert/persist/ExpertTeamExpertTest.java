package cn.chinaunicom.awarding.expert.persist;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import cn.chinaunicom.awarding.expert.enums.ExpertTeamExpertStatus;

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
public class ExpertTeamExpertTest {

	@Autowired
	private ExpertService expertService;

	@Autowired
	private ExpertTeamService expertTeamService;

	@Autowired
	private ExpertTeamExpertService expertTeamExpertService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testSelect.result.xml")
	public void testSelect() {
		ExpertTeamExpert expertTeamExpert = expertTeamExpertService.select("e");
		Expert expert2 = expertService.select("e2");
		Assert.assertEquals("e", expertTeamExpert.getExpert().getId());
		Assert.assertEquals("name", expertTeamExpert.getExpertTeam().getName());
		Assert.assertEquals(ExpertTeamExpertStatus.leader,
				expertTeamExpert.getStatus());

		ExpertTeam expertTeam = expertTeamService.select("et");
		expertTeamExpertService.loadExpertTeam(expertTeam,
				new ExpertTeamExpert());
		Assert.assertEquals(1, expertTeam.getExpertTeamExpert().size());

		expertTeamExpert.setExpert(expert2);
		expertTeamExpert.setStatus(ExpertTeamExpertStatus.member);
		expertTeamExpertService.update(expertTeamExpert);
	}

	@Test
	@IfProfileValue(name = "CACHE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testCache.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testCache.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testCache.result.xml")
	public void testCache() {
		Expert e = new Expert();
		expertService.insert(e);
		ExpertTeamExpert ete = new ExpertTeamExpert();
		ete.setStatus(ExpertTeamExpertStatus.leader);
		ete.setExpert(e);
		expertTeamExpertService.insert(ete);

		ExpertTeamExpert expertTeamExpert = expertTeamExpertService.select(ete
				.getId());
		Assert.assertNotNull(expertTeamExpert.getExpert());
		expertService.delete(e);
		ExpertTeamExpert expertTeamExpert2 = expertTeamExpertService.select(ete
				.getId());
		Assert.assertNull(expertTeamExpert2.getExpert());
	}

	@Test
	@IfProfileValue(name = "CACHE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testCache2.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testCache2.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/expertTeamExpertTest/testCache2.result.xml")
	public void testCache2() {
		ExpertTeam et = new ExpertTeam();
		et.setName("name");
		expertTeamService.insert(et);
		ExpertTeamExpert ete = new ExpertTeamExpert();
		ete.setStatus(ExpertTeamExpertStatus.leader);
		ete.setExpertTeam(et);
		expertTeamExpertService.insert(ete);

		ExpertTeamExpert expertTeamExpert = expertTeamExpertService.select(ete
				.getId());
		Assert.assertNotNull(expertTeamExpert.getExpertTeam());
		expertTeamService.delete(et);
		ExpertTeamExpert expertTeamExpert2 = expertTeamExpertService.select(ete
				.getId());
		Assert.assertNull(expertTeamExpert2.getExpertTeam());
	}
}
