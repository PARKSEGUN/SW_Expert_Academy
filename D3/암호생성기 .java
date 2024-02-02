- 큐의 성질을 이용한 해결



----------------코드----------------
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int T, N;
	static Deque<Integer> dq;
	static StringBuilder resultString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= 10; t++) {
			t = Integer.parseInt(reader.readLine());
			resultString.append("#").append(t).append(" ");
			setting();
			while (dq.peekLast() != 0) {
				for (int i = 1; i <= 5; i++) {
					int tmp = dq.pop();
					if (tmp - i > 0) {
						dq.add(tmp - i);
					} else {
						dq.add(0);
						break;
					}
				}

			}
			for (int x : dq) {
				resultString.append(x).append(" ");
			}
			resultString.append("\n");
		}
		System.out.println(resultString);
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		dq = new ArrayDeque<>();
		for (int i = 0; i < 8; i++) {
			dq.add(Integer.parseInt(tokenizer.nextToken()));
		}
	}
}
