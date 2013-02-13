package com.yongcheng.trigrams;

public class FileRawLineDataSourceWrapperImpl implements IDataSource<String> {

	private int minimunWords = 3;

	private IDataSource<String> nextLineDataSource;

	public FileRawLineDataSourceWrapperImpl(
			IDataSource<String> nextLineDataSource) {
		super();
		this.nextLineDataSource = nextLineDataSource;
	}

	public void setMinimunWords(int minimunWords) {
		if (minimunWords > this.minimunWords) {
			this.minimunWords = minimunWords;
		}
	}

	@Override
	public String getNext() {

		int numberOfWords = 0;
		StringBuffer sb = new StringBuffer();

		String nextLine = null;
		String lastWord = null;
		while (numberOfWords < minimunWords
				&& (nextLine = this.nextLineDataSource.getNext()) != null) {

			if (nextLine.trim().length() == 0
					&& !NextPairDataSourceImpl.NEW_PARAGRAPH.equals(lastWord)) {
				numberOfWords++;
				lastWord = NextPairDataSourceImpl.NEW_PARAGRAPH;
				sb.append(NextPairDataSourceImpl.NEW_PARAGRAPH_FLAG);
				sb.append(NextPairDataSourceImpl.SPACE);
			} else {
				for (String s : nextLine.split(NextPairDataSourceImpl.SPACE)) {
					if (s.length() == 0) {
						continue;
					}
					numberOfWords++;
					sb.append(s.trim());
					sb.append(NextPairDataSourceImpl.SPACE);
				}
			}
		}

		if (nextLine == null && sb.length() == 0) {
			return null;
		}

		return sb.toString().trim();
	}
}
