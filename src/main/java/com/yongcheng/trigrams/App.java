package com.yongcheng.trigrams;

public class App {
	public static void main(String[] args) {

		App app = new App();

		String path = "C:\\sandbox\\git\\Java\\trigrams\\src\\main\\java\\com\\yongcheng\\trigrams\\simpleTestData.txt";
		System.out.println(app.generateArticle(path));

		path = "C:\\sandbox\\git\\Java\\trigrams\\src\\main\\java\\com\\yongcheng\\trigrams\\TheVoiceInSingingByEmmaSeiler.txt";

		System.out.println(app.generateArticle(path));
	}

	public String generateArticle(String path) {
		IDataSource<Pair> nextWordDataSouce = new NextPairDataSourceImpl(
				new FileRawLineDataSourceImpl(path));
		IPairWordsProcessor pairWordsProcessor = new PairWordsProcessorImpl(
				nextWordDataSouce);

		pairWordsProcessor.process();

		ArticleGenerator articleGenerator = new ArticleGenerator();

		articleGenerator.setPairWordsProcessor(pairWordsProcessor);

		articleGenerator.generate();

		return articleGenerator.getArticle();
	}
}
