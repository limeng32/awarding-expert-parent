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
public class AcceptionBucketTest {

	@Autowired
	private AcceptionService accpetionService;

	@Autowired
	private AcceptionBucketService acceptionBucketService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/acceptionBucketTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/acceptionBucketTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/acceptionBucketTest/testSelect.result.xml")
	public void testSelect() {
		Acception acception = accpetionService.select("a");
		acceptionBucketService.loadAcception(acception, new AcceptionBucket());
		Assert.assertEquals(1, acception.getAcceptionBucket().size());

		AcceptionBucket acceptionBucket = acceptionBucketService.select("ab");
		Assert.assertEquals("old", acceptionBucket.getReview());
		acceptionBucket.setReview("new");
		Acception acception2 = accpetionService.select("a2");
		acceptionBucket.setAcception(acception2);
		acceptionBucketService.update(acceptionBucket);

		AcceptionBucket acceptionBucket2 = acceptionBucketService.select("ab2");
		acceptionBucketService.delete(acceptionBucket2);
	}

	@Test
	@IfProfileValue(name = "CACHE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/acceptionBucketTest/testCache.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/acceptionBucketTest/testCache.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/acceptionBucketTest/testCache.result.xml")
	public void testCache() {
		Acception a = new Acception();
		a.setName("old");
		accpetionService.insert(a);

		AcceptionBucket ab = new AcceptionBucket();
		ab.setReview("old");
		ab.setAcception(a);
		acceptionBucketService.insert(ab);

		AcceptionBucket acceptionBucket = acceptionBucketService.select(ab
				.getId());
		Assert.assertNotNull(acceptionBucket.getAcception());
		accpetionService.delete(a);
		AcceptionBucket acceptionBucket2 = acceptionBucketService.select(ab
				.getId());
		Assert.assertNull(acceptionBucket2.getAcception());
	}
}
