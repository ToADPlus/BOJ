import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844_쉬운계단수_Main_이세련 {
	
	private static int N;
	private static long answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N+1][10]; // dp[자리수][0~9]
		
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1; // 처음에 한자리 숫자일때 값 채워주기
		}
		
		for (int i = 2; i <= N; i++) { // -1 , +1 해주기
			dp[i][0] = dp[i-1][1]; // 0에서 -1해주면 음수되므로 +1해서 1만 넣어주기
			for (int j = 1; j <= 8 ; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%1000000000;
			}
			dp[i][9] = dp[i-1][8]; // 9에서 +1해주면 10되므로 -1해서 8만 넣어주기
		}
		
		answer = 0;
		for (int i = 0; i <= 9; i++) {
			answer = (answer + dp[N][i])%1000000000;
		}
		System.out.println(answer);
	}

}
