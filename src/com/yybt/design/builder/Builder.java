package com.yybt.design.builder;
/**
 * ������ģʽ��Builder��
 * @author lx
 *
 */
public class Builder {
    public static void main(String[] args) {
         User user1=new User.UserBuilder().setAge(13).setId(1).setName("����").build();
         User user2=new User.UserBuilder().setAge(15).setId(2).setName("����").build();
         System.out.println("user1:"+user1);
         System.out.println("user2:"+user2);
         
	}
}
