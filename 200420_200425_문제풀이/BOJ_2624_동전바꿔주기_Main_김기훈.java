import java.io.*;
import java.util.StringTokenizer;


public class BOJ_2624_�����ٲ��ֱ� {
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
			dp[0][i-1] = 1; //0������� ��� 1��.
			for(int j=1; j<=total; j++) {
				int now = value*j;
				while(now<=weight) {
					dp[now][i] += dp[now-value*j][i-1]; //���� ��
					now++;
				}//�� ������.
			}
			for(int j=1; j<=weight; j++) {
				dp[j][i] += dp[j][i-1]; //�����ܰ�͵� ��������.
			}
		}//end input.
		System.out.println(dp[weight][K]);
	}//end main.
}//end class.
