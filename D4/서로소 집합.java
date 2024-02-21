Union-Find 알고리즘을 이용한 구현
Union을 통한 정점 연결
  a는 부모인 parents[a]와 연결되어있다.
  parents[a]는 그 부모인 parents[parents[a]]와 연결되어있다.
  이 구조를 재귀함수를 통해서 구현한다.
  구현하는 중에 parents에 직접적인 부모가 아닌 최상의 부모를 찾아서 정점끼리의 최상의 부모가 같은지를 확인하는 것이 Union-Find 알고리즘
  때문에 최상위 부모를 찾으면서 최상의 부모를 저장해주기위해
  return parents[a]=findParent(parents[a]); 를 구현
  위 코드로 a의 위로있는 모든 부모들의 parents에 최상위 정점이 저장이된다.
  a와 b 를 같은 그룹으로 연결해주고 싶다면 a의 최상위 정점과 b의 최상위 정점을 연결해준다.


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M;
	static int parents[];

	static StringBuilder answerString = new StringBuilder();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(reader.readLine());
		for (int t = 0; t < T; t++) {
			tokenizer = new StringTokenizer(reader.readLine());
			N = Integer.parseInt(tokenizer.nextToken());
			M = Integer.parseInt(tokenizer.nextToken());
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}
			answerString.append("#").append(t + 1).append(" ");
			for (int i = 0; i < M; i++) {
				tokenizer = new StringTokenizer(reader.readLine());
				int a = Integer.parseInt(tokenizer.nextToken());
				int b = Integer.parseInt(tokenizer.nextToken());
				int c = Integer.parseInt(tokenizer.nextToken());
				if (a == 0) {
					union(b, c);
				} else {
					answerString.append((isConnected(b, c)) ? 1 : 0);
				}
			}
			answerString.append("\n");
		}
		System.out.println(answerString);
	}

	static void union(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		parents[b] = a;

		/*
		 * if (a < b) { parents[b] = a; } else { parents[a] = b; }
		 */
	}

	static int findParent(int a) {

		if (a == parents[a]) {
			return a;
		}
		return parents[a] = findParent(parents[a]);
	}

	static boolean isConnected(int b, int c) {
		b = findParent(b);
		c = findParent(c);
		if (b == c)
			return true;
		return false;
	}
}
