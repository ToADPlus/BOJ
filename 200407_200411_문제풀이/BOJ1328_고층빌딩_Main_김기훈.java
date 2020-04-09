import java.io.*;
import java.util.*;

public class BOJ_1328_°íÃþºôµù {
	static int P = 1000000007;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		long[][][] dp = new long[N+1][L+1][R+1];
		dp[1][1][1] = 1L;
		for(int n=2; n<=N; n++) {
			for(int l=1; l<=L; l++) {
				if(l>n) break;
				for(int r=1; r<=R; r++){
					if(r>n) break;
					dp[n][l][r] = ((dp[n-1][l-1][r] + dp[n-1][l][r-1])%P + (dp[n-1][l][r] * (n-2))%P )%P;
				}
			}
		}
		System.out.println(dp[N][L][R]);
	}//end main.
}//end class.
