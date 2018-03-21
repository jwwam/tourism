package com.tourism.utils;

/*
 *  프로젝트명 : 서초사옥 사무환경 솔루션 구축
 *  과제명 : MOB 오피스 솔루션
 *
 *  Copyright 2007 by Digital Solution Center, Samsung Electronics, Inc.,
 *   All rights reserved.
 *
 *  This software is the confidential and proprietary information
 *  of Samsung Electronics, Inc. ("Confidential Information").  You
 *  shall not disclose such Confidential Information and shall use
 *  it only in accordance with the terms of the license agreement
 *  you entered into with Samsung.
 *
 *
 */ 

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Description : 공통적으로 필요한 데이터 변환기능 및 문자열 조작기능등이 구현되어 있음.
 * 
 * <pre>
 *  Externalized Method : None
 *  Initialization and Sequencing requirements : None
 * </pre>
 * 
 * @author 양유민 (010-9990-6878, perfectjava94@gmail.com)
 * <pre>
 *  2007. 02. 23 최초작성 by 양유민
 *  2007. 04. 10 정재훈 collapse 메소드 추가 
 * </pre>
 */


public class StringUtil {
	
	/**
	 * right align
	 */
	public static final int RIGHT = 1;

	/**
	 * left align
	 */
	public static final int LEFT = 2;


	/**
	 * 주어진 문자열을 이용하여 지정한 위치로부터 원하는 길이만큼의 문자열을 구함
	 * 
	 * @param str
	 *            원하는 문자열 가지고 있는 문자열
	 * @param offset
	 *            원하는 문자열 시작위치 (1부터 시작)
	 * @param leng
	 *            원하는 문자열 길이
	 * @return 원하는 문자열 객체
	 */

	public static String subString(String str, int offset, int leng) {
		return new String(str.getBytes(), (offset - 1), leng);
	}

	/**
	 * 주어진 문자열을 이용하여 지정한 위치로부터 끝까지의 문자열을 구함
	 * 
	 * @param str
	 *            원하는 문자열 가지고 있는 문자열
	 * @param offset
	 *            원하는 문자열 시작위치 (1부터 시작)
	 * @return 원하는 문자열 객체
	 */

	public static String subString(String str, int offset) {
		byte[] bytes = str.getBytes();
		int size = bytes.length - (offset - 1);
		return new String(bytes, (offset - 1), size);
	}

	/**
	 * 주어진 문자열을 대상으로하여 주어진 길이만큼의 문자열을 생성하여 리턴함.
	 * <p>
	 * 
	 * <pre>
	 *   (예)
	 *  	String str = &quot;abcd&quot;;
	 *  	System.out.println(StringUtil.fitString(str,6));
	 *  	출력 = &quot;abcd  &quot;
	 *  
	 *  	String str = &quot;abcd&quot;;
	 *  	System.out.println(StringUtil.fitString(str,3));
	 *  	출력 = &quot;abc&quot;
	 *  
	 *  	String str = &quot;가나다라&quot;;
	 *  	System.out.println(StringUtil.fitString(str,6));
	 *  	출력 = &quot;가나다&quot;
	 *  
	 *  	String str = &quot;가나다라&quot;;
	 *  	System.out.println(StringUtil.fitString(str,3));
	 *  	출력 = &quot;???&quot;
	 * </pre>
	 * 
	 * @param str
	 *            대상 문자열
	 * @param size
	 *            만들고자 하는 문자열의 길이
	 * @return 주어진 길이만큼의 문자
	 */

	public static String fitString(String str, int size) {
		return fitString(str, size, StringUtil.LEFT);
	}

	/**
	 * 주어진 문자열을 대상으로하여 주어진 길이만큼의 문자열을 생성하여 리턴함.
	 * <p>
	 * 
	 * <pre>
	 *   (예)
	 *  	String str = &quot;abcd&quot;;
	 *  	System.out.println(StringUtil.fitString(str,6,StringUtil.RIGHT));
	 *  	출력 = &quot;  abcd&quot;
	 * </pre>
	 * 
	 * @param str
	 *            대상 문자열
	 * @param size
	 *            만들고자 하는 문자열의 길이
	 * @param align
	 *            정열기준의 방향(RIGHT, LEFT)
	 * @return 주어진 길이만큼의 문자
	 */

