package com.yongcheng.trigrams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRawLineDataSourceImpl implements IDataSource<String> {

	private BufferedReader fileBufferedReader;

	public FileRawLineDataSourceImpl(String path) {

		if (path == null) {
			throw new RuntimeException("File path should be specified.");
		}

		File f = new File(path);

		f.setReadOnly();
		try {
			FileReader fr = new FileReader(f);
			this.fileBufferedReader = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File not existed:" + path);
		}

	}

	public String getNext() {
		String line = null;
		try {
			line = this.fileBufferedReader.readLine();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return line;
	}

}
