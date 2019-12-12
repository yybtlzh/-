package com.yybt.example.other.ch01;

import java.util.Arrays;

import com.yybt.datastructure.stack.MyStack;
import com.yybt.utils.StringUtils;

/**
 * 栈应用之计算中缀表达式，由于这里转换用的String，因此数字只支持个位，你们可以在此基础上进行进一步拓展
 * 在讨论图的时候也用到过栈，参考 com.yybt.datastructure.graph.Graph
 * @author liuzehong
 *
 */
public class StackApply {
    
	/**
	 * 中缀转后缀
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
			// 忽略空格
			case " ":
				break;
			// 碰到'('，push到栈
			case "(":
				stack.push(c);
				break;
			// 碰到'+''-'，将栈中所有运算符弹出，送到输出队列中
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
			// 碰到'*''/'，将栈中所有乘除运算符弹出，送到输出队列中
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
			//碰到右括号，将靠近栈顶的第一个左括号上面的运算符全部依次弹出，
			//送至输出队列后，再丢弃左括号
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
	 * 后缀表达式计算
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
		//从左到右扫描表达式
		Integer m1;
		Integer m2;
		int result;
	    for (int i = 0; i <length; i++) {
			String c = str[i];
			//遇到操作符表示可以计算，这时取出栈顶的两个元素进行操作，然后再次将结果压入栈
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
			default://遇到数字就将其压入栈
				stack.push(Integer.valueOf(c));
				break;
			}
		}
	    //最后栈里会留下一个元素，该元素就是运行结果
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
		System.out.println("中缀转后缀："+rt);
		System.out.println("计算结果："+compute(result));
	}

}
