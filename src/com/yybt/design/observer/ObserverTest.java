package com.yybt.design.observer;

/**
  * @ClassName: Test
  * @Description: 测试下
  * @date 2018年12月13日
 **/
public class ObserverTest {
	
	public static void main(String[] args) {
		WeatherServer server = new WeatherServer();
        System.out.println("定义三位观察者");
        Observer  missZhang = new User("张三");
        Observer missLi = new User("李四");
        Observer missWang = new User("王五");
        server.registerObserver(missZhang);
        server.registerObserver(missLi);
        server.registerObserver(missWang);
        server.setMessage("今天天气晴");
        System.out.println("注销观察者王五");
        server.removeObserver(missWang);
        server.setMessage("天气预报出差错，应该是晴转阴");
        System.out.println("注册观察者赵六");
        Observer missZhao = new User("赵六");
        server.registerObserver(missZhao);
        server.setMessage("真的是抱歉了，直接下的倾盆大雨");
    }
}
