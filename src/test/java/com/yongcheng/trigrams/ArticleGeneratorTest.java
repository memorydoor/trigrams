package com.yongcheng.trigrams;

import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArticleGeneratorTest {

	@InjectMocks
	private final ArticleGenerator underTest = new ArticleGenerator();

	@Mock
	private IPairWordsProcessor pairWordsProcessor;

	@Test
	public void test_that_generate_method_generated_a_right_report() {

		// --Arrange
		when(this.pairWordsProcessor.getRandomLeft()).thenReturn("I may");
		when(this.pairWordsProcessor.getRight("I may")).thenReturn("I");
		when(this.pairWordsProcessor.getRight("may I")).thenReturn("wish");
		when(this.pairWordsProcessor.getRight("I wish")).thenReturn("I");
		when(this.pairWordsProcessor.getRight("wish I")).thenReturn("may")
				.thenReturn("might");

		// --Action
		this.underTest.generate();

		// --Assert
		Assert.assertEquals("I may I wish I may I wish I might",
				this.underTest.getArticle());

	}
}
