package BOJ;

import java.util.Scanner;

public class BOJ10844_쉬운계단수_Main_김우희 {
	static int[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		dp = new int[n+1][10];
		
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				if(i == 2 && j == 0) {
					dp[2][0] = 1;
				} else if(j == 0) {
					dp[i][0] = (dp[i-1][0]+1)%1000000000;
				} else if(j == 9) {
					dp[i][9] = dp[i-1][8]%1000000000;
				} else {
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%1000000000;
				}
				System.out.print(dp[i][j]);
			}
			System.out.println();
		}

		
		int sum = 0;
		for (int i = 1; i < 10; i++) {
			sum += dp[n][i]%1000000000;
		}
		
		sum = sum%1000000000;
		System.out.println(sum);
	}

}
