package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소_Main_김형준 {
	
	static int[][] m;
	static int[][] m2;
	static int M;
	static int N;
	static int ans = Integer.MAX_VALUE;
	static boolean[][] visit;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][M];
		m2 = new int[N][M];
		int one = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
				if (m[i][j] == 1) {
					one++;
				}
			}

		}
		// 벽을세운다
		
		// 감염을시킨다.

		copy(m);
		boolean[][] visitm = new boolean[m.length][m[0].length];
		dfs(0);
		
		
		
		
		//System.out.println(ans);
		System.out.println(M * N - ans - one-3);

	}// main
	
	private static void dfs(int cnt) {
		if(cnt ==3) {
			int [][]copyM = new int [m.length][m[0].length];
			for (int i = 0; i < copyM.length; i++) {
				copyM[i] = m2[i].clone();
			}
			int sum = 0;
			visit = new boolean[m.length][m[0].length];
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[0].length; j++) {
					if (copyM[i][j] == 2 && !visit[i][j]) {
						sum += bfs(copyM,i, j);
					}
				}
			} 
			ans = Math.min(ans, sum);
			return;
		}else {
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[0].length; j++) {
					if(m2[i][j]==0) {
						m2[i][j] =1;
						dfs(cnt+1);
						m2[i][j] =0;
						
						
					}
				}
			}
		}
		
	}

	private static int bfs(int[][] copyM,int y, int x) { // 감염
		Queue<pair> q = new LinkedList<pair>();
		q.add(new pair(y, x));
		int[] wy = { -1, 1, 0, 0 };
		int[] wx = { 0, 0, -1, 1 };
		int cnt = 1;
		// boolean [][]visit = new boolean [m.length][m[0].length];
		visit[y][x] = true;
		while (!q.isEmpty()) {
			pair pinfo = q.poll();
			int ny = pinfo.y;
			int nx = pinfo.x;
			for (int i = 0; i < wy.length; i++) {
				int nny = ny + wy[i];
				int nnx = nx + wx[i];
				if (nny >= 0 && nnx >= 0 && nny < N && nnx < M && !visit[nny][nnx]
						&& copyM[nny][nnx] == 0) {
					copyM[nny][nnx] = 2;
					visit[nny][nnx] = true;
					q.add(new pair(nny, nnx));
					cnt++;
				}
			}
		}
		return cnt;

	}

	public static void copy(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			m2[i] = m[i].clone();
		}
	}



public static class pair {
	int y;
	int x;

	public pair(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "pair [y=" + y + ", x=" + x + "]";
	}

}
}
