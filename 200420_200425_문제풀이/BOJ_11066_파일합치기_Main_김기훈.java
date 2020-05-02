import java.io.*;
import java.util.*;

public class BOJ_11066_파일합치기 {
	static int N;
	static int[] book;
	static int[] sum;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			N = Integer.parseInt(br.readLine());
			dp = new int[N+1][N+1];
			book = new int[N+1];
			sum = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1 ;i<=N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				book[i] = temp;
				sum[i] = sum[i-1] + temp;
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			sb.append(start(1,N)).append("\n");
		}
		System.out.print(sb);
	}//end main.
	public static int start(int x, int y) {
		if(dp[x][y] != Integer.MAX_VALUE) return dp[x][y];
		if(x==y) return dp[x][y]= 0;
		if(x+1 == y) return dp[x][y] = book[x]+book[y];
		for (int idx=x; idx<y; idx++) {
			//System.out.println(idx);
	        int left = start(x, idx);
	        int right = start(idx+1, y);
	        dp[x][y] = Math.min(dp[x][y], left + right);
	    }
		return dp[x][y] += sum[y]-sum[x-1];
	}
}//end class.
