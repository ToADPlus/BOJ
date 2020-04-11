import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10844_main_김형준 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		long [][]dp = new long [N+1][11];
		for (int i = 0; i < 10;i++) {
			dp[1][i] = 1;
		}
		long sum = 0;
		for (int i = 2; i < dp.length; i++) {
			dp[i][0]= dp[i-1][1];
			for (int j = 1; j < 10; j++) {
				
				dp[i][j]= (dp[i-1][j-1]+dp[i-1][j+1])%1000000000;
				
			
			}
		
			
			
		}
		for (int i = 1; i < dp[N].length; i++) {
		
				sum += dp[N][i];
				sum%=1000000000;
			
		}
		System.out.println(sum);
//		for (int i = 0; i < dp.length; i++) {
//			for (int j = 0; j < dp[i].length; j++) {
//				System.out.println("dp "+i+" : "+ j + " = "+ dp[i][j]);
//			}
//		}
		
		
		
		
	}
}
