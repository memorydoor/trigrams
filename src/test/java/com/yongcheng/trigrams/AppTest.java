package com.yongcheng.trigrams;

import org.junit.Test;

public class AppTest {

	private App underTest = new App();

	@Test
	public void test_that_app_generate_a_right_article_using_simpleTestData_txt() {

		String path = AppTest.class.getResource("/simpleTestData.txt")
				.getPath();
		System.out.println(this.underTest.generateArticle(path));

	}

	@Test
	public void test_that_app_generate_a_right_article_using_TheVoiceInSingingByEmmaSeiler_txt() {

		String path = AppTest.class.getResource(
				"/TheVoiceInSingingByEmmaSeiler.txt").getPath();
		System.out.println(this.underTest.generateArticle(path));
	}
}
