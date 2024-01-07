#수도쿠 검증
문제를 요약하자면 주어진 수도쿠의 값들이 올바르게 지정되어있는지를 확인하는 문제였다.
행, 열, 박스(네모칸으로 이루어진 범위) 를 확인해서 1~9 까지의 수가 올바르게 들어있는지를 확인하면 된다.
각각에 맞게 메서드를 설계해주었다.
----------------------------------------------------------------------------------------------------
#include<iostream>
#include <vector>

using namespace std;

//행과 열을 확인하는 과정을 하나로 설계한다면 중복을 줄일 수 있는데 효율적인가 고민

bool isColumnCorrect(int sudoku[][9]) {
	for (int i = 0; i < 9; i++) {
		bool checkNumber[10] = { 0, };	//모든 배열이 false로 초기화되는지 확인하기
		for (int j = 0; j < 9; j++) {
			if (checkNumber[sudoku[j][i]] == true) {
				return false;
			}
			checkNumber[sudoku[j][i]] = true;
		}
	}
	return true;
}

bool isRowCorrect(int sudoku[][9]) {
	for (int i = 0; i < 9; i++) {
		bool checkNumber[10] = { 0, };	//모든 배열이 false로 초기화되는지 확인하기
		for (int j = 0; j < 9; j++) {
			if (checkNumber[sudoku[i][j]] == true) {
				return false;
			}
			checkNumber[sudoku[i][j]] = true;
		}
	}
	return true;
}

bool isBoxCorrect(int sudoku[][9]) {
	pair<int, int> startPoint[9] = {
		{0,0},{0,3},{0,6},
		{3,0},{3,3},{3,6},
		{6,0},{6,3},{6,6}
	};
	for (int startIdx = 0; startIdx < 9; startIdx++) {
		bool checkNumber[10] = { 0, };	//모든 배열이 false로 초기화되는지 확인하기
		int x = startPoint[startIdx].first;
		int y = startPoint[startIdx].second;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (checkNumber[sudoku[i][j]] == true) {
					return false;
				}
				checkNumber[sudoku[i][j]] = true;
			}
		}

	}
	return true;
}

bool isSudokuCorrect(int sudoku[][9]) {
	return isColumnCorrect(sudoku) && isRowCorrect(sudoku) && isBoxCorrect(sudoku);
}

bool solve() {
	int sudoku[9][9] = { 0, };
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			cin >> sudoku[i][j];
		}
	}
	return isSudokuCorrect(sudoku);
}

void printAnswer(int T, bool answer) {
	cout << "#" << T << " " << answer << endl;
}

int main(void) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int T;
	cin >> T;
	for (int i = 1; i <= T; i++) {
		printAnswer(i, solve());
	}
}
