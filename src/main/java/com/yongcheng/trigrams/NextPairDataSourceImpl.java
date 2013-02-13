package com.yongcheng.trigrams;

public class NextPairDataSourceImpl implements IDataSource<Pair> {

	public final static String NEW_PARAGRAPH = "\r";

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

		if (this.wordsInCurrentLine == null || this.index == this.length - 2) {
			this.wordsInPreviousPreviousLine = this.wordsInPreviousLine;
			this.previousPreviousLine = this.previousLine;
			this.wordsInPreviousLine = this.wordsInCurrentLine;
			this.previousLine = this.currentLine;
			this.currentLine = this.nextLineDataSource.getNext();

			// --the end of the file
			if (this.currentLine == null) {
				return null;
			}

			// --the current Paragraph ended
			if (this.currentLine.trim().length() == 0) {
				return new Pair(this.wordsInPreviousLine[this.index] + " "
						+ this.wordsInPreviousLine[this.index + 1],
						this.NEW_PARAGRAPH);
			}

			// --the new Paragraph started
			if (this.previousLine != null
					&& this.previousLine.trim().length() == 0) {
				return new Pair(this.wordsInPreviousPreviousLine[this.index]
						+ " " + this.NEW_PARAGRAPH,
						this.wordsInCurrentLine[this.index]);
			}

			this.wordsInCurrentLine = this.currentLine.split(" ");
			this.index = -1;
			this.length = this.wordsInCurrentLine.length;
		}
		this.index++;

		// --only have one word in current line
		if (this.length == 1) {
			return new Pair(this.wordsInCurrentLine[this.index] + " "
					+ this.wordsInPreviousLine[this.index + 1],
					this.wordsInCurrentLine[this.index]);
		}

		if (this.index == this.length - 2) {
			return new Pair(this.wordsInCurrentLine[this.index] + " "
					+ this.wordsInCurrentLine[this.index + 1], null);
		}

		return new Pair(this.wordsInCurrentLine[this.index] + " "
				+ this.wordsInCurrentLine[this.index + 1],
				this.wordsInCurrentLine[this.index + 2]);
	}
}
