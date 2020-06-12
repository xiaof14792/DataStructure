package Set;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * 文件相关操作
 * 
 * @author 14792
 *
 */
public class FileOperation {
	// 读取文件名为filename中的内容，并将其中包含的所有词语放入words中
	public static boolean readFile(String filename, ArrayList<String> words) {

		if (filename == null || words == null) {
			System.out.println("filename or words is null");
			return false;
		}

		// 文件读取
		Scanner scanner = null;
		StringBuilder sb = new StringBuilder();

		try {
			File file = new File(filename);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream buf = new BufferedInputStream(fis);
				byte[] bytes = new byte[1024];
				int length = buf.read(bytes);
				while (length != -1){
					sb.append(new String(bytes, "UTF-8"));
					length = buf.read(bytes);
				}
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("can not open " + filename);
			return false;
		}

		// 简单分词
		// 这个分词方式相对简陋，没有考虑文本处理中的特殊问题
		// 这里只做demo用
		if (sb.length() > 0) {
			String contents = sb.toString();
			System.out.println("**contents length: " + contents.length() + "**");
//			System.out.println(contents);

			int start = firstCharacterIndex(contents, 0);
			for (int i = start + 1; i <= contents.length();) {
				if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
					String word = contents.substring(start, i).toLowerCase();
					words.add(word);

					start = firstCharacterIndex(contents, i);
					i = start + 1;
				} else {
					i++;
				}
			}
		}

//		scanner.close();

		return true;
	}

	// 寻找字符串s中，从start位置开始的第一个字母字符的位置
	private static int firstCharacterIndex(String s, int start) {
		for (int i = start; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				return i;
			}
		}

		return s.length();
	}

}
