메모리 : 20812kb
실행시간 : 129ms

탐색해서 더해주어야 하는 범위를 지정해서 탐색


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int map[][];
	static List<Range> rangeList;
	static int T, N, result;
	static StringBuilder builder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(reader.readLine());
		for (int t = 1; t <= T; t++) {
			rangeList = new ArrayList<>();
			setting(t);
			makeRangeList();
			solve();
			builder.append(result).append("\n");
		}
		System.out.println(builder);
	}

	private static void solve() {
		result = 0;
		for (int i = 0; i < N; i++) {
			int start = rangeList.get(i).getStart();
			int end = rangeList.get(i).getEnd();
			for (int j = start; j <= end; j++) {
				result += map[i][j];
			}

		}
	}

	private static void makeRangeList() {
		int mid = N / 2;
		int tmp = -1;
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			if (flag == false) {
				tmp++;
			} else {
				tmp--;
			}
			if (tmp == mid) {
				flag = true;
			}
			rangeList.add(new Range(mid - tmp, mid + tmp));
		}

	}

	private static void setting(int t) throws IOException {
		builder.append("#").append(t).append(" ");
		N = Integer.parseInt(reader.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String tmp = reader.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
	}

}

class Range {
	private int start;
	private int end;

	public Range(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Range [start=" + start + ", end=" + end + "]";
	}

}
