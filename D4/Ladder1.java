메모리 : 29,668 kb     실행시간 : 139ms

1. 밑으로 이동
2. 이동중 왼쪽이나 오른쪽에 길이 있으면 우선적으로 이동
3. '2' 문자를 찾을때까지 1, 2 반복

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int T;
    static boolean[][] map = new boolean[111][111];
    static int ry, rx;
    static StringBuilder builder;
 
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
    public static void main(String[] args) throws IOException {
        for (int k = 0; k < 10; k++) {
            setting();
            builder = new StringBuilder();
            builder.append("#").append(T).append(" ");
            for (int i = 0; i < 100; i++) {
                if (map[0][i] == true && searchMap(0, i)) {
                    builder.append(i).append("\n");
                    break;
                }
            }
            System.out.println(builder);
        }
 
    }
 
    private static boolean searchMap(int y, int x) {
        if (y == 99) {
            if (x == rx) {
                return true;
            }
            return false;
        }
 
        if (x - 1 >= 0 && map[y][x - 1] == true) {
            while (x - 1 >= 0 && map[y][x - 1] == true) {
                x--;
            }
        } else if (x + 1 <= 99 && map[y][x + 1] == true) {
            while (x + 1 <= 99 && map[y][x + 1] == true) {
                x++;
            }
        }
        return searchMap(y + 1, x);
 
    }
 
    private static void setting() throws IOException {
        String tmp = reader.readLine();
        T = Integer.parseInt(tmp);
        for (int i = 0; i < 100; i++) {
            String tString = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(tString);
            for (int j = 0; j < 100; j++) {
                String string = tokenizer.nextToken();
                if (string.equals("0")) {
                    map[i][j] = false;
                } else if (string.equals("2")) {
                    ry = i;
                    rx = j;
                    map[i][j] = true;
                } else if (string.equals("1")) {
                    map[i][j] = true;
                }
            }
        }
    }
 
}
