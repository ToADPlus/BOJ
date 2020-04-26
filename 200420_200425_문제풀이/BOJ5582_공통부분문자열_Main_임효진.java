import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class BOJ5582_공통부분문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		if (str1.length() > str2.length()) {
			String tmp = str1;
			str1 = str2;
			str2 = tmp;
		}
		int[][] LCS = new int[str1.length() + 1][str2.length() + 1];
		int ans = 0;
		for (int i = 1; i < LCS.length; i++) {
			for (int j = 1; j < LCS[i].length; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1))  // 같다면 대각선에서 가져옴
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
				ans = Math.max(ans, LCS[i][j]);
			}
		}
		System.out.println(ans);
	}
}
