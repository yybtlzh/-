/**
 * @Title: User.java
 * @Package com.lzh.test4
 */
package com.yybt.design.builder;

/**
  * @ClassName: User
  * @author liuzehong
 **/
public class User {
	
	private  int  id;
	private String name;
	private int age;
	private String sex;
	    
	   static class UserBuilder{
	        String name = null ;
	        int id =0 ;
	        String sex = null ;
	        int age = -1 ;
	        
	        public UserBuilder setId(int id) {
	            this.id = id;
	            return  this ;
	        }

	        public UserBuilder setSex(String sex) {
	            this.sex = sex;
	            return  this ;
	        }
	        
	        public UserBuilder setName(String name) {
	            this.name = name;
	            return  this ;
	        }


	        public UserBuilder setAge(int age) {
	            this.age = age;
	            return  this ;
	        }

	        public User build() {
	            return new User(this);
	        }
	    }
	    
	    public User(UserBuilder builder){
	    	
	    	this.id = builder.id;
	    	this.name = builder.name;
	        this.age = builder.age;
	        this.sex = builder.sex ;
	    }
		
		@Override
		public String toString() {
			return "User [name=" + name + ", id=" + id + ", sex=" + sex + ", age=" + age 
					+ "]";
		}
}
