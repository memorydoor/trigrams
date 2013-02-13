package com.yongcheng.trigrams;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class FileRawLineDataSourceImplTest {

	private FileRawLineDataSourceImpl underTest;

	@Test
	public void test_that_constructor_throw_runTimeException_when_filePath_is_null() {

		try {
			this.underTest = new FileRawLineDataSourceImpl(null);
		} catch (RuntimeException e) {
			Assert.assertEquals("File path should be specified.",
					e.getMessage());
		}

	}

	@Test
	public void test_that_constructor_throw_runTimeException_when_filePath_is_not_exsit() {

		try {
			this.underTest = new FileRawLineDataSourceImpl(
					"some_where_I_don't_know_@#");
		} catch (RuntimeException e) {
			Assert.assertEquals("File not existed:"
					+ "some_where_I_don't_know_@#", e.getMessage());
		}

	}

	@Test
	public void test_that_constructor_work_well_when_filePath_is_exsit()
			throws IOException {

		File file = new File(FileRawLineDataSourceImpl.class.getResource(
				"./FileRawLineDataSourceImplTest.class").getPath());

		// --Act
		this.underTest = new FileRawLineDataSourceImpl(file.getAbsolutePath());
	}

	@Test
	public void test_that_getNext_return_something_at_first() {

		File file = new File(FileRawLineDataSourceImpl.class.getResource(
				"./FileRawLineDataSourceImplTest.class").getPath());

		// --Act
		this.underTest = new FileRawLineDataSourceImpl(file.getAbsolutePath());

		Assert.assertNotNull(this.underTest.getNext());
	}

	@Test
	public void test_that_getNext_return_null_in_the_end() {

		File file = new File(FileRawLineDataSourceImpl.class.getResource(
				"./FileRawLineDataSourceImplTest.class").getPath());

		// --Act
		this.underTest = new FileRawLineDataSourceImpl(file.getAbsolutePath());

		String line = null;

		do {
			line = this.underTest.getNext();
		} while (line != null);

		Assert.assertNull(this.underTest.getNext());
	}
}
