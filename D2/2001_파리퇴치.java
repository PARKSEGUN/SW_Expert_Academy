맵에 누적합을 적용해서 모든 맵을 구현해놓고 범위를 지정해서 해결

-----------코드-----------


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int T, N, M;
	static int[][] arr;
	static StringTokenizer tokenizer;
	static StringBuilder resultString = new StringBuilder();

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(reader.readLine());
		for (int t = 1; t <= T; t++) {
			resultString.append("#").append(t).append(" ");
			setting();
			resultString.append(solution()).append("\n");

		}
		System.out.print(resultString);
	}

	private static int solution() {
		int answer = 0;
		List<Integer> integerList = new ArrayList<Integer>();
		for (int i = 1; i <= N - M + 1; i++) {
			for (int j = 1; j <= N - M + 1; j++) {
				int tmp = arr[i + M - 1][j + M - 1] - arr[i - 1][j + M - 1] - arr[i + M - 1][j - 1] + arr[i - 1][j - 1];
				integerList.add(tmp);
				answer = Math.max(answer, tmp);
			}
		}
		return answer;
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 1; j <= N; j++) {
				int tmp = Integer.parseInt(tokenizer.nextToken());
				arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + tmp;
			}
		}
	}
}
