package com.yybt.example.thread.ch01;

/**
 * ģ���վ�ര����Ʊ
 * @author liuzehong
 *
 */
public class Window extends Thread {

	// ͨ�����췽�����߳����ָ�ֵ
	public Window(String name) {
		super(name);
	}

	// Ϊ�˱���Ʊ����һ�£�Ʊ��Ҫ��̬
	static int tick = 200;

	// ����һ����̬Կ��
	static Object key = "key";// ֵ�������

	// ��дrun������ʵ����Ʊ����
	@Override
	public void run() {
		while (tick > 0) {
			synchronized (key) {// �������Ҫ������ʹ��һ������
				// ��ȥ���˻��Կ���������ϣ�������Ű�Կ�����ó���
				if (tick > 0) {
					System.out.println(getName() + "�����˵�" + tick-- + "��Ʊ");
				} else {
					System.out.println("Ʊ������");
				}
			}
			try {
				sleep(1000);// ��Ϣһ��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		
		for (int i = 0; i < 3; i++) {
			Window window = new Window("����"+i);
			window.start();
		}

	}

}