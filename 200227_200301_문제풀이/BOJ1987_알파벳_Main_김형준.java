package ¹éÁØ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987_¾ËÆÄºª_Main_±èÇüÁØ {
	static int R;
	static int C;
	static int ans = Integer.MIN_VALUE;
	static int[][] m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < C; j++) {
				m[i][j] = (int) (s.charAt(j) - 'A');
				// System.out.print(m[i][j]+" ");
			} // System.out.println();
		}
		boolean visit[] = new boolean[26];
		visit[m[0][0]] = true;
		dfs(visit, 0, 0, 1);
		System.out.println(ans);
	}

	private static void dfs(boolean[] visit, int i, int j, int cnt) {
		int[] wy = { -1, 1, 0, 0 };
		int[] wx = { 0, 0, -1, 1 };

		for (int k = 0; k < wx.length; k++) {
			int ny = i + wy[k];
			int nx = j + wx[k];
			if (ny >= 0 && nx >= 0 && ny < R && nx < C && !visit[m[ny][nx]]) {
				cnt++;
				visit[m[ny][nx]] = true;
				dfs(visit, ny, nx, cnt);
				visit[m[ny][nx]] = false;
				cnt--;
			}
			ans = Math.max(ans, cnt);

		}

	}
}
