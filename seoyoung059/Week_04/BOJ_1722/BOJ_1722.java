package seoyoung059.Week_04.BOJ_1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb= new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int caseNum = Integer.parseInt(st.nextToken());

        int check = 0;
        switch(caseNum){
            case 1:
                // k번째 순열을 출력
                if(n==1) {
                    System.out.println(1);
                    return;
                }
                long k = Long.parseLong(st.nextToken())-1;
                // factorial = factNum!의 관계
                long factorial = 1; int factNum = 1;
                int[] perm = new int[n];
                for (int i = 1; i < 20; i++) {
                    if(factorial*(i+1)>k) break;
                    factorial*=(i+1);
                    factNum++;
                }
                for (int i = 0; i < n; i++) {
                    // 해당 자리수의 factorial보다 작다면
                    // 가장 작은 수부터 순열을 채워나간다
                    if(factNum<n-1-i) {
                        for (int j = 1; j <= n; j++) {
                            if((check&(1<<j))==0) {
                                check|=(1<<j);
                                perm[i] = j;
                                break;
                            }
                        }
                    }
                    else {
                        // k를 해당 자리수의 factorial로 나눈 몫이 1 이상이면
                        // 사용하지 않은 수 중 오름차순으로 k/factorial번째 수로 순열을 채운다.
                        long nn=0L;
                        for (int j = 1; j <= n; j++) {
                            if((check&(1<<j))==0){
                                if(nn==k/factorial) {
                                    perm[i] = j;
                                    break;
                                }
                                else nn++;
                            }
                        }
                        k%=(long)factorial;
                        if(factNum==0) {
                            for(int m:perm) sb.append(m).append(" ");
                            System.out.print(sb);
                            return;
                        }
                        factorial/=factNum;
                        factNum--;
                        check|=(1<<perm[i]);
                    }
                }
                break;

            case 2:
                // 몇번째 순열인지 출력
                int[] input = new int[n];
                for (int i = 0; i < n; i++) {
                    input[i] = Integer.parseInt(st.nextToken());
                }

                long ans = 0L;
                // 첫번째 숫자부터 사용하지 않은 수 중 몇번째 수인지 확인해서 합을 구함
                for (int i = 0; i < n; i++) {
                    int tmp = (input[i]-1-Integer.bitCount(check&((1<<input[i])-1)));
                    ans *= (n-i);
                    ans += tmp;
                    check|=(1<<(input[i]-1));
                }
                System.out.println(ans+1);
                break;
        }
    }
}