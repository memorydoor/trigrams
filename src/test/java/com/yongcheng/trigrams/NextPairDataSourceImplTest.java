package com.yongcheng.trigrams;

import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NextPairDataSourceImplTest {

	@InjectMocks
	private final NextPairDataSourceImpl underTest = new NextPairDataSourceImpl(
			null);

	@Mock
	private IDataSource<String> nextLineDataSourceWrapper;

	@Test
	public void test_that_getNext_return_a_pair_with_I_wish_I_at_fisrt_time() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn(
				"I wish I may I wish I might").thenReturn(null);

		// --Action
		Pair pair = this.underTest.getNext();

		// --Assert

		Assert.assertEquals(new Pair("I wish", "I"), pair);
	}

	@Test
	public void test_that_getNext_return_a_pair_with_I_might_null_after_called_seven_times() {
		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn(
				"I wish I may I wish I might").thenReturn(null);

		// --Action
		Pair result = null;
		for (int i = 0; i < 7; i++) {
			result = this.underTest.getNext();
		}

		// --Assert
		Assert.assertEquals(new Pair("I might", null), result);
	}

	@Test
	public void test_that_getNext_return_Paragraph_holder_when_encounter_an_empty_line() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext())
				.thenReturn("I wish I may I wish I might").thenReturn("")
				.thenReturn(null);

		// --Action
		Pair result = null;
		for (int i = 0; i < 8; i++) {
			result = this.underTest.getNext();
		}

		// --Assert

		Assert.assertEquals(new Pair("I might",
				NextPairDataSourceImpl.NEW_PARAGRAPH_FLAG), result);
	}

	@Test
	public void test_that_getNext_return_null_after_called_eight_times() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn(
				"I wish I may I wish I might").thenReturn(null);

		// --Action
		Pair result = null;
		for (int i = 0; i < 8; i++) {
			result = this.underTest.getNext();
		}

		// --Assert

		Assert.assertEquals(null, result);
	}

	@Test
	public void test_that_getNext_return_null_after_called_ten_times() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn(
				"I wish I may I wish I might").thenReturn(null);

		// --Action
		Pair result = null;
		for (int i = 0; i < 10; i++) {
			result = this.underTest.getNext();
		}

		// --Assert

		Assert.assertEquals(null, result);
	}

	@Test
	public void test_that_getNext_return_a_pair_with_null_on_right() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn("I wish")
				.thenReturn(null);

		// --Action
		Pair result = this.underTest.getNext();

		// --Assert

		Assert.assertEquals(new Pair("I wish", null), result);
	}

	@Test
	public void test_that_getNext_return_a_pair_with_null_on_right_at_the_third_time() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn("I wish I")
				.thenReturn("might").thenReturn(null);

		// --Action
		Pair result = null;
		result = this.underTest.getNext();
		result = this.underTest.getNext();
		result = this.underTest.getNext();

		// --Assert
		Assert.assertEquals(new Pair("I might", null), result);
	}

}
