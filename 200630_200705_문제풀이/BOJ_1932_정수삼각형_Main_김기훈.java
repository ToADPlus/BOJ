import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932_정수삼각형 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[1];
		st = new StringTokenizer(br.readLine());
		dp[0] = Integer.parseInt(st.nextToken());
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] now_list = new int[i+1];
			for(int j=0; j<now_list.length; j++) {
				now_list[j] = Integer.parseInt(st.nextToken());
			}//현재줄 입력받기.
			int[] now_dp = new int[i+1]; 
			now_dp[0] = now_list[0] + dp[0];
			for(int j=1; j<i; j++) {
				now_dp[j] = now_list[j] + Math.max(dp[j-1], dp[j]);
			}
			now_dp[i] = now_list[i] + dp[i-1];
			dp = now_dp;
		}//2번쨰줄부터 마지막줄까지 Dynamic Programming.
		int max = 0;
		for(int i=0; i<dp.length; i++) {
			max = dp[i] > max ? dp[i] : max;
		}//맨끝줄 제일 큰 값 구하기.
		System.out.println(max);
	}//end main.
}//end class.
