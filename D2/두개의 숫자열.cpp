두개의 숫자배열이 주어진다면 서로 옆으로 이동하면서 서로 같은 위치선상에 있는 값들을 곱하고 그 곱한값들의 합이 최대로 되는 경우를 구하는 문제이다.
알고리즘 적으로 구현을 어렵지 않았지만 계속해서 다른 답이 나오기에 하나하나 뜯어 보려고했다.
문제를 해결하면서 벡터를 만들어주는 메서드를 따로 구현하였다.
이 메서드를 호출하고 스택에 쌓이는 과정에서 오류가 발생하였다.
예를 들어서, makeVector(5)라는 의미는 길이가 5개의 값을 입력받고 그 값으로 벡터를 생성하는 메서드이다.
하지만 makeVector(5)를 호출하는 코드를 그대로 함수안에 넣어주게되었다.
  -> function(makeVector(5),makeVector(10)) 과 같이
차례대로 입력을 해주면 문제 없다고 생각했던 것과 다르게 다른 값이 저장되고 있었다.
  그 이유는 스택과 관련해서 알 수 있었다.
  makeVector(5)를 호출하면 밑에 쌓이게 되고 그 위에 makeVector(10)이 쌓이고 최종적으로 makeVector(10)이 먼저 호출당하는 것이었다.
5개를 입력하면 되는지알았지만 10개의 벡터를 생성하고 있었기에 오류가 발생했던 것이다.
이러한 문제를 해결하고자, 방지하고자 앞으로는 함수에 함수를 넣을때는 다시 한번 흐름을 확인해보고 구현하자.
이와 같은 문제를 해결하면서 중복된 코드도 없애기 위해서
vector<int> arrA = initializedVector(A);
	vector<int> arrB = initializedVector(B);
위와 같이 미리 생성해주고 사용하는 방식을 사용하였다.

#include<iostream>
#include <vector>

using namespace std;

int findAnswer(vector<int> arrA, vector<int> arrB) {
	int answer = -987654321;
	int addValue = 0;
	while (arrA.size() + addValue <= arrB.size()) {
		int sum = 0;
		for (int i = 0; i < arrA.size(); i++) {
			sum += arrA[i] * arrB[i + addValue];
		}
		answer = max(answer, sum);
		addValue++;
	}
	return answer;
}

vector<int> initializedVector(int count) {
	vector<int> vec(count);
	for (int i = 0; i < count; i++) {
		cin >> vec[i];
	}
	return vec;
}

void printAnswer(int T, int answer) {
	cout << "#" << T << " " << answer << endl;
}

void solve(int T) {
	int A, B;
	cin >> A >> B;
	int answer = 0;
	vector<int> arrA = initializedVector(A);
	vector<int> arrB = initializedVector(B);
	if (A < B) {
		answer = findAnswer(arrA, arrB);
	}
	else {
		answer = findAnswer(arrB, arrA);
	}
	printAnswer(T, answer);
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
