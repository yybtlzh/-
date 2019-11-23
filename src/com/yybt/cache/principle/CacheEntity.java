package com.yybt.cache.principle;

import java.io.Serializable;

/**
 * ����������
 * @author lx
 *
 */
public class CacheEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long beginTime;// ���濪ʼʱ��
	
	private boolean isForever = false;// �Ƿ�־�
	
	private int durableTime;// ����ʱ��

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public boolean isForever() {
		return isForever;
	}

	public void setForever(boolean isForever) {
		this.isForever = isForever;
	}

	public int getDurableTime() {
		return durableTime;
	}

	public void setDurableTime(int durableTime) {
		this.durableTime = durableTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
