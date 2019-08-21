package com.one;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * onenlp: onenlp Processing <br>
 *
 * @author linhs
 */
public class One {

	/**
	 * 库的全局配置，既可以用代码修改，也可以通过one.properties配置（按照 变量名=值 的形式）
	 */
	public static final class Config {
		/**
		 * 开发模式
		 */
		public static boolean DEBUG = false;
		/**
		 * 测试词典路径
		 */
		public static String WORDDICPATH = "data/words.dic";

		static {
			System.out.println("读取配置文件");
			// 自动读取配置
			Properties p = new Properties();
			try {
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				if (loader == null) { // IKVM (v.0.44.0.5) doesn't set context classloader
					loader = One.Config.class.getClassLoader();
				}
				p.load(new InputStreamReader(loader.getResourceAsStream("one.properties"), "UTF-8"));
				String root = p.getProperty("root", "").replaceAll("\\\\", "/");
				System.out.println("root path:" + root);
				if (root.length() > 0 && !root.endsWith("/"))
					root += "/";
				WORDDICPATH = root + p.getProperty("wordDicPath", WORDDICPATH);
				System.out.println("wordDicPath path:" + WORDDICPATH);
			} catch (Exception e) {
				System.out.println("没有找到one.properties");
			}
		}

	}
}
