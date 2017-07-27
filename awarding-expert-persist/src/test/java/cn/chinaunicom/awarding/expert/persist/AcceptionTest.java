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

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.FlatXmlDataSetLoader;

import cn.chinaunicom.awarding.project.persist.Project;
import cn.chinaunicom.awarding.project.persist.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:expert-persist-test.xml", "classpath:project-service.xml",
		"classpath:account-service.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class)
public class AcceptionTest {

	@Autowired
	private AcceptionService acceptionService;

	@Autowired
	private ExpertService expertService;

	@Autowired
	private ProjectService projectService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/acceptionTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/cn/chinaunicom/awarding/expert/persist/acceptionTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/acceptionTest/testSelect.result.xml")
	public void testSelect() {
		Expert expert = expertService.select("e");
		acceptionService.loadExpert(expert, new Acception());
		Assert.assertEquals(1, expert.getAcception().size());

		Project project = projectService.select("p");
		acceptionService.loadProject(project, new Acception());
		Assert.assertEquals(1, project.getAcception().size());

		Acception acception = acceptionService.select("a");
		Assert.assertEquals("old", acception.getName());
		acception.setName("new");
		acception.setExpert(expertService.select("e2"));
		acception.setProject(projectService.select("p2"));
		acceptionService.update(acception);

		Acception acception2 = acceptionService.select("a2");
		acceptionService.delete(acception2);
	}

	@Test
	@IfProfileValue(name = "CACHE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/acceptionTest/testCache.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/cn/chinaunicom/awarding/expert/persist/acceptionTest/testCache.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/acceptionTest/testCache.result.xml")
	public void testCache() {
		Expert e = new Expert();
		expertService.insert(e);

		Project p = new Project();
		projectService.insert(p);

		Acception a = new Acception();
		a.setName("old");
		a.setExpert(e);
		a.setProject(p);
		acceptionService.insert(a);

		Acception acception = acceptionService.select(a.getId());
		Assert.assertEquals("old", acception.getName());
		expertService.delete(e);
		projectService.delete(p);
		Acception acception2 = acceptionService.select(a.getId());
		Assert.assertNull(acception2.getExpert());
		Assert.assertNull(acception2.getProject());
	}
}
