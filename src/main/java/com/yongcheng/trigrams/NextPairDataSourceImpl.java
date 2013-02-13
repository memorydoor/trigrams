package com.yongcheng.trigrams;

public class NextPairDataSourceImpl implements IDataSource<Pair> {

	public final static String NEW_PARAGRAPH = "\r";
	public final static String NEW_PARAGRAPH_FLAG = "NEW_PARAGRAPH";
	public final static String SPACE = " ";

	private IDataSource<String> nextLineDataSource;

	private String[] wordsInPreviousPreviousLine = null;
	private String previousPreviousLine = null;

	private String previousLine = null;
	private String[] wordsInPreviousLine = null;

	private String[] wordsInCurrentLine = null;
	private String currentLine = null;
	private int index = -1;
	private int length = 0;

	public NextPairDataSourceImpl(IDataSource<String> nextLineDataSource) {
		super();
		this.nextLineDataSource = nextLineDataSource;
	}

	public Pair getNext() {

		if (this.wordsInCurrentLine == null
				|| this.index == this.wordsInCurrentLine.length - 2
				|| wordsInCurrentLine.length == 1) {
			this.wordsInPreviousPreviousLine = this.wordsInPreviousLine;
			this.previousPreviousLine = this.previousLine;
			this.wordsInPreviousLine = this.wordsInCurrentLine;
			this.previousLine = this.currentLine;
			this.currentLine = this.nextLineDataSource.getNext();

			if (currentLine != null) {
				this.wordsInCurrentLine = currentLine.trim().length() > 0 ? this.currentLine
						.trim().split(" ")
						: new String[] { NEW_PARAGRAPH_FLAG };
			}

			// --the end of the file
			if (this.currentLine == null && this.previousLine != null
					&& this.wordsInPreviousLine.length > 1) {
				return new Pair(
						this.wordsInPreviousLine[wordsInPreviousLine.length - 2]
								+ " "
								+ this.wordsInPreviousLine[wordsInPreviousLine.length - 1],
						null);
			} else if (this.currentLine == null && this.previousLine != null
					&& this.wordsInPreviousLine.length == 1) {
				return new Pair(
						this.wordsInPreviousPreviousLine[wordsInPreviousPreviousLine.length - 1]
								+ " "
								+ this.wordsInPreviousLine[wordsInPreviousLine.length - 1],
						null);
			} else if (this.currentLine != null && this.previousLine != null
					&& this.wordsInPreviousLine.length == 1) {
				return new Pair(
						this.wordsInPreviousPreviousLine[wordsInPreviousPreviousLine.length - 1]
								+ " "
								+ this.wordsInPreviousLine[wordsInPreviousLine.length - 1],
						this.wordsInCurrentLine[0]);
			} else if (this.currentLine == null && this.previousLine == null) {
				return null;
			}

			// --the current Paragraph ended
			if (this.currentLine.trim().length() == 0) {
				return new Pair(this.wordsInPreviousLine[this.index] + " "
						+ this.wordsInPreviousLine[this.index + 1],
						this.NEW_PARAGRAPH_FLAG);
			}

			// --the new Paragraph started
			if (this.previousLine != null
					&& this.previousLine.trim().length() == 0) {
				return new Pair(this.wordsInPreviousPreviousLine[this.index]
						+ " " + this.NEW_PARAGRAPH_FLAG,
						this.wordsInCurrentLine[this.index]);
			}

			this.index = -1;
			this.length = this.wordsInCurrentLine.length;
		}
		this.index++;

		// --only have one word in current line
		if (this.wordsInCurrentLine.length == 1) {
			return new Pair(
					this.wordsInPreviousLine[wordsInPreviousLine.length - 2]
							+ " "
							+ this.wordsInPreviousLine[wordsInPreviousLine.length - 1],
					this.wordsInCurrentLine[0]);
		}

		// --the current line processed completely, need to read a new line
		if (this.index == this.length - 2) {
			return getNext();
		}

		return new Pair(this.wordsInCurrentLine[this.index] + " "
				+ this.wordsInCurrentLine[this.index + 1],
				this.wordsInCurrentLine[this.index + 2]);
	}
}
