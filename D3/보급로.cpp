문제는 map이 입력되었을때 왼쪽위에서 출발해서 오른쪽 아래를 목적지로 할때 map에 입력된 수들을 더하면서 지나갈 것이고 이 값이 최소가 되어야한다는 것이다.
처음에 이 문제를 이동방향이 오른쪽과 아래쪽 밖에 없는지 알고 DP로 해결하려 시도했다.
하지만 후에 답이 다른것을 확인했고 이동 방향이 위아래오른쪽아래로 총 4가지 방향이 있다는 것을 알았고 이는 DPS 또는 BPS로 해결해야된다고 생각했다 하지만 완전탐색이 필요하다고 생각해서 DPS로 해결해보려한다.
DPS로 해결하려했지만 시간초과가 발생했고 DPS를 구현하면서 DPS와 비슷한 로직으로 DP를 구현해 볼 수 있을 것 같아 구현해서 시간을 줄일 수 있었다.
하지만 이 또한 시간초과가 발생했고 원하는 풀이법이 아니라고 생각했다.
모든 경우를 확인하는 또 다른 알고리즘으로 BFS를 생각했고 구현해서 해결하였다.
하지만 다른 사람들의 결과를 보고 내 코드가 속도가 많이 느리다고 생각했고
이유를 찾던중 sumQueue라는 것을 구현한 부분에서 차이가있었다.
왜 sum으로 조건을 거는것과 dp를 사용해서 걸어주는 것에 속도차이가 있을까 생각하던중에 sum으로 조건을 걸게된다면 다음 큐에서 나올때 이전에 큐에 넣어주었던 값이 그대로 나오지만
dp를 조건으로 사용해준다면 queue에 넣어주는 것과 상관없이 더 낮은 값으로 계속 초기화 될 수 있었고 이에 속도를 줄일 수 있었다.

  -------------------------------------DP로 시도했던 코드----------------------------
  #include<iostream>
#include <vector>
#include<queue>
#include <climits>
#include<cstring>

using namespace std;

void initializeDpMap(int N,int dpMap[][111]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			dpMap[i][j] = INT_MAX;
		}
	}
}
void inputToMap(int N,int map[][111]) {
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < N; j++) {
			map[i][j] = str[j] - '0';
		}
	}
}

void solve(int T) {
	int N;
	cin >> N;
	int map[111][111];
	int dpMap[111][111];
	inputToMap(N,map);
	initializeDpMap(N, dpMap);
	dpMap[0][0] = map[0][0];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (i -1>=0) {
				dpMap[i][j] = min(dpMap[i][j], dpMap[i - 1][j] + map[i][j]);
			}
			if (j - 1 >= 0) {
				dpMap[i][j] = min(dpMap[i][j], dpMap[i][j-1] + map[i][j]);
			}
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << dpMap[i][j] << " ";
		}cout << endl;
	}
	cout << "#" << T << " " << dpMap[N - 1][N - 1]<<endl;
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int T;
	cin >> T;
	for (int i = 1; i <= T; i++) {
		solve(i);
	}
}
-----------------------------------BFS 코드----------------------------------
  #include<iostream>
#include <vector>
#include<cstring>
#include<climits>
#include<queue>

using namespace std;

int dix[4] = { 0,0,1,-1 };
int diy[4] = { 1,-1,0,0 };
int answer2 = 0;
void inputToMap(int N, int map[][111]) {
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < N; j++) {
			map[i][j] = str[j] - '0';
		}
	}
}



void settingDpArr(int N, int dp[][111]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			dp[i][j] = INT_MAX;
		}
	}
}

void findMinValue(int xx, int yy, int N, int map[][111], int dp[][111]) {
	queue<pair<int, int>> pointQueue;
	queue<int> sumQueue;
	pointQueue.push({ xx,yy });
	sumQueue.push(0);
	while (pointQueue.size() > 0) {
		int x = pointQueue.front().first;
		int y = pointQueue.front().second;
		int sum = sumQueue.front();
		pointQueue.pop();
		sumQueue.pop();
		for (int i = 0; i < 4; i++) {
			int dx = x + dix[i];
			int dy = y + diy[i];
			if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
				if (sum + map[dx][dy] < dp[dx][dy]) {
					dp[dx][dy] = sum + map[dx][dy];
					pointQueue.push({ dx,dy });
					sumQueue.push(sum + map[dx][dy]);
				}
			}
		}
	}

}

void solve(int T) {
	int N;
	cin >> N;
	int map[111][111];
	int dp[111][111];
	inputToMap(N, map);
	settingDpArr(N, dp);
	dp[0][0] = 0;
	findMinValue(0, 0, N, map, dp);
	cout << "#" << T << " " << dp[N - 1][N - 1] << endl;
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int T;
	cin >> T;
	for (int i = 1; i <= T; i++) {
		solve(i);
	}
}
  
------------sumQueue를 제거하고 dp를 기준으로 설정해서 최적화---------------
	#include<iostream>
#include <vector>
#include<cstring>
#include<climits>
#include<queue>

using namespace std;

int dix[4] = { 0,0,1,-1 };
int diy[4] = { 1,-1,0,0 };
int answer2 = 0;
void inputToMap(int N, int map[][111]) {
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < N; j++) {
			map[i][j] = str[j] - '0';
		}
	}
}



void settingDpArr(int N, int dp[][111]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			dp[i][j] = INT_MAX;
		}
	}
}

void findMinValue(int xx, int yy, int N, int map[][111], int dp[][111]) {
	queue<pair<int, int>> pointQueue;
	queue<int> sumQueue;
	pointQueue.push({ xx,yy });
	while (pointQueue.size() > 0) {
		int x = pointQueue.front().first;
		int y = pointQueue.front().second;
		pointQueue.pop();
		for (int i = 0; i < 4; i++) {
			int dx = x + dix[i];
			int dy = y + diy[i];
			if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
				if (dp[x][y] + map[dx][dy] < dp[dx][dy]) {
					dp[dx][dy] = dp[x][y] + map[dx][dy];
					pointQueue.push({ dx,dy });
				}
			}
		}
	}

}

void solve(int T) {
	int N;
	cin >> N;
	int map[111][111];
	int dp[111][111];
	inputToMap(N, map);
	settingDpArr(N, dp);
	dp[0][0] = 0;
	findMinValue(0, 0, N, map, dp);
	cout << "#" << T << " " << dp[N - 1][N - 1] << endl;
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int T;
	cin >> T;
	for (int i = 1; i <= T; i++) {
		solve(i);
	}
}
