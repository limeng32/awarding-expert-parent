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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:expert-persist-test.xml",
		"classpath:project-service.xml", "classpath:account-service.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class)
public class AcceptionTest {

	@Autowired
	private AcceptionService acceptionService;

	@Autowired
	private ExpertService expertService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/AcceptionTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/AcceptionTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/AcceptionTest/testSelect.result.xml")
	public void testSelect() {
		Acception acception = acceptionService.select("a");
		Assert.assertEquals("e", acception.getExpert().getId());
		Assert.assertEquals("p", acception.getProject().getId());
		acception.setName("new");
		acceptionService.update(acception);
	}

}
