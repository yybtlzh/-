package com.yybt.design.Clonemode;

/**

 * @Description: ��¡ģʽ
 * @author liuzehong
 *
 */
public class ProtypeTest implements Cloneable {
	
    private String name;
	private String id;
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	          return super.clone();
	}
	public static void main ( String[] args){
		ProtypeTest pro = new ProtypeTest();
		pro.setId("1");
		pro.setName("����");
	  try {
		ProtypeTest pro1 = (ProtypeTest)pro.clone();
		System.out.println("id:"+pro1.getId()+" name��"+pro1.getName());
	} catch (CloneNotSupportedException e) {
		e.printStackTrace();
		}
	}
}
