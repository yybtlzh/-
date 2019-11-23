package com.yybt.design.builder;
/**
 * 建造者模式（Builder）
 * @author lx
 *
 */
public class Builder {
    public static void main(String[] args) {
         User user1=new User.UserBuilder().setAge(13).setId(1).setName("张三").build();
         User user2=new User.UserBuilder().setAge(15).setId(2).setName("李四").build();
         System.out.println("user1:"+user1);
         System.out.println("user2:"+user2);
         
	}
}
