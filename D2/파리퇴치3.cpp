처음 문제를 확인하고 가운데에서 부터 대각선으로 펼쳐지는 모양과 십자가로 펼쳐지는 모양, 2가지의 경우중 최대의 값을 추출할 수 있는 경우를 확인하는 문제인지 알았다.
때문에 두가지의 경우를 확인할 수 있는 메서드를 설계한 후에 문제를 해결했지만 예제에서 알려주는 값과 다르다는것을 알고 문제를 다시 확인했다.
주어진 두가지의 모양이 꼭 중심에서 시작하는 것이 어느 곳에서도 지정할 수 있었다는 것을알게되었고 2차원 배열의 왼쪽위부분에서 부터 오른쪽 아래까지 탐색하면서
탐색중인 부분에서 2가지의 방법대로 펼쳐졌을때에 최댓값을 구하는 방식으로 다시 해결하였다.

---
#include<iostream>

using namespace std;

int findMaxValue(int x,int y,int N, int M, int map[][20]) {
	int sum = map[x][y];
	for (int i = 1; i < M; i++) {
		if (y - i >= 0) {
			sum += map[x][y - i];
		}
		if (y + i < N) {
			sum += map[x][y + i];
		}
		if (x - i >= 0) {
			sum += map[x - i][y];
		}
		if (x + i < N) {
			sum += map[x + i][y];
		}
	}
	int result = sum;
	sum = map[x][y];
	for (int i = 1; i < M; i++) {
		if (x - i >= 0 && y - i >= 0) {
			sum += map[x - i][y - i];
		}
		if (x - i >= 0 && y + i<N) {
			sum += map[x - i][y + i];
		}
		if (x +i<N && y - i >= 0) {
			sum += map[x + i][y - i];
		}
		if (x +i<N&&y+i<N) {
			sum += map[x + i][y + i];
		}
	}
	return max(result, sum);
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin >> T;
	for (test_case = 1; test_case <= T; ++test_case)
	{
		int N, M;
		cin >> N >> M;
		int result = 0;
		int map[20][20] = { 0, };
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cin >> map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result=max(result,findMaxValue(i, j, N,M, map));
			}
		}
		cout << "#" << test_case << " " << result << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
