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
public class NoticeBucketTest {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private NoticeBucketService noticeBucketService;

	@Test
	@IfProfileValue(name = "VOLATILE", value = "true")
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/cn/chinaunicom/awarding/expert/persist/noticeBucketTest/testSelect.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/cn/chinaunicom/awarding/expert/persist/noticeBucketTest/testSelect.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/cn/chinaunicom/awarding/expert/persist/noticeBucketTest/testSelect.result.xml")
	public void testSelect() {
		Notice notice = noticeService.select("n");
		noticeBucketService.loadNotice(notice, new NoticeBucket());
		Assert.assertEquals(1, notice.getNoticeBucket().size());

		NoticeBucket noticeBucket = noticeBucketService.select("nb");
		Notice notice2 = noticeService.select("n2");
		noticeBucket.setNotice(notice2);
		noticeBucketService.update(noticeBucket);

		NoticeBucket noticeBucket2 = noticeBucketService.select("nb2");
		noticeBucketService.delete(noticeBucket2);
	}
}
