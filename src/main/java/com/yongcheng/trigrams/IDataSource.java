package com.yongcheng.trigrams;

public interface IDataSource<T> {
	public T getNext();
}