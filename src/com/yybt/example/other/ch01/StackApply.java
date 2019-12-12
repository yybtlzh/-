package com.yybt.example.other.ch01;

import java.util.Arrays;

import com.yybt.datastructure.stack.MyStack;
import com.yybt.utils.StringUtils;

/**
 * ջӦ��֮������׺���ʽ����������ת���õ�String���������ֻ֧�ָ�λ�����ǿ����ڴ˻����Ͻ��н�һ����չ
 * ������ͼ��ʱ��Ҳ�õ���ջ���ο� com.yybt.datastructure.graph.Graph
 * @author liuzehong
 *
 */
public class StackApply {
    
	/**
	 * ��׺ת��׺
	 * @param infix
	 * @return
	 */
	public static String[] infixToSuffix(String infix) {
		MyStack<String> stack = new MyStack<>(infix.length());
		String[] suffix = new String[infix.length()];
		int s=0;
		int length = infix.length();
		for (int i = 0; i < length; i++) {
			String temp;
			char n = infix.charAt(i);
			String c = String.valueOf(n);
			switch (c) {
			// ���Կո�
			case " ":
				break;
			// ����'('��push��ջ
			case "(":
				stack.push(c);
				break;
			// ����'+''-'����ջ������������������͵����������
			case "+":
			case "-":
				while (!stack.isEmpty()) {
					temp = stack.pop();
					if ("(".equals(temp)) {
						stack.push(")");
						break;
					}else {
						suffix[s++] =temp;
					}
					
				}
				stack.push(c);
				break;
			// ����'*''/'����ջ�����г˳�������������͵����������
			case "*":
			case "/":
				while (!stack.isEmpty()) {
					temp = stack.pop();
					if ("(".equals(temp) ||"+".equals(temp) ||"-".equals(temp)) {
						stack.push(temp);
						break;
					} else {
						suffix[s++] =temp;
					}
				}
				stack.push(c);
				break;
			//���������ţ�������ջ���ĵ�һ������������������ȫ�����ε�����
			//����������к��ٶ���������
			case ")":
				while (!stack.isEmpty()) {
					temp = stack.pop();
					if ("(".equals(temp))
						break;
					else
						if (!")".equals(temp)) {
							suffix[s++] =temp;
						}
					
				}
				// suffix += " ";
				break;
			default:
				suffix[s++] = c;
			}
		}
		while (!stack.isEmpty())
			suffix[s++] =stack.pop();
		System.out.println(Arrays.toString(suffix));
		return suffix;
	}
	
	/**
	 * ��׺���ʽ����
	 * @param str
	 * @return
	 */
	public static int  compute(String[] str) {
		MyStack<Integer> stack = new MyStack<>(str.length);
		int length =0;
		for (int i = 0; i < str.length; i++) {
			if (StringUtils.isEmpty(str[i])) 
				break;
			length++;
		}
		//������ɨ����ʽ
		Integer m1;
		Integer m2;
		int result;
	    for (int i = 0; i <length; i++) {
			String c = str[i];
			//������������ʾ���Լ��㣬��ʱȡ��ջ��������Ԫ�ؽ��в�����Ȼ���ٴν����ѹ��ջ
			switch (c) {
			case "+":
				m1 = stack.pop();
				m2 = stack.pop();
				result=m2+m1;
				stack.push(result);
				break;
			case "-":
				m1 = stack.pop();
				m2 = stack.pop();
				result=m2-m1;
				stack.push(result);
				break;
			case "*":
				m1 = stack.pop();
				m2 = stack.pop();
				result=m2*m1;
				stack.push(result);
				break;
			case "/":
				m1 = stack.pop();
				m2 = stack.pop();
				result=m2/m1;
				stack.push(result);
				break;
			default://�������־ͽ���ѹ��ջ
				stack.push(Integer.valueOf(c));
				break;
			}
		}
	    //���ջ�������һ��Ԫ�أ���Ԫ�ؾ������н��
		return stack.pop();
		
	}
	
	

	public static void main(String[] args) {
		String str = "2*(2+3)*4-9";
		String[] result = infixToSuffix(str);
		String rt="";
		for (int i = 0; i < result.length; i++) {
			if(result[i]==null)
				break;
			rt+=" "+result[i];
		}
		System.out.println("��׺ת��׺��"+rt);
		System.out.println("��������"+compute(result));
	}

}
