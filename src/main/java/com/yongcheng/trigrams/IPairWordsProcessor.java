package com.yongcheng.trigrams;

public interface IPairWordsProcessor {

	public void process();

	public String getRandomLeft();

	public String getRight(String left);

}