완전 탐색을 이용한 구현으로 해결

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T, N, W, H;
	static int[][] map;
	static int[] dix = { -1, 0, 1, 0 };
	static int[] diy = { 0, 1, 0, -1 };
	static int answerCnt;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			answer.append("#").append(t + 1).append(" ");
			input();
			startGame(0, map);
			answer.append(answerCnt).append("\n");
		}
		System.out.println(answer);
	}

	private static void startGame(int cnt, int[][] curMap) {
//		printMap(curMap);
		if (cnt == N) {
			answerCnt = Math.min(answerCnt, findNotZeroCnt(curMap));
			return;
		}
		for (int i = 0; i < W; i++) {
			int[][] tmpMap = copyMap(curMap);
			findTargetAndBreak(i, tmpMap);
			mapDown(tmpMap);
			startGame(cnt + 1, tmpMap);
		}

	}

	private static int[][] copyMap(int[][] curMap) {
		int[][] tmpMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tmpMap[i][j] = curMap[i][j];
			}
		}
		return tmpMap;
	}

	private static void printMap(int[][] map) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	private static void mapDown(int[][] curMap) {
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (curMap[j][i] == 0) {
					for (int k = j - 1; k >= 0; k--) {
						if (curMap[k][i] != 0) {
							curMap[j][i] = curMap[k][i];
							curMap[k][i] = 0;
							break;
						}
					}
				}
			}
		}
	}

	private static int findNotZeroCnt(int[][] curMap) {
		int sum = 0;
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (curMap[j][i] != 0) {
					sum++;
				}
			}
		}
		return sum;
	}

	public static void findTargetAndBreak(int y, int[][] curMap) {
		for (int i = 0; i < H; i++) {
			// 최상의 벽돌 발견
			if (curMap[i][y] != 0) {
				breakMap(i, y, curMap);
				return;
			}
		}
	}

	private static void breakMap(int sx, int sy, int[][] curMap) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { sx, sy, curMap[sx][sy] });
		while (!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.peek()[1];
			int val = queue.peek()[2];
			queue.poll();

			for (int k = 0; k < 4; k++) {
				for (int i = 1; i < val; i++) {
					int dx = x + i * dix[k];
					int dy = y + i * diy[k];
					if (dx >= 0 && dx < H && dy >= 0 && dy < W) {
						if (curMap[dx][dy] > 1) {
							queue.add(new int[] { dx, dy, curMap[dx][dy] });
						}
						curMap[dx][dy] = 0;
					}
				}
			}
			curMap[x][y] = 0;
		}
	}

	private static void input() throws IOException {
		answerCnt = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
