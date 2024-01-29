입력받은 배열을 순차적으로 탐색하며 같은수인지 판단한뒤 다르다면 0은 1로 1은 0 으로 변경해준다.

  ----------코드----------
  import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
    static int T;
 
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
        T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append("#").append(i + 1).append(" ");
            int answer = 0;
            String string = reader.readLine();
            char[] target = string.toCharArray();
            char[] arr = new char[target.length];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = '0';
            }
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != target[j]) {
                    answer++;
                    opposite(arr);
                }
            }
            builder.append(answer);
            System.out.println(builder);
        }
    }
 
    static private void opposite(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                arr[i] = '1';
            } else {
                arr[i] = '0';
            }
        }
    }
}
