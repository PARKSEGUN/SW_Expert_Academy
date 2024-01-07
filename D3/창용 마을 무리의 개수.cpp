사람의 수와 사람들과의 연결 정보가 주어졌을떄에 몇개의 그룹으로 나눠지는지 구하는 문제였다.
문제를 보고 BFS를 사용해서 해결할 수 있다고 생각하였고 적용시켜보았다.
  먼저 정보를 저장할떄에는 인덱스를 사람이라고 생각하고 vector를 사용했다.
  예시 -> vec[5] 는 5번의 사람과 연결되어있는 사람들의 번호를 저장
후에 BFS를 사용해서 해결

#include<iostream>
#include <vector>
#include<queue>

using namespace std;

void findPeople(int start,vector<vector<int>>& connectInfo,vector<bool>& visited) {
	queue<int> people;
	people.push(start);
	visited[start] = true;
	while (people.size() > 0) {
		int num = people.front();
		people.pop();
		for (auto x : connectInfo[num]) {
			if (visited[x] == false) {
				people.push(x);
				visited[x] = true;
			}
		}
	}
}

void solve(int T) {
	int N, M;
	cin >> N >> M;
	vector<vector<int>> connectInfo(N+1);
	vector<bool> visited(N+1, false);
	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		connectInfo[a].push_back(b);
		connectInfo[b].push_back(a);
	}
	int answer = 0;
	for (int i = 1; i <= N; i++) {
		if (visited[i] == false) {
			answer++;
			findPeople(i, connectInfo,visited);
		}
	}
	cout << "#" << T << " " << answer << endl;
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
