입력으로 주어진 수들의 집합중에서 가장 많이 입력된 수를 최빈수라고 정의하고 이 값을 찾아내는 문제이다.
이러한 문제들을 값을 배열의 인덱스로 생각해서 해결했던 적이 많았기에 적용시켜보았다.

#include<iostream>
#include <vector>
 
using namespace std;
 
void solve(int T) {
    int count;
    cin >> count;
    int scoreCount[111] = { 0, };
    for (int i = 0; i < 1000; i++) {
        int score;
        cin >> score;
        scoreCount[score]++;
    }
    int answerCount = 0;
    int answerValue = 0;
    for (int i = 0; i <= 100;i++) {
        if (scoreCount[i] >= answerCount) {
            answerCount = scoreCount[i];
            answerValue = i;
        }
    }
    cout << "#" << T << " " << answerValue<<endl;
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
