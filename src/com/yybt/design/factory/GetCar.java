package com.yybt.design.factory;


/**
 * 工厂模式
 * @author lx
 *
 */
public class GetCar {
	
	public Car get(String name){
		
        Car x = null ;
        if ( name.equals("CarA")) {
            x = StaticFactory.getA();
        }else if ( name.equals("CarB")){
            x = StaticFactory.getB();
        }else if ( name.equals("CarC")){
            x = StaticFactory.getC();
        }
        
        return x;
        
        
    }
	
	/**
	 * 利用反射，让代码变得简洁
	 * @param name
	 * @return
	 */
    public Car get2(String name){
    	 try {
             Class cls = Class.forName("com.yybt.design.factory."+name);
             try {
               //得到一个类的实例
               return  (Car) cls.newInstance();
             } catch (Exception e) {
             	//没必要抛异常，看着也不舒服
             }  
         } catch (ClassNotFoundException e) {}
		return null;
        
        
    }
    
    public static void main(String[] args) {
    	GetCar getCar=new GetCar();
    	System.out.println(getCar.get("CarA"));
		System.out.println(getCar.get2("CarA"));
	}

}