	public static String fitString(String str, int size, int align) {
		byte[] bts = str.getBytes();
		int len = bts.length;
		if (len == size) {
			return str;
		}
		if (len > size) {
			String s = new String(bts, 0, size);
			if (s.length() == 0) {
				StringBuffer sb = new StringBuffer(size);
				for (int idx = 0; idx < size; idx++) {
					sb.append("?");
				}
				s = sb.toString();
			}
			return s;
		}
		if (len < size) {
			int cnt = size - len;
			char[] values = new char[cnt];
			for (int idx = 0; idx < cnt; idx++) {
				values[idx] = ' ';
			}
			if (align == StringUtil.RIGHT) {
				return String.copyValueOf(values) + str;
			} else {
				return str + String.copyValueOf(values);
			}
		}
		return str;
	}

	/**
	 * 문자열을 기본분리자(white space)로 분리하여 문자열배열을 생성함
	 * 
	 * @param str 기본 분리자(white space)로 구분된 문자열
	 * @return 문자열 배열
	 */
	public static String[] toStringArray(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}	
		
		return toStringArray(list);
	}

	/**
	 * Vector에 저장된 객체들을 이용하여 문자열 배열을 생성함
	 * 
	 * @param list String이 내용으로 들어있는 List 객체
	 * @return 문자열 배열
	 */
	public static String[] toStringArray(List<String> list) {
		String[] strings = new String[list.size()];
		for (int idx = 0; idx < list.size(); idx++) {
			strings[idx] = list.get(idx).toString();
		}
		return strings;
	}

	/**
	 * 주어진 문자열에서 지정한 문자열값을 지정한 문자열로 치환후 그결과 문자열을 리턴함.
	 * 
	 * @param src
	 * @param from
	 * @param to
	 * @return 문자열
	 */
	public static String replace(String src, String from, String to) {
		if (src == null)
			return null;
		if (from == null)
			return src;
		if (to == null)
			to = "";
		StringBuffer buf = new StringBuffer();
		for (int pos; (pos = src.indexOf(from)) >= 0; ){
			buf.append(src.substring(0, pos));
			buf.append(to);
			src = src.substring(pos + from.length());
		}
		buf.append(src);
		return buf.toString();
	}

	/**
	 * 주어진문자열이 지정한 길이를 초과하는 경우 짤라내고 '...'을 붙여 리턴함.
	 * 
	 * @param str
	 * @param limit
	 * @return 문자열
	 */

	public static String cutString(String str, int limit) {

		if (str == null || limit < 4)
			return str;

		int len = str.length();
		int cnt = 0, index = 0;

		while (index < len && cnt < limit) {
			if (str.charAt(index++) < 256) // 1바이트 문자라면...
				cnt++; // 길이 1 증가
			else
				// 2바이트 문자라면...
				cnt += 2; // 길이 2 증가
		}

		if (index < len)
			str = str.substring(0, index - 1) + "...";

		return str;
	}

	/**
	 * 스트링에서 특정 문자를 시작으로 끝 문자열까지 삭제한다.
	 * 
	 * @param src
	 * @param end
	 * @return result 삭제후 값.
	 */

	public static String cutEndString(String src, String end) {
		if (src == null)
			return null;
		if (end == null)
			return src;

		int pos = src.indexOf(end);

		if (pos >= 0) {
			src = src.substring(0, pos);
		}

		return src;
	}

	/**
	 * 주어진 문자로 원하는 갯수만큼의 char[] 를 생성함.
	 * 
	 * @param c
	 *            생성할 문자
	 * @param cnt
	 *            생성할 갯수
	 * @return char array
	 */

	public static char[] makeCharArray(char c, int cnt) {
		char[] a = new char[cnt];
		Arrays.fill(a, c);
		return a;
	}

	/**
	 * 주어진 문자로 원하는 갯수만큼의 String 을 생성함.
	 * 
	 * @param c
	 *            생성할 문자
	 * @param cnt
	 *            생성할 갯수
	 * @return 원하는 갯수 많큼 생성된 문자열
	 */

	public static String getString(char c, int cnt) {
		return new String(makeCharArray(c, cnt));
	}

	// 2002-02-07 추가

	/**
	 * String의 좌측 공백을 없앤다.
	 * 
	 * @param lstr
	 *            대상 String
	 * @return String 결과 String
	 */

	public static String getLeftTrim(String lstr) {

		if (!lstr.equals("")) {
			int strlen = 0;
			int cptr = 0;
			boolean lpflag = true;
			char chk;

			strlen = lstr.length();
			cptr = 0;
			lpflag = true;

			do {
				chk = lstr.charAt(cptr);
				if (chk != ' ') {
					lpflag = false;
				} else {
					if (cptr == strlen) {
						lpflag = false;
					} else {
						cptr++;
					}
				}
			} while (lpflag);

			if (cptr > 0) {
				lstr = lstr.substring(cptr, strlen);
			}
		}
		return lstr;
	}

	/**
	 * String의 우측 공백을 없앤다.
	 * 
	 * @param lstr
	 *            대상 String
	 * @return String 결과 String
	 */

	public static String getRightTrim(String lstr) {
		if (!lstr.equals("")) {
			int strlen = 0;
			int cptr = 0;
			boolean lpflag = true;
			char chk;

			strlen = lstr.length();
			cptr = strlen;
			lpflag = true;

			do {
				chk = lstr.charAt(cptr - 1);
				if (chk != ' ') {
					lpflag = false;
				} else {
					if (cptr == 0) {
						lpflag = false;
					} else {
						cptr--;
					}
				}
			} while (lpflag);

			if (cptr < strlen) {
				lstr = lstr.substring(0, cptr);
			}
		}
		return lstr;
	}

	/**
	 * 좌측에서 특정 길이 만큼 취한다.
	 * 
	 * @param str
	 *            대상 String
	 * @param len
	 *            길이
	 * @return 결과 String
	 */

	public static String getLeft(String str, int len) {
		if (str == null)
			return "";

		return str.substring(0, len);
	}

	/**
	 * 우측에서 특정 길이 만큼 취한다.
	 * 
	 * @param str
	 *            대상 String
	 * @param len
	 *            길이
	 * @return 결과 String
	 */

	public static String getRight(String str, int len) {
		if (str == null)
			return "";

		String dest = "";
		for (int i = (str.length() - 1); i >= 0; i--){
			dest = dest + str.charAt(i);
		}

		str = dest;
		str = str.substring(0, len);
		dest = "";

		for (int i = (str.length() - 1); i >= 0; i--){
			dest = dest + str.charAt(i);
		}

		return dest;
	}

	/**
	 * 입력된 값이 널이면, replace 값으로 대체한다.
	 * 
	 * @param str
	 *            입력
	 * @param replace
	 *            대체 값
	 * @return 문자
	 */

	public static String nvl(String str, String replace) {
		if (str == null) {
			return replace;
		} else {
			return str;
		}
	}

	/**
	 * Null 또는 공백이면 다른 값으로 대체한다.
	 * 
	 * @param str
	 *            입력
	 * @param replace
	 *            대체 값
	 * @return 문
	 */

	public static String checkEmpty(String str, String replace) {
		if (str == null || str.equals("")) {
			return replace;
		} else {
			return str;
		}
	}

	/**
	 * 문자를 합친다.
	 * 
	 * @param str
	 *            문자
	 * @return 합쳐진 문자
	 */

	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen).append(
				Character.toTitleCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}

	/**
	 * Exception 정보를 String으로 변환한다.
	 * 
	 * @param e
	 *            Exception
	 * @return String 변환된 Exception
	 */

	public static String getErrorTrace(Exception e) {
		if (e == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);

		String errTrace = sw.toString();

		return errTrace;
	}

	/**
	 * XML에서 사용하는 특수 문자를 변환한다.
	 * 
	 * @param s
	 * @return str
	 */

	public static String escapeXml(String s) {
		if (s == null)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '&') {
				sb.append("&amp;");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * token문자들을 ArrayList로 반환
	 * 
	 * @param s
	 *            구분자를 포함한 문자열
	 * @param token
	 *            구분자
	 * @return List 문자들이 저장된 List object
	 */

	public static List<String> getTokenList(String s, String token) {
		if (s == null || s.equals(""))
			return null;

		List<String> tokenList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(s, token);

		while (st.hasMoreTokens()) {
			tokenList.add(st.nextToken().trim());
		}

		return tokenList;
	}

	/**
	 * 구분(token) 문자열(s) 중에서 문자의 카운트를 가져옴
	 * 
	 * @param s
	 *            구분자를 포함한 문자열
	 * @param token
	 *            구분자
	 * @return int word count
	 */

	public static int getTokenLength(String s, String token) {
		if (s == null)
			return 0;

		int len = 0;
		StringTokenizer st = new StringTokenizer(s, token);
		while (st.hasMoreTokens()) {
			len++;
		}
		return len;
	}

	/**
	 * 구분(token) 문자중(s)에서 특정 index번째 문자를 가져옴
	 * 
	 * @param index
	 *            가져올 문자의 index
	 * @param s
	 *            구분자를 포함한 문자열
	 * @param token
	 *            구분자
	 * @return String index번째 문자
	 */

	public static String getToken(int index, String s, String token) {
		if (s == null)
			return "";

		StringTokenizer st = new StringTokenizer(s, token);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; st.hasMoreTokens(); i++) {
			if (index == i) {
				sb.append(st.nextToken());
				break;
			}
			st.nextToken();
		}

		if (sb.toString().length() > 0)
			return sb.toString().trim();
		else
			return "";
	}

	/**
	 * 구분(token) 문자중(s)에서 특정 index번째 문자를 가져옴. <BR>
	 * 문자(s)가 null일시 nvl반환
	 * 
	 * @param index
	 *            가져올 문자의 index
	 * @param s
	 *            구분자를 포함한 문자열
	 * @param token
	 *            구분자
	 * @param nvl
	 *            null일째 반환될 nvl
	 * @return String index번째 문자
	 */

	public static String getToken(int index, String s, String token, String nvl) {
		if (s == null)
			return nvl;

		StringTokenizer st = new StringTokenizer(s, token);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; st.hasMoreTokens(); i++) {
			if (index == i) {
				sb.append(st.nextToken());
				break;
			}
			st.nextToken();
		}

		if (sb.toString().length() > 0)
			return sb.toString().trim();
		else
			return nvl;
	}

	/**
	 * StringBuffer의 내용을 지운다
	 * 
	 * @param strBuf
	 *            지울 StringBuffer
	 */

	public static void deleteStringBuffer(StringBuffer strBuf) {
		strBuf.delete(0, strBuf.length());
	}

	/**
	 * 인자로 주어진 String 객체가 널이 아니고 길이가 0 보다 클 경우 true 리턴한다.
	 * 
	 * @param str
	 *            검사할 String
	 * @return boolean 
	 */
	public static boolean isset(String str) {
		return (str != null && str.trim().length() > 0);
	}

	/** 
	 * 문자열에서 일치하는 문자를 다른 문자로 교체한다. 
	 *
	 * @param htmlStr
	 * @return
	 */
	public static String collapse(String str, String characters, String replacement) {
		if (str == null) {
			return null;
		}
		
		StringBuffer newStr = new StringBuffer();

		boolean prevCharMatched = false;
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (characters.indexOf(c) != -1) {
				// this character is matched
				if (prevCharMatched) {
					// apparently a string of matched chars, so don't append anything
					// to the string
					continue;
				}
				prevCharMatched = true;
				newStr.append(replacement);
			} else {
				prevCharMatched = false;
				newStr.append(c);
			}
		}
		return newStr.toString();
	}
	
	/**
	 * 주어진 String을 byte로 변환하여 max와 비교하는데, 이보다 크면 max 만큼만 잘라서 리턴.
	 * 여기서, 국영문이 혼합된 문자열의 마지막 국문자는 잘릴 위험이 있는데 이때, 해당 문자는 버린다. 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 *
	 * @param str
	 * @param max
	 * @return str
	 */
	public static String getString(String str, int max){
		
		byte[] temp = str.getBytes();
		
		int count = 0; //한글 count
		int str_count = 0;
		 
		while (max > str_count) {
			
			if (str_count == temp.length) break;
			
			//한글처럼 2byte 문자의 경우 byte수치가 마이너스로 표시됨
			if (temp[str_count] < 0) count++; 
			
			str_count++;
		}
		
		//한글이 잘릴경우 버림
		if (count % 2 != 0) {
			str = new String(temp, 0, str_count - 1);
		} else {
			str = new String(temp, 0, str_count);
		}
		
		return str;
	}
	
	/**
	 * Table object 에서 사용되지 않는 컬럼 체크.
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 *
	 * @param obj Object
	 * @return boolean true/false
	 */
	public static boolean checkUndefined(Object obj){
		
		boolean result = false;
		
		if (obj.toString().equals("Undefined")) {
			result = true;
		} else  {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * PdaNo에 대시(-)를 넣어 준다.
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 *
	 * @param pdaNo String값
	 * @return  String 대시된 pda no
	 */
	public static String dashedPdaNo(String pdaNo){
		
		String firstPdaNo = "";
		String secondPdaNo = "";
		String thirdPdaNo = "";
		
		if (null == pdaNo || 0 == pdaNo.trim().length()){
			return "";
		}
		
		if (pdaNo.trim().length() < 10 || 11 < pdaNo.trim().length()){
			return pdaNo;
		}
		
		firstPdaNo = pdaNo.substring(0, 3) + "-";
		secondPdaNo = pdaNo.substring(3, pdaNo.length() - 4) + "-";
		thirdPdaNo = pdaNo.substring(pdaNo.length() - 4);

		return firstPdaNo + secondPdaNo + thirdPdaNo;		
	}
	
	/**
	 * like 검색을 위해 값에 '%'를 붙여준다.
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 *
	 * @param value DB에서 like 검색 대상의 값
	 * @return value의 양쪽에 '%'를 붙여 리턴.
	 */
	public static String makeLikeValue(String value) {
		StringBuffer sb = new StringBuffer();
		sb.append('%');
		if (value != null) {
			sb.append(value);
		}
		sb.append('%');
		
		return sb.toString();
	}
	
	/**
	 * 주어진 문자열에 ASCII 문자가 아닌것이 존재하는지 체크.
	 * @param src
	 * @return
	 */
	public  static boolean existsNonAscii(String src) { 
		byte [] b = src.getBytes(); 
		for (int i = 0; i < b.length; i++) { 
			if (b[i] < 0) return true; 
		} 
		return false; 
	} 
	
	/**
	 * 주어진 문자열이 한글인지 체크
	 * @param str
	 * @return
	 */
	public static boolean isEnglish(String str) {
		boolean flag = false;

	    for(int j = 0; j<str.length(); j++){
	      char c = str.charAt(j);
	      if ( c  <  0xac00 || 0xd7a3 < c ) {
	    	  //영문인 경우
	    	  flag = true;
	      }
	    }
	    return flag;
	}
	
	public static String nullToBlank(String str){
		return str == null ? "" : str;
	}
	
//	public static void peakLogWrite(String userID, String method, String module, long startTime){
//		long endTime = 0;
//		long workTime = 0;
//		//Logger peakLogger = Logger.getLogger(PEMGlobal.PEM_LOG);
//		endTime = System.currentTimeMillis();
//		workTime = endTime - startTime;
//		//peakLogger.debug(userID + " | " + method + " | " + module  + " | " + startTime  + " | " + endTime  + " | " + workTime);
//		
//	}
//	
//	public static void peakLogWrite(String userID, String module, long startTime){
//		long endTime = 0;
//		long workTime = 0;
////		Logger peakLogger = Logger.getLogger(PEMGlobal.PEAK_LOG);
//		String logName = null;
////		if ( module.equals("mail") ) {
////			logName = PEMGlobal.MAIL_LOG;
////		} else if ( module.equals("approval") ) {
////			logName = PEMGlobal.APPROVAL_LOG;
////		} else if ( module.equals("schedule") ) {
////			logName = PEMGlobal.SCHEDULE_LOG;
////		} else if ( module.equals("single") ) {
////			logName = PEMGlobal.SINGLE_LOG;
////		} else {
////			logName = PEMGlobal.SEARCH_LOG;
////		}
//
//		logName = PEMGlobal.PEM_LOG;		
//		//Logger peakLogger = Logger.getLogger(logName);
//		endTime = System.currentTimeMillis();
//		workTime = endTime - startTime;
//		//if ( userID == null || userID.equals("") )
//			//peakLogger.debug(module  + " | " + workTime  + " | " + convertToDate(startTime) + " | " + convertToDate(endTime));
//		//else
//			//peakLogger.debug(userID + " | " + module  + " | " + workTime  + " | " + convertToDate(startTime)  + " | " + convertToDate(endTime));
//		
//	}
//	private static String convertToDate(long time) {
//		
//		return DateUtil.getDateString(new Date(time), "yyyy-MM-dd HH:mm:ss.S");
//	}
	
	
}
