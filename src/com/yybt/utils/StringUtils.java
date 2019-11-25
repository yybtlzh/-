package com.yybt.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

//import org.apache.commons.lang.StringUtils;
/**
 * @ClassName: StringUtils
 * @author liuzehong
 * @date 2016��12��4�� ����9:42:08
 *
 **/
public class StringUtils {
	
	private static final  String  STR_NO="[0-9]*";
	
	private static final  String ISO8859_1="ISO8859_1";
	
	private static final  String  GBK="GBK";
	
	private static final  String  LEAP_YEAR_DATE= "((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	
	private static final  String  LEAP_YEAR_TIME= "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))";
    ;
	
    /**
     * ����˽�л�����ֹ���ִ���
     */
	private  StringUtils() {
		
	}

	/**
	 * �ж��Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * �ж��Ƿ���ڿ�
	 * @param str
	 * @return
	 */
	public static boolean existEmpty(String... str) {
		if (str != null) {
			for (int i = 0; i < str.length; i++) {
				if (isEmpty(str[i])) 
					return true;
			}
		}
		return false;
	}

	/**
	 * ����ת�����ֽ�����
	 * 
	 * @param pIns
	 * @return
	 */
	public static byte[] InputStreamToBytes(InputStream pIns) {
		ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();

		try {
			byte[] tBytes = new byte[8 * 1024];
			for (int tReadSize; -1 != (tReadSize = pIns.read(tBytes));) {
				mByteArrayOutputStream.write(tBytes, 0, tReadSize);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				pIns.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return mByteArrayOutputStream.toByteArray();
	}

	/**
	 * �ж��ַ����ǲ����������
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (isEmpty(str)) 
			return false;
		Pattern pattern = Pattern.compile(STR_NO);
		return pattern.matcher(str.replace(".", "")).matches();
	}

	/**
	 * ����ĸ��д
	 * 
	 * @param str
	 * @return
	 */
	public static String upperCase(String str) {
		return !isEmpty(str) ? str.substring(0, 1).toUpperCase() 
				+ str.substring(1) : "";
	}

	/**
	 * ����ĸСд
	 * 
	 * @param str
	 * @return
	 */
	public static String lowerCase(String str) {
		return !isEmpty(str) ? str.substring(0, 1).toLowerCase() + 
				str.substring(1) : "";
	}

	/**
	 * �ֻ�������4-9λ
	 * 
	 * @param phone
	 * @return
	 *//*
	public static String getSensitivePhone(String phone) {
		return desensitization(phone, 3, 2);
	}

	*//**
	 * ���֤��������2-17λ
	 * 
	 * @param phone
	 * @return
	 *//*
	public static String getSensitiveCardnum(String cardnum) {
		return desensitization(cardnum, 1, 1);
	}*/

	/**
	 * ��������
	 * 
	 * @param str
	 * @param left
	 * @param right
	 * @return
	 *//*
	private static String desensitization(String str, Integer left, Integer right) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		String replaceCardnum = str.replaceAll(" ", "");
		return StringUtils.left(replaceCardnum, left).concat(StringUtils.removeStart(
				StringUtils.leftPad(StringUtils.right(replaceCardnum, right), StringUtils.length(replaceCardnum), "*"),
				"***"));

	}*/

	/**
	 * ��һ���ַ���ת��Ϊ������
	 */
	public static InputStream getStringStream(String sInputString) {
		if (!isEmpty(sInputString)) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	
	/**
	 *  ���ַ���ת��ΪGBK�ַ��� ���� : strOriginal:ԭ�� ����ֵ�� ��ԭ����ISO8859_1(Unicode)����ת��ΪGBK����
	 * @param strOriginal
	 * @return
	 */
	public static String unicodeToGBK(String strOriginal) {
		if (strOriginal != null) {
			try {
				if (!isGBKString(strOriginal)) {
					return new String(strOriginal.getBytes(ISO8859_1), GBK);
				} else {
					return strOriginal;
				}

			} catch (Exception exception) {
				return strOriginal;
			}
		} else {
			return "";
		}
	}

	
    /**
     * ���ַ���ת��ΪUnicode�ַ���
     * @param strOriginal  ԭ��
     * @param realConvert �Ƿ�ȷ��ת��
     * @return  ��ԭ����GBK����ת��ΪISO8859_1(Unicode)����
     */
	public static String GBKToUnicode(String strOriginal, boolean realConvert) {
		if (realConvert == false) {
			return unicodeToGBK(strOriginal);
		}
		if (strOriginal != null) {
			try {
				if (isGBKString(strOriginal)) {
					return new String(strOriginal.getBytes(GBK), ISO8859_1);
				} else {
					return strOriginal;
				}
			} catch (Exception exception) {
				return strOriginal;
			}
		} else {
			return "";
		}
	}
	/**
	 * �ж��Ƿ�Ϊ�Ǹ�����
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(0|[1-9]\\d*)$");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж��Ƿ���GBK����
	 * @param tStr
	 * @return
	 */
	public static boolean isGBKString(String tStr) {
		int tlength = tStr.length();
		int t = 0;
		for (int i = 0; i < tlength; i++) {
			t = Integer.parseInt(Integer.toOctalString(tStr.charAt(i)));
			if (t > 511) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �ж��Ƿ���Unicode����
	 * 
	 * @param tStr
	 * @return
	 */
	public static boolean isUnicodeString(String tStr) {
		int tlength = tStr.length();
		int t = 0;
		for (int i = 0; i < tlength; i++) {
			t = Integer.parseInt(Integer.toOctalString(tStr.charAt(i)));
			if (t > 511) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ��һ��������ת��Ϊ�ַ���
	 */
	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);
				}
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "";
	}
	
	
	/**
	 * �Ƿ�����
	 * @param year ��
	 * @return �Ƿ�����
	 */
	public static boolean isLeapYear(int year) {
		return new GregorianCalendar().isLeapYear(year);
	}
	/**
	 * �ж��Ƿ�Ϊ���ڸ�ʽyyyy-MM-dd֧������
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(LEAP_YEAR_DATE);
		return pattern.matcher(str).matches();
	}
	/**
	 * �ж��Ƿ�Ϊ���ڸ�ʽyyyy-MM-dd HH:mm:ss֧������
	 * @param str
	 * @return
	 */
	public static boolean isDate_time(String str) {
		if (isEmpty(str)) 
			return false;
		Pattern pattern = Pattern.compile(LEAP_YEAR_TIME);
		return pattern.matcher(str).matches();
	}

}
