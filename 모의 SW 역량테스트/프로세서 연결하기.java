모든 core 마다 4가지 방향을 확인해서 뻗어주면서 모든경우를 확인한다.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int T, N;
	static boolean map[][];
	static List<int[]> core;
	static int[] dix = { -1, 0, 1, 0 };
	static int[] diy = { 0, 1, 0, -1 };
	static int answerCnt;
	static int answerValue = Integer.MAX_VALUE;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(reader.readLine());
		for (int t = 0; t < T; t++) {
			setting();
			findAnswer(0, 0, 0, 0);
			answerString.append("#").append(t + 1).append(" ").append(answerValue).append("\n");
			answerCnt = 0;
			answerValue = Integer.MAX_VALUE;
		}
		System.out.println(answerString);
	}

	private static void findAnswer(int start, int cnt, int sum, int coreCnt) {
		if (cnt == core.size()) {
			if (answerCnt < coreCnt) {
				answerCnt = coreCnt;
				answerValue = sum;
			} else if (answerCnt == coreCnt) {
				answerValue = Math.min(answerValue, sum);
			}
			return;
		}
		for (int i = start; i < core.size(); i++) {
			for (int j = 0; j < 4; j++) {
				int x = core.get(i)[0];
				int y = core.get(i)[1];
				int distance = isPossible(x, y, j);
				if (distance != -1) {
					makeConnect(x, y, j, true);
					findAnswer(i + 1, cnt + 1, sum + distance, coreCnt + 1);
					makeConnect(x, y, j, false);
				}
			}
			findAnswer(i + 1, cnt + 1, sum, coreCnt);
		}
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print((map[i][j] == false) ? 0 : 1);
			}
			System.out.println();

		}
		System.out.println();
	}

	private static void makeConnect(int x, int y, int dir, boolean target) {
		int dx = x + dix[dir];
		int dy = y + diy[dir];
		while (dx >= 0 && dx < N && dy >= 0 && dy < N) {
			map[dx][dy] = target;
			dx = dx + dix[dir];
			dy = dy + diy[dir];
		}
	}

	private static int isPossible(int x, int y, int dir) {
		int cnt = 0;
		int dx = x + dix[dir];
		int dy = y + diy[dir];
		while (dx >= 0 && dx < N && dy >= 0 && dy < N) {
			if (map[dx][dy] == true) {
				return -1;
			}
			dx = dx + dix[dir];
			dy = dy + diy[dir];
			cnt++;
		}
		return cnt;
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		map = new boolean[N][N];
		core = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				boolean x = (tokenizer.nextToken().equals("0") ? false : true);
				map[i][j] = x;
				if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
					if (x == true) {
						core.add(new int[] { i, j });
					}
				}
			}
		}
	}
}
