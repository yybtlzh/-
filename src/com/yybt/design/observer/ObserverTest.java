package com.yybt.design.observer;

/**
  * @ClassName: Test
  * @Description: ������
  * @date 2018��12��13��
 **/
public class ObserverTest {
	
	public static void main(String[] args) {
		WeatherServer server = new WeatherServer();
        System.out.println("������λ�۲���");
        Observer  missZhang = new User("����");
        Observer missLi = new User("����");
        Observer missWang = new User("����");
        server.registerObserver(missZhang);
        server.registerObserver(missLi);
        server.registerObserver(missWang);
        server.setMessage("����������");
        System.out.println("ע���۲�������");
        server.removeObserver(missWang);
        server.setMessage("����Ԥ�������Ӧ������ת��");
        System.out.println("ע��۲�������");
        Observer missZhao = new User("����");
        server.registerObserver(missZhao);
        server.setMessage("����Ǳ�Ǹ�ˣ�ֱ���µ��������");
    }
}
