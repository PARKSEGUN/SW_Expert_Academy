각각의 지역에 대한 정보와 거리와 주어진 E 의 값으로 간선의 정보를 추출한뒤 모든 간선을 저장한다.
간선으로 크루스칼 알고리즘을 사용해서 해결

  package com.ssafy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		int num, x, y;

		public Point(int num, int x, int y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}

	}

	static class Edge implements Comparable<Edge> {
		Point from;
		Point to;
		double value;

		public Edge(Point from, Point to, double value) {
			super();
			this.from = from;
			this.to = to;
			this.value = value;
		}

		@Override
		public String toString() {
			return "From : " + from.num + " | to : " + to.num + " | value : " + value;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.value, o.value);
		}

	}

	static int T, N;
	static double E;
	static List<Edge> edgeList;
	static int topParent[];
	static double answer;

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(reader.readLine());
		for (int t = 0; t < T; t++) {
			setting();
			Collections.sort(edgeList);
//			for (int i = 0; i < edgeList.size(); i++) {
//				System.out.println(edgeList.get(i));
//			}
			int cnt = 0;
			for (int i = 0; i < edgeList.size(); i++) {
				int num1 = edgeList.get(i).from.num;
				int num2 = edgeList.get(i).to.num;
				if (find(num1) != find(num2)) {
					// System.out.println(num1 + " " + num2);
					union(num1, num2);
					answer += edgeList.get(i).value;
					cnt++;
					if (cnt == N - 1) {
						break;
					}
				}
			}
			answerString.append("#").append(t + 1).append(" ").append(Math.round(answer)).append("\n");
			answer = 0;
		}
		System.out.println(answerString);
	}

	private static void union(int num1, int num2) {
		int topParent1 = find(num1);
		int topParent2 = find(num2);
		topParent[topParent1] = topParent2;
	}

	private static int find(int num) {

		if (num == topParent[num]) {
			return num;
		}
		return topParent[num] = find(topParent[num]);
	}

	private static void setting() throws IOException {
		N = Integer.parseInt(reader.readLine());
		edgeList = new ArrayList<Edge>();
		int[] x = new int[N];
		int[] y = new int[N];
		topParent = new int[N];
		edgeList = new ArrayList<>();
		tokenizer = new StringTokenizer(reader.readLine());
		for (int j = 0; j < N; j++) {
			x[j] = Integer.parseInt(tokenizer.nextToken());
		}
		tokenizer = new StringTokenizer(reader.readLine());
		for (int j = 0; j < N; j++) {
			y[j] = Integer.parseInt(tokenizer.nextToken());
		}
		E = Double.parseDouble(reader.readLine());

//		간선의 정보를 저장
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double value = E * (Math.pow(Math.abs(x[i] - x[j]), 2) + Math.pow(Math.abs(y[i] - y[j]), 2));
				edgeList.add(new Edge(new Point(i, x[i], y[i]), new Point(j, x[j], y[j]), value));
			}
		}
		// 자기 자신을 초기의 부모로 지정
		for (int i = 0; i < N; i++) {
			topParent[i] = i;

		}
	}

}
