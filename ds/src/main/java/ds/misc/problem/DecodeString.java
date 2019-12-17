package ds.misc.problem;

import java.util.Stack;

/**
 * 
 * An encoded string (s) is given, the task is to decode it. The pattern in
 * which the strings were encoded were as follows <br/>
 * original string: abbbababbbababbbab <br/>
 * encoded string : "3[a3[b]1[ab]]".
 * 
 * @author prabhakaran.nivanil
 */
public class DecodeString {
	public static void main(String[] args) {
		System.out.println(solution("3[b2[ca]]"));
	}

	private static String solution(String str) {
		char[] ch = str.toCharArray();
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Character> stackChar = new Stack<Character>();
		StringBuffer sb = new StringBuffer();
		for (char c : ch) {
			if (Character.isDigit(c)) {
				stack.push(Integer.parseInt(String.valueOf(c)));
			} else if (c == ']') {
				sb.append(buildOutput(stack, stackChar));
			} else {
				stackChar.push(c);
			}
		}
		String temp = sb.toString();
		int cur = stack.isEmpty() ? 1 : stack.pop();
		for (int i = 0; i < cur - 1; i++) {
			sb.append(temp);
		}
		return sb.reverse().toString();
	}

	private static String buildOutput(Stack<Integer> stack, Stack<Character> stackChar) {
		boolean flag = true;
		StringBuffer sb = new StringBuffer();
		while (flag && !stackChar.isEmpty()) {
			char ch = stackChar.pop();
			if (ch == '[') {
				flag = false;
			} else {
				sb.append(ch);
			}
		}
		String temp = sb.toString();
		if (stack.size() > 1) {
			int cur = stack.pop();
			for (int i = 0; i < cur - 1; i++) {
				sb.append(temp);
			}
		}

		return sb.toString();
	}
}
