package com.yongcheng.trigrams;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class PairWordsProcessorImpl implements IPairWordsProcessor {

	private final IDataSource<Pair> nextPairDataSource;

	private Map<String, LinkedHashSet<String>> data;

	public PairWordsProcessorImpl(IDataSource<Pair> nextPairDataSource) {
		super();
		this.nextPairDataSource = nextPairDataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yongcheng.trigrams.IPairWordsProcessor#process()
	 */
	public void process() {
		this.data = new HashMap<String, LinkedHashSet<String>>();

		Pair pair = null;

		while ((pair = this.nextPairDataSource.getNext()) != null) {

			LinkedHashSet<String> followingWords = this.data
					.get(pair.getLeft());
			if (followingWords == null) {
				followingWords = new LinkedHashSet<String>();
				this.data.put(pair.getLeft(), followingWords);
			}
			followingWords.add(pair.getRigth());
		}
	}

	public Map<String, LinkedHashSet<String>> getData() {
		return this.data;
	}

	@SuppressWarnings("unchecked")
	public String getRandomLeft() {
		return (String) getRandomElementFromSet((Set) this.data.keySet());
	}

	@SuppressWarnings("unchecked")
	public String getRight(String left) {
		if (left == null) {
			return null;
		}

		LinkedHashSet<String> followingWords = this.data.get(left);

		if (followingWords == null) {
			return null;
		}

		return (String) getRandomElementFromSet((Set) followingWords);
	}

	private Object getRandomElementFromSet(Set<Object> followingWords) {
		int size = followingWords.size();
		int ramdomIndex = getRandomIndex(size);

		int index = -1;

		Object result = null;
		for (Object object : followingWords) {
			result = object;
			index++;
			if (index == ramdomIndex) {
				break;
			}
		}
		return result;
	}

	private int getRandomIndex(int size) {
		double randomDouble = Math.random();
		int ramdomIndex = (int) (randomDouble * (size + 1));
		return ramdomIndex;
	}
}
