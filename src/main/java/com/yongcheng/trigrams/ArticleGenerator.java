package com.yongcheng.trigrams;

public class ArticleGenerator {
	private IPairWordsProcessor pairWordsProcessor;

	private final StringBuilder article = new StringBuilder();

	public void generate() {
		this.article.setLength(0);

		String startingPoint = this.pairWordsProcessor.getRandomLeft();

		this.article.append(startingPoint);

		String left = startingPoint;
		String right = null;

		while ((right = this.pairWordsProcessor.getRight(left)) != null) {
			this.article.append(" ");
			this.article.append(right);

			left = left.substring(left.indexOf(" ") + 1) + " " + right;
		}
	}

	public String getArticle() {
		return this.article.toString();
	}

	public void setPairWordsProcessor(IPairWordsProcessor pairWordsProcessor) {
		this.pairWordsProcessor = pairWordsProcessor;
	}
}
