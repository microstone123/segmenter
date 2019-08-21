package com.one.bean;

public class CharacterType {
	/**
	 * 是分隔符
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isCharSeperator(char c) {
		return "\u3002\uFF01\uFF1F\uFF1A\uFF1B\u3001\uFF0C\uFF08\uFF09\u300A\u300B\u3010\u3011{}\u201C\u201D\u2018\u2019!?:;,()<>[]{}\"'\n\r\t "
				.indexOf(c) != -1;
	}

	/**
	 * 是中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isCharChinese(char c) {
		return c >= '\u4E00' && c <= '\u9FBF';
	}

	/**
	 * 其他字符
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isCharOther(char c) {
		return !isCharSeperator(c) && !isCharChinese(c);
	}

}
