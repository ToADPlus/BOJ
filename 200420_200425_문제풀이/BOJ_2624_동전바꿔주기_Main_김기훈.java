import java.io.*;
import java.util.StringTokenizer;


public class BOJ_2624_동전바꿔주기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int weight = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int dp[][] = new int[weight+1][K+1];
		int[][] coin = new int[K][2];
		for(int i=1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			int value = Integer.parseInt(st.nextToken());
			int total = Integer.parseInt(st.nextToken());
			dp[0][i-1] = 1; //0원만드는 경우 1개.
			for(int j=1; j<=total; j++) {
				int now = value*j;
				while(now<=weight) {
					dp[now][i] += dp[now-value*j][i-1]; //현재 돈
					now++;
				}//딱 돈까지.
			}
			for(int j=1; j<=weight; j++) {
				dp[j][i] += dp[j][i-1]; //그전단계것들 가져오기.
			}
		}//end input.
		System.out.println(dp[weight][K]);
	}//end main.
}//end class.
