주어진 정사각형 모양의 2차원 숫자 배열을 시계방향으로 돌렸을때에 어떤 모양이되는지를 확인하는 문제이다.
  90도 180도 270도 돌렸을때의 모양을 판단한다.
  돌렸을때에 규칙을 알아보던중 newMap[j][N - i - 1] = map[i][j]; 라는 규칙을 찾게되었고 이 규칙을 적용시켜서 해결해보았다.
또한 출력에 대한 문제에서도 순서대로 출력하는 것과는 다른 표현이었기에 이를 해결하기위해서 2차원벡터를 사용해서 해결했다.

  #include<iostream>
#include <vector>

using namespace std;

void turnRight(int N, int map[][10], vector<vector<int>>& vec) {
	int newMap[10][10] = { 0, };
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			newMap[j][N - i - 1] = map[i][j];
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = newMap[i][j];
			vec[i].push_back(map[i][j]);
		}
	}
}

void printAnswer(int T,int N,vector<vector<int>> answer) {
	cout << "#" << T << endl;
	for (int i = 0; i < answer.size(); i++) {
		for (int j = 0; j < answer[i].size(); j++) {
			if (j != 0 && j % N == 0) {
				cout << " ";
			}
			cout << answer[i][j];
		}
		cout << endl;
	}

}

void solve(int T) {
	int N;
	cin >> N;
	int map[10][10];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
		}
	}
	vector<vector<int>> answer(N);
	turnRight(N, map, answer);	//90도
	turnRight(N, map, answer);	//180도
	turnRight(N, map, answer);	//270도
	printAnswer(T,N,answer);
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
