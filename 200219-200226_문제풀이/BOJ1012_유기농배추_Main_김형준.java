package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012_유기농배추_Main_김형준 {
	static boolean [][]m;
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		int TC =  Integer.parseInt(st.nextToken());
		for (int i = 0; i < TC; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); //가로
			int N = Integer.parseInt(st.nextToken()); //세로
			int K = Integer.parseInt(st.nextToken()); //배추의 개수
			m = new boolean [N][M];
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				m[y][x] = true;
			}
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(m[j][k]) {
						bfs(j,k);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
			
					
		}
	}
	private static void bfs(int j, int k) {
		Queue<pair>	q =  new LinkedList<pair>();
		q.add(new pair(j,k));
		m[j][k] = false;
		int []wy = {-1,1,0,0};//상하좌우
		int []wx = {0,0,-1,1};//상하좌우
		while(!q.isEmpty()) {
			
			pair pinfo = q.poll();
			int y = pinfo.y;
			int x = pinfo.x;
			for (int i = 0; i < wx.length; i++) {
				int ny = y + wy[i];
				int nx = x + wx[i];
				if(ny >=0&& nx>=0 && ny <m.length && nx<m[0].length && m[ny][nx]) {
					q.offer(new pair(ny,nx));
					m[ny][nx] = false;
				}
			}
			
			
		}
		
		
	}
	
	
	
}


class pair{
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
