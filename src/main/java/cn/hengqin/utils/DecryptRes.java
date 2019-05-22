package cn.hengqin.utils;

public class DecryptRes {
	private int res; //������

	private String srcString;//ԭʼ�����

	private String digest;//CHA-1ԭʼ�����

	public DecryptRes(int res, String srcString, String digest) {
		super();
		this.res = res;
		this.srcString = srcString;
		this.digest = digest;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public String getSrcString() {
		return srcString;
	}

	public void setSrcString(String srcString) {
		this.srcString = srcString;
	}

}
