package com.yongcheng.trigrams;

import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PairWordsProcessorImplTest {

	@InjectMocks
	private PairWordsProcessorImpl underTest;

	@Mock
	private IDataSource<Pair> pairDataSource;

	@Before
	public void setup() {
		when(this.pairDataSource.getNext()).thenReturn(new Pair("I wish", "I"))
				.thenReturn(new Pair("wish I", "may"))
				.thenReturn(new Pair("I may", "I"))
				.thenReturn(new Pair("may I", "wish"))
				.thenReturn(new Pair("I wish", "I"))
				.thenReturn(new Pair("wish I", "might")).thenReturn(null);
	}

	@Test
	public void test_that_process_the_pair_data_correctly() {

		// --Action
		this.underTest.process();

		// --Assert
		Assert.assertEquals(
				"{I may=[I], I wish=[I], wish I=[may, might], may I=[wish]}",
				this.underTest.getData().toString());
	}

	@Test
	public void test_that_getRandomLeft_return_the_result_is_within__I_may__I_wish__wish_I__May_I() {
		// --Arrange
		this.underTest.process();

		// --Action
		String left = this.underTest.getRandomLeft();

		Assert.assertTrue(
				"the return left should be within the range {I may,I wish,wish I,may I}",
				"I may,I wish,wish I,may I".contains(left));

	}

	@Test
	public void test_that_getRight_return_wish_when_the_left_is_may_I() {
		// --Arrange
		this.underTest.process();

		// --Action
		String right = this.underTest.getRight("may I");

		// --Assert
		Assert.assertEquals("wish", right);
	}

	@Test
	public void test_that_getRight_return_right_when_the_left_is_wish_I_in_10_times() {
		// --Arrange
		this.underTest.process();

		// --Action
		String right = null;

		for (int i = 0; i < 10; i++) {
			right = this.underTest.getRight("wish I");
			if (right.equals("may")) {
				break;
			}

		}

		// --Assert
		Assert.assertEquals("may", right);
	}

	@Test
	public void test_that_getRight_return_might_when_the_left_is_wish_I_in_10_times() {
		// --Arrange
		this.underTest.process();

		// --Action
		String right = null;

		for (int i = 0; i < 10; i++) {
			right = this.underTest.getRight("wish I");
			if (right.equals("might")) {
				break;
			}

		}

		// --Assert
		Assert.assertEquals("might", right);
	}
}
