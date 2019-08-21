package com.one;

import java.io.IOException;

import com.one.bean.CharacterType;
import com.one.bean.TrieDictionary;
import com.one.bean.TrieNode;

/**
 * 分词工具
 * 
 * @author linhs
 *
 */
public class Segmenter {
	public static TrieDictionary dict = null;

	static { // 加载词典
		dict = TrieDictionary.getInstance();
	}

	/**
	 * 正向匹配分词
	 * 
	 * @param sentence
	 * @return
	 */
	public String segment(String sentence) {
		StringBuffer segBuffer = new StringBuffer();
		TrieNode root = dict.getRoot();
		TrieNode cur = root;
		int length = sentence.length();
		for (int i = 0; i < length; ++i) {
			char c = sentence.charAt(i);
			if (CharacterType.isCharChinese(c)) {// 识别出一个中文词
				cur = cur.childs.get(c);
				if (cur == null) {// 不在词典中的中文字符
					segBuffer.append(c);
					segBuffer.append('|'); // 添加分词标记
					cur = root;
				} else {// 在词典中的中文字符
					do {
						segBuffer.append(c);
						if (++i == length) {
							break;
						}
						c = sentence.charAt(i);
						cur = cur.childs.get(c);
					} while (CharacterType.isCharChinese(c) && cur != null);
					if (i != length)
						--i;// 还原现场
					segBuffer.append('|'); // 添加分词标记
					cur = root;
				}
			} else if (CharacterType.isCharOther(c)) {// 识别出一个其他语言单词
				do {
					segBuffer.append(c);
					if (++i == length) {
						break;
					}
					c = sentence.charAt(i);
				} while (CharacterType.isCharOther(c));
				if (i != length)
					--i;// 还原现场
				segBuffer.append('|'); // 添加分词标记
				cur = root;
			} else if (CharacterType.isCharSeperator(c)) {// 可以多个连续分隔符

			}

		}

		return new String(segBuffer);
	}

	public static void main(String args[]) throws IOException {
		Segmenter mmsegger = new Segmenter();
		String word = "我叫林海石，目前就职于平安产险AI部门机器人组，担任机器人组组长，熟悉NLP常用技术，有丰富的nlp项目工程实战经验，精通多轮对话系统设计，喜欢钻研新技术，个人作品：129.204.64.135/oneChat";
		System.out.println("原句：" + word);
		System.out.println(mmsegger.segment(word));
	}
}
