package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468_안전영역_Main_김형준 {
	static int N;
	static int [][]m;
	static boolean visit[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		m = new int [N][N];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, m[i][j]);
			}
		}
		int ans = 0;
		int cnt;
		for (int i = 0; i <= max; i++) {
			cnt = 0;
			visit = new boolean [N][N];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(!visit[j][k] && m[j][k]>i) {
						bfs(j,k,i);
						cnt++;
						
					}
					
				}
			}
			ans = Math.max(ans, cnt);
			
		}
		
		System.out.println(ans);
		
	}//main

	private static void bfs(int y,int x, int r) {
		Queue<pa>q = new LinkedList<pa>();
		q.add(new pa(y,x));
		int wy[]= {-1,1,0,0};
		int wx[]= {0,0,-1,1};
		visit[y][x] = true;
		while(!q.isEmpty()) {
			pa pi = q.poll();
			y = pi.y;
			x = pi.x;
			for (int i = 0; i < wx.length; i++) {
				int ny = y+wy[i];
				int nx = x+wx[i];
				if(ny>=0&&nx>=0&&ny<N&&nx<N&&!visit[ny][nx]&&m[ny][nx]>r) {
					visit[ny][nx] = true;
					q.add(new pa(ny,nx));
				}
			}
		}
		
	}
	
	static class pa{
		int y;
		int x;
		public pa(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}//class
