import java.io.*;
import java.util.*;

public class BOJ_10844_쉬운계단수 {
	static int P = 1000000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N+1][10];
		for(int i=0; i<10; i++) {
			if(i==0) dp[1][i] = 0;
			else dp[1][i] = 1;
		}
		for(int i=2; i<=N; i++) {
			dp[i][0] = dp[i-1][1];
			for(int j=1; j<=8; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%P;
			}
			dp[i][9] = dp[i-1][8];
		}
		long answer = 0;
		for(int i=0; i<10; i++) {
			answer = (answer + dp[N][i])%P;
		}
		System.out.println(answer);
	}//end main.
}//end class.
