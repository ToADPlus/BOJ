import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1328_고층빌딩_Main_이세련 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 1<=N<=100
		int L = Integer.parseInt(st.nextToken()); // 1<=L<=N
		int R = Integer.parseInt(st.nextToken()); // 1<=R<=N
		// N번째 빌딩
		// 왼쪽에서 봤을때의 갯수
		// 오른쪽에서 봤을때의 갯수
		long[][][] dp = new long[N + 1][L + 1][R + 1];

//		가장 높은 빌딩 먼저 놓기
		dp[1][1][1] = 1; // 1번째 빌딩, 왼쪽에서 봤을 때 1개, 오른쪽에서 봤을 때 한개

		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= L; j++) {
				for (int k = 1; k <= R; k++) {
					// 가장 왼쪽에 놓는 경우
					// 높이 1인 건물을 N-1개의 건물들 왼쪽에 추가한다면 왼쪽에서 보는 건물의 수가 증가
					dp[i][j][k] += dp[i - 1][j - 1][k];
					// 가장 오른쪽에 놓는 경우
					dp[i][j][k] += dp[i - 1][j][k - 1];
					// 중간 어딘가에 놓을 경우
					// 높이가 1인 건물을 중간 어디에 놓아도 좌우에 보여지는 건물의 수는 변화가 없다.
					dp[i][j][k] += dp[i - 1][j][k] * (i - 2);
					dp[i][j][k] %= 1000000007;
//					System.out.println(dp[i][j][k]);
				}
			}
		}
		System.out.println(dp[N][L][R]);

	}

}
