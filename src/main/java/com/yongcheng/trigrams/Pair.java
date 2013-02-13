package com.yongcheng.trigrams;

import java.io.Serializable;

public class Pair implements Serializable {

	private static final long serialVersionUID = -1986857497107432351L;
	private String left = null;
	private String rigth = null;

	public Pair() {
	}

	public Pair(String left, String rigth) {
		super();
		this.left = left;
		this.rigth = rigth;
	}

	public String getLeft() {
		return this.left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRigth() {
		return this.rigth;
	}

	public void setRigth(String rigth) {
		this.rigth = rigth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.left == null) ? 0 : this.left.hashCode());
		result = prime * result
				+ ((this.rigth == null) ? 0 : this.rigth.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (this.left == null) {
			if (other.left != null)
				return false;
		} else if (!this.left.equals(other.left))
			return false;
		if (this.rigth == null) {
			if (other.rigth != null)
				return false;
		} else if (!this.rigth.equals(other.rigth))
			return false;
		return true;
	}

}
