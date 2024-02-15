충전기 범위를 저장하려하면 복잡해지는 것을 확인
이동할 수 있는 경로가 주어지기 때문에 이동을 따라가면서 A와 B의 모든 충전 가능한 경우의 수 중에서 가장 큰 값을 리턴



package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int T;
	static int M, K;
	static int moveA[];
	static int moveB[];
	static int bcs[][];
	static int[] dix = { 0, -1, 0, 1, 0 };
	static int[] diy = { 0, 0, 1, 0, -1 };
	static int answer;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(reader.readLine());
		for (int t = 0; t < T; t++) {
			setting();
			startMoving();
			answerString.append("#").append(t + 1).append(" ").append(answer).append("\n");
			answer = 0;
		}
		System.out.println(answerString);
	}

	private static void startMoving() {
		int ax = 0, ay = 0, bx = 9, by = 9;
		calCharging(ax, ay, bx, by);
		for (int i = 0; i < M; i++) {
			ax += dix[moveA[i]];
			ay += diy[moveA[i]];
			bx += dix[moveB[i]];
			by += diy[moveB[i]];
			calCharging(ax, ay, bx, by);
		}
	}

	private static void calCharging(int ax, int ay, int bx, int by) {
		List<Integer> aCase = new ArrayList<>();
		List<Integer> bCase = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			int aDis = Math.abs(ax - bcs[i][0]) + Math.abs(ay - bcs[i][1]);
			int bDis = Math.abs(bx - bcs[i][0]) + Math.abs(by - bcs[i][1]);
			// System.out.println(bDis);
			if (aDis <= bcs[i][2]) {
				aCase.add(i);
			}
			if (bDis <= bcs[i][2]) {
				bCase.add(i);
			}
		}

		int finalSum = 0;
		if (aCase.size() != 0) {
			for (int i = 0; i < aCase.size(); i++) {
				int sum = bcs[aCase.get(i)][3];
				for (int j = 0; j < bCase.size(); j++) {
					if (aCase.get(i) == bCase.get(j)) {
						finalSum = Math.max(finalSum, (sum + bcs[bCase.get(j)][3]) / 2);
					} else {
						finalSum = Math.max(finalSum, sum + bcs[bCase.get(j)][3]);
					}
				}
				finalSum = Math.max(finalSum, sum);
			}
		} else {
			for (int i = 0; i < bCase.size(); i++) {
				finalSum = Math.max(finalSum, bcs[bCase.get(i)][3]);
			}
		}
		// System.out.println(finalSum);
		answer += finalSum;
	}

	private static void setting() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		M = Integer.parseInt(tokenizer.nextToken());
		moveA = new int[M];
		moveB = new int[M];
		K = Integer.parseInt(tokenizer.nextToken());
		bcs = new int[K][4];

		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < M; i++) {
			moveA[i] = Integer.parseInt(tokenizer.nextToken());
		}
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < M; i++) {
			moveB[i] = Integer.parseInt(tokenizer.nextToken());
		}
		for (int i = 0; i < K; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			bcs[i][1] = Integer.parseInt(tokenizer.nextToken()) - 1;
			bcs[i][0] = Integer.parseInt(tokenizer.nextToken()) - 1;
			bcs[i][2] = Integer.parseInt(tokenizer.nextToken());
			bcs[i][3] = Integer.parseInt(tokenizer.nextToken());
		}
	}
}
