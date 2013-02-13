package com.yongcheng.trigrams;

import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileRawLineDataSourceWrapperImplTest {

	@InjectMocks
	private FileRawLineDataSourceWrapperImpl underTest = new FileRawLineDataSourceWrapperImpl(
			null);

	@Mock
	private IDataSource<String> nextLineDataSource;

	@Test
	public void test_that_getNext_returns_all_words_in_the_first_line_when_use_the_default_settings() {

		// --Arrange

		when(this.nextLineDataSource.getNext())
				.thenReturn("I wish I may I wish I might")
				.thenReturn(" I wish I   might").thenReturn(null);

		// --Action
		String actual = this.underTest.getNext();

		// --Assert
		Assert.assertEquals("I wish I may I wish I might", actual);
	}

	@Test
	public void test_that_getNext_returns_all_words_in_these_two_lines_when_set_MinimunWords_to_10() {

		// --Arrange
		underTest.setMinimunWords(10);
		when(this.nextLineDataSource.getNext())
				.thenReturn("I wish I may I wish I might")
				.thenReturn(" I wish I   might").thenReturn(null);

		// --Action
		String actual = this.underTest.getNext();

		// --Assert
		Assert.assertEquals("I wish I may I wish I might I wish I might",
				actual);
	}

	@Test
	public void test_that_getNext_returns_all_words_in_these_three_lines_use_the_default_settings() {

		// --Arrange
		when(this.nextLineDataSource.getNext()).thenReturn("I ")
				.thenReturn("    might").thenReturn("    I   ")
				.thenReturn(null);

		// --Action
		String actual = this.underTest.getNext();

		// --Assert
		Assert.assertEquals("I might I", actual);
	}

	@Test
	public void test_that_getNext_returns_all_words_splited_by_NEW_PARAGRAPH_in_these_lines_use_the_default_settings() {

		// --Arrange
		when(this.nextLineDataSource.getNext()).thenReturn("I ")
				.thenReturn("  ").thenReturn("    might").thenReturn("    ")
				.thenReturn("").thenReturn("    I   ").thenReturn(null);

		// --Action
		String actual = this.underTest.getNext();

		// --Assert
		Assert.assertEquals("I \r might", actual);
	}

	@Test
	public void test_that_getNext_returns_null_when_when_the_file_ended_and_using_the_default_settings() {

		// --Arrange
		when(this.nextLineDataSource.getNext()).thenReturn("I ")
				.thenReturn("    might").thenReturn("    I   ")
				.thenReturn(null);

		// --Action
		this.underTest.getNext();
		String actual = this.underTest.getNext();

		// --Assert
		Assert.assertEquals(null, actual);
	}
}
