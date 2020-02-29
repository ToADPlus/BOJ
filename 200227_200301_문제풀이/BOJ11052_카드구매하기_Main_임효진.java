import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11052_카드구매하기_Main_임효진 {

	public static int max;
	public static int N;
	public static int[] p;
	public static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		p = new int[N + 1];
		dp = new int[N + 1];
		for (int i = 1; i < p.length; i++) {
			p[i] = Integer.parseInt(st.nextToken());
			dp[i] = p[i];
		}

		for (int i = 1; i < p.length; i++) {
			for (int j = 1; j <= i; j++) {
				if (i % j == 0) {
					dp[i] = Math.max(p[j] * (i / j), dp[i]);
				}
				if (i - j > 0) {
					dp[i] = Math.max(dp[j] + dp[i - j], dp[i]);
				}
			}
		}
		System.out.println(dp[N]);
	}

}
