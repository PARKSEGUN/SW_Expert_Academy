메모리 : 21,356kb
실행시간 : 126 ms

우선순위큐를 사용해서 덤프 횟수만큼 가장 큰값을 제거하고 -1 한값을 추가 가장 작은 값을 제거하고 +1한값으로 설정

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Solution {
 
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int dump = 0;
    static int[] height = new int[100];
    static StringBuilder builder = new StringBuilder();
 
    public static void main(String[] args) throws IOException {
        for (int t = 1; t <= 10; t++) {
            setting(t);
            builder.append(solution()).append("\n");
        }
        System.out.println(builder);
    }
 
    private static int solution() {
        PriorityQueue<Integer> ascending = new PriorityQueue<>();
        PriorityQueue<Integer> descending = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < 100; i++) {
            ascending.add(height[i]);
            descending.add(height[i]);
        }
        for (int i = 0; i < dump; i++) {
            int tmp = ascending.poll();
            ascending.add(tmp + 1);
            tmp = descending.poll();
            descending.add(tmp - 1);
        }
        return descending.peek() - ascending.peek();
    }
 
    private static void setting(int t) throws IOException {
        builder.append("#").append(t).append(" ");
        dump = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < 100; i++) {
            height[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}
