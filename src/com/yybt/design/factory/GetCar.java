package com.yybt.design.factory;


/**
 * ����ģʽ
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
	 * ���÷��䣬�ô����ü��
	 * @param name
	 * @return
	 */
    public Car get2(String name){
    	 try {
             Class cls = Class.forName("com.yybt.design.factory."+name);
             try {
               //�õ�һ�����ʵ��
               return  (Car) cls.newInstance();
             } catch (Exception e) {
             	//û��Ҫ���쳣������Ҳ�����
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
