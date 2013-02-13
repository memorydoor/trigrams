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
	public void test_that_getNext_return_Paragraph_holder_on_the_right_at_first_time() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn("I might")
				.thenReturn("").thenReturn(null);

		// --Action
		Pair result = this.underTest.getNext();

		// --Assert

		Assert.assertEquals(new Pair("I might",
				NextPairDataSourceImpl.NEW_PARAGRAPH_FLAG), result);
	}

	@Test
	public void test_that_getNext_return_Paragraph_holder_and_null_on_the_right_at_second_time() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn("I might")
				.thenReturn("").thenReturn(null);

		// --Action
		this.underTest.getNext();
		Pair result = this.underTest.getNext();

		// --Assert

		Assert.assertEquals(new Pair("might "
				+ NextPairDataSourceImpl.NEW_PARAGRAPH_FLAG, null), result);
	}

	@Test
	public void test_that_getNext_return_I_new_paragraph_flag_I_on_the_right_at_third_time() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn("I might I")
				.thenReturn("").thenReturn("I might I");

		// --Action
		Pair result =this.underTest.getNext();
		result = this.underTest.getNext();
		result = this.underTest.getNext();

		// --Assert

		Assert.assertEquals(new Pair("I "
				+ NextPairDataSourceImpl.NEW_PARAGRAPH_FLAG, "I"), result);
	}

	@Test
	public void test_that_getNext_return_null_at_the_third_time() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn("I might")
				.thenReturn("").thenReturn(null);

		// --Action
		this.underTest.getNext();
		this.underTest.getNext();
		Pair result = this.underTest.getNext();

		// --Assert

		Assert.assertEquals(null, result);
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
	public void test_that_getNext_return_a_pair_I_wish_I_at_the_first_time() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn("I wish I")
				.thenReturn("might").thenReturn(null);

		// --Action
		Pair result = null;
		result = this.underTest.getNext();

		// --Assert
		Assert.assertEquals(new Pair("I wish", "I"), result);
	}

	@Test
	public void test_that_getNext_return_a_pair_wish_I_might_at_the_first_time() {

		// --Arrange
		when(this.nextLineDataSourceWrapper.getNext()).thenReturn("I wish I")
				.thenReturn("might").thenReturn(null);

		// --Action
		Pair result = null;
		result = this.underTest.getNext();
		result = this.underTest.getNext();

		// --Assert
		Assert.assertEquals(new Pair("wish I", "might"), result);
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
