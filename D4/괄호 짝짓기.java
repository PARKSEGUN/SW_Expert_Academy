스택구조를 이용한 풀이
스택의 Top과 현재 탐색중인 모양으로 인덱스를 비교


-----------------코드------------------


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static String open = "([{<";
	static String close = ")]}>";
	static int answer = 1;

	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= 10; t++) {
			setting(t);
			Stack<Character> stack = new Stack<>();
			String input = reader.readLine();
			for (int i = 0; i < input.length(); i++) {
				if (stack.size() > 0 && close.indexOf(input.charAt(i)) != -1) {
					if (open.indexOf(stack.peek()) == close.indexOf(input.charAt(i))) {
						stack.pop();
						continue;
					} else {
						answer = 0;
						break;
					}
				}
				stack.add(input.charAt(i));
			}
			resultString.append(answer).append("\n");
		}
		System.out.println(resultString);
	}

	private static void setting(int t) throws IOException {
		answer = 1;
		resultString.append("#").append(t).append(" ");
		N = Integer.parseInt(reader.readLine());
	}
}
