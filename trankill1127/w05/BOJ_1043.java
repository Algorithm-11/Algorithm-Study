import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1043 {

   public static int[] parent;

   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       StringTokenizer st = new StringTokenizer(br.readLine(), " ");
       int n = Integer.parseInt(st.nextToken()); //사람 수
       int m = Integer.parseInt(st.nextToken()); //파티 수

       parent = new int[n + 1];
       for (int i = 1; i <= n; i++) {
           parent[i] = i;
       }
       st = new StringTokenizer(br.readLine(), " ");
       int knowCnt = Integer.parseInt(st.nextToken()); //비밀을 아는 사람 수
       for (int i = 0; i < knowCnt; i++) { //비밀을 아는 사람들의 집합 생성
           union(0, Integer.parseInt(st.nextToken())); 
       }

       //파티 입력
       ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>(m); 
       for (int i = 0; i < m; i++) {
           st = new StringTokenizer(br.readLine(), " ");
           int partyCnt = Integer.parseInt(st.nextToken()); //파티 참여 인원
           arr.add(new ArrayList<>(partyCnt)); 
           int partyBoss = Integer.parseInt(st.nextToken()); //임의로 파티의 대표자 선정
           arr.get(i).add(partyBoss);
           for (int j = 1; j < partyCnt; j++) {
               int partyNotBoss = Integer.parseInt(st.nextToken());
               union(partyBoss, partyNotBoss);
               arr.get(i).add(partyNotBoss);
           }
       }

       int talkableParty=0;
       for (int i=0; i<m; i++) {
    	   boolean isTalkable = true;
    	   
    	   for (int j=0; j<arr.get(i).size(); j++) {
    		   if (find(arr.get(i).get(j))==0) {
    			   isTalkable=false;
    			   break;
    		   }
    	   }
    	   
    	   if (isTalkable) talkableParty++;
       }
       
       System.out.println(talkableParty);
   }

   public static int find(int idx) {
       if (idx == parent[idx]) return idx;
       return parent[idx] = find(parent[idx]);
   }

   public static boolean union(int idx1, int idx2) {
       int h1 = find(idx1);
       int h2 = find(idx2);
       if (h1 == h2) return false;
       if (h1 < h2) parent[h2] = h1;
       else parent[h1] = h2;
       return true;
   }
}
