package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

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

import cn.chinaunicom.awarding.account.persist.Account;
import cn.chinaunicom.awarding.account.persist.AccountService;

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
public class ExpertTest {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AcceptionService acceptionService;

	@Autowired
	private ExpertService expertService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testSelect.result.xml")
	public void testSelect() {
		Expert expert = expertService.select("e");
		Assert.assertEquals("a", expert.getAccount().getId());
		expertService.update(expert);
	}

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testLoad.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testLoad.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testLoad.result.xml")
	public void testLoad() {
		Expert expert = expertService.select("e");
		acceptionService.loadExpert(expert, new Acception());
		Assert.assertEquals(2, expert.getAcception().size());
	}

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testUpdate.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testUpdate.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testUpdate.result.xml")
	public void testUpdate() {
		Collection<Expert> experts = expertService.selectAll(new Expert());
		Assert.assertEquals(2, experts.size());
		Expert e = expertService.select("e1");
		expertService.delete(e);
		Collection<Expert> experts2 = expertService.selectAll(new Expert());
		Assert.assertEquals(1, experts2.size());
	}

	@Test
	@IfProfileValue(name = "CACHE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testCache.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testCache.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/Expert/testCache.result.xml")
	public void testCache() {
		Account a = new Account();
		a.setName("old");
		accountService.insert(a);

		Expert e = new Expert();
		e.setAccount(a);
		expertService.insert(e);

		Expert expert = expertService.select(e.getId());
		Assert.assertNotNull(expert.getAccount());
		accountService.delete(a);
		Expert expert2 = expertService.select(e.getId());
		Assert.assertNull(expert2.getAccount());
	}
}
