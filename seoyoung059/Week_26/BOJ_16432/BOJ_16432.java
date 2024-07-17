package Week_26.BOJ_16432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_16432 {

    static int solve(int n, int[][] dp, boolean[][] arr) {

        int idx1, idx2;
        int check = -1;
        for (int i = 1; i < n + 1; i++) {

            // 이전 과정에서 가능했던 2가지 경우 확인
            idx1 = 0;
            idx2 = 0;
            check = -1;
            if(i > 1) {
                for (int j = 1; j < 10; j++) {
                    if (dp[i - 1][j] > 0) {
                        idx2 = idx1;
                        idx1 = j;
                    }
                }
            } else idx1 = 10;

            for (int j = 1; j < 10; j++) {
                // 이번에 j를 선택할 수 있을 때
                if (arr[i][j]) {
                    // 지난번에 가능했던 어떤 경우 idx1과 같은 종류의 떡이라면
                    if (j == idx1) {
                        // idx2의 떡이 존재하는지 확인하여 가능 여부 체크, 백트래킹을 위한 떡 체크
                        if (i == 1 || dp[i - 1][idx2] > 0) {
                            dp[i][j] = idx2;
                            check = j;
                        }
                        // 지난번에 가능했던 idx1과 다른 떡이 하나라도 있었다면 백트래킹을 위한 떡 체크
                    } else if (idx1 != 0) {
                        check = j;
                        dp[i][j] = idx1;
                    }
                }
            }
            // i번째 날에서 떡을 주는게 불가능했다면 종료
            if (check == -1) return -1;
        }
        return check;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        boolean[][] arr = new boolean[n + 1][10];
        int m;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                arr[i][Integer.parseInt(st.nextToken())] = true;
            }
        }

        int[][] dp = new int[n + 1][10];
        int tmp = solve(n, dp, arr);
        if (tmp != -1) {
            ArrayDeque<Integer> ans = new ArrayDeque<>();
            for (int i = n; i > 0; i--) {
                ans.offerLast(tmp);
                tmp = dp[i][tmp];
            }
            StringBuilder sb = new StringBuilder();
            while (!ans.isEmpty()) {
                sb.append(ans.pollLast()).append("\n");
            }
            System.out.print(sb);
        } else {
            System.out.println(-1);
        }
    }
}