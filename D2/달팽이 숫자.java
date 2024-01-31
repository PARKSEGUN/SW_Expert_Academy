탐색할 수 있는 곳인지 확인한뒤에 가능하다면 같은 방향으로 진행하고 불가능하다면 방향을 변경해서 진행한다.

----------코드----------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int T, N;
    static StringTokenizer tokenizer;
    static StringBuilder resultString = new StringBuilder();
    static int[] diy = { 0, 1, 0, -1 };
    static int[] dix = { 1, 0, -1, 0 };
    static int map[][];
 
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= T; i++) {
            resultString.append("#").append(i).append("\n");
            N = Integer.parseInt(reader.readLine());
            map = new int[N][N];
            돌아라달팽아(1, 0, 0, 0);
            printMap();
        }
        System.out.println(resultString);
    }
 
    private static void 돌아라달팽아(int cnt, int y, int x, int dir) {
        if (!isLocatedMap(y, x) || map[y][x] != 0) {
            return;
        }
        map[y][x] = cnt;
        int dy = y + diy[dir];
        int dx = x + dix[dir];
        if (isLocatedMap(dy, dx) && map[dy][dx] == 0) {
            돌아라달팽아(cnt + 1, dy, dx, dir);
        } else {
            dir = (dir + 1) % 4;
            돌아라달팽아(cnt + 1, y + diy[dir], x + dix[dir], dir);
        }
    }
 
    private static boolean isLocatedMap(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
 
    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                resultString.append(map[i][j]).append(" ");
            }
            resultString.append("\n");
        }
    }
 
}
