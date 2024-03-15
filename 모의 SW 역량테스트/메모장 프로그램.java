메모장이 주어지고 메모장의 높이야 너비가 주어진다.
시작은 왼쪽위에 커서가 위치하고 문자열이 주어진다.

기능
- 현재 커서에 값 추가(입력 한 값에 오른쪽에 커서위치)
- 커서 이동(주어진 좌표의 왼쪾에 커서위치, 커서의 오른쪽 값 리턴)
- 현재 커서를 기준으로 문자열끝까지 중에 주어진 문자의 개수(개수 리턴)

확인해야할 점
  1. 현재 커서 위치의 값을 알아야한다.
  2. 커서가 위치한 행의 정보를 탐색할 수 있어야한다.
  3.  중간에 값을 추가할 수 있어야한다.
  4. 문자의 개수를 파악해야한다.

  이를 리스트로 구현해서 해결하려고 하면 주어지는 범위때문에 시간초과가 발생할 것이라고 생각
  값을 추가할때는 커서가 위치한 행만 확인하고 그 밑의 행부터는 맨 앞과 맨 뒤를 이용해서 값을 옮겨주면 된다고 생각 -> Deque 사용
  현재의 커서의 행의 문자를 확인하기위해서는 foreach문을 사용해서 탐색가능
  커서에 값을 추가할때는 커서까지 값을 pop 해주고 값 추가 후 다시 pop했던 값을 넣어준다.
  Deque를 사용해서 1, 2, 3 번 해결
  4번을 해결하기위해서 모든 내용을 탐색한다면 시간초과 발생 -> 각 행의 문자의 정보를 저장 ex) row[x][0] : x번째 행에 a(0번째)의 개수 저장

  추가로 커서가 마지막 값의 오른쪽에 위치할때를 예외로 잡아줘야한다.

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

class UserSolution {

	Deque<Character> deque[];
	int[][] countByAlphabet;
	int x, y, N, M;

	void init(int H, int W, char mStr[]) {
		N = H;
		M = W;
//		System.out.println("asdf");
		x = 1;
		y = 1;
		deque = new Deque[H + 1];
		for (int i = 0; i < H + 1; i++) {
			deque[i] = new ArrayDeque<>();
		}
		countByAlphabet = new int[H + 1][26];
		int i = 0;
		int height = 1;
		int cnt = 0;
		while (mStr[i] >= 'a' && mStr[i] <= 'z') {
			deque[height].add(mStr[i]);
			countByAlphabet[height][mStr[i] - 'a']++;
			cnt++;
			if (cnt == W) {
				height++;
				cnt = 0;
			}
			i++;
		}
//		for (int j = 0; j < H + 1; j++) {
//			System.out.println(deque[j]);
//		}
//		for (int j = 0; j < H + 1; j++) {
//			for (int k = 0; k < 26; k++) {
//				System.out.print(countByAlphabet[j][k] + " ");
//			}
//			System.out.println();
//		}
	}

	void insert(char mChar) {
		// 현재 커서(x,y)에 문자를 입력한다.
		// 커서가 마지막에 있을때를 구별해줘야한다.
		// 커서가 마지막일때
		int curHeight = x;
		// y칸에 들어가기위해 queue에 원소 잠깐 빼두기
		Stack<Character> stack = new Stack();
//		System.out.println(x + " " + y + " " + M);
//		System.out.println("size " + deque[curHeight].size());
		for (int i = 1; i < y; i++) {
//			System.out.println(deque[curHeight].size());
			stack.add(deque[curHeight].pollFirst().charValue());
		}
		// 그자리에 값 넣어주기 & 커서 이동 & 알파벳 카운트 올려주기
		deque[curHeight].addFirst(mChar);
		y++;
		if (y > M + 1) {
			x++;
			y = 2;
		}
		countByAlphabet[curHeight][mChar - 'a']++;
		// 다시 채워주기
		while (!stack.isEmpty()) {
			deque[curHeight].addFirst(stack.pop());
		}
		// 입력가능한 넓이가 넘었다면 밑으로 넘겨주기 & 알파벳 카운트 조정
		while (deque[curHeight].size() > M) {
			countByAlphabet[curHeight][deque[curHeight].peekLast() - 'a']--;
			countByAlphabet[curHeight + 1][deque[curHeight].peekLast() - 'a']++;
			deque[curHeight + 1].addFirst(deque[curHeight].pollLast());
			curHeight++;
		}
//		System.out.println("insert " + x + " " + y);
//		for (int i = 1; i < N + 1; i++) {
//			System.out.println(deque[i]);
//		}

	}

	char moveCursor(int mRow, int mCol) {
		// row,col 문자의 왼쪽에 커서를 이동시킨다.
		x = mRow;
		y = mCol;
		int i = 1;
		for (Character c : deque[x]) {
			if (i == mCol) {
//				System.out.println("moveCursor " + x + " " + y);

				return c.charValue();
			}
			i++;
		}
		// 그자리에 값이 없다면 마지막 문자열을 찾는다.
		// 있을때까지
		while (deque[x].size() == 0) {
			x--;
		}
		// x번째 행에는 값이 들어있다.
		y = deque[x].size() + 1;
//		System.out.println("moveCursor못찾음 " + x + " " + y);
		return '$';
	}

	int countCharacter(char mChar) {
		// 커서 뒤쪽부터 끝까지 입력받은 문자의 개수를 리턴
		int cnt = 0;
		int i = 1;
//		System.out.println(x);
//		System.out.println(deque[x].size());
		for (Character c : deque[x]) {
			if (y <= i && c.charValue() == mChar) {
				cnt++;
			}

			i++;
		}
		for (int j = x + 1; j < N + 1; j++) {
			cnt += countByAlphabet[j][mChar - 'a'];
		}
//		System.out.println("count " + x + " " + y);
//		System.out.println(cnt);
		return cnt;
	}
}
