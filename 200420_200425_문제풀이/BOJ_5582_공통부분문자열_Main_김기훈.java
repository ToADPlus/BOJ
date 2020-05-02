import java.io.*;
import java.util.*;

public class BOJ_5582_����κй��ڿ� {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int[][] dp = new int[str1.length()+1][str2.length()+1];
		int answer = 0;
		for(int i=1;i <=str1.length(); i++) {
			for(int j=1; j<=str2.length(); j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1; //���� ���ϴ� ���ڳ��� ���� ���, �밢�� �� +1.
					if(answer < dp[i][j]) answer = dp[i][j];
				}
			}
		}//end for.
		System.out.println(answer);
	}//end main.
}//end class.
