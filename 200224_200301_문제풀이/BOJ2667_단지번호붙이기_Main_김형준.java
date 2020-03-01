package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2667_단지번호붙이기_Main_김형준 {
	static int N;
	static int [][]m;
	static boolean [][]visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		m = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String num = st.nextToken(); 
			for (int j = 0; j < N; j++) {
				m[i][j] = num.charAt(j)-'0';
				//System.out.print(m[i][j]);
			}//System.out.println();
		}
		visit =  new boolean [N][N];
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(m[i][j]==1 && !visit[i][j]) {
					int k = bfs(i,j);
					//System.out.println(k);
					pq.add(k);
				}
			}
		}
		System.out.println(pq.size());
		while(!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
		
	}//main
	private static int bfs(int i, int j) {
		Queue<pair> q = new LinkedList<pair>();
		
		q.add(new pair(i,j));
		visit[i][j] = true;
		int[] wy = {-1,1,0,0}; //상하좌우
		int[] wx = {0,0,-1,1}; //상하좌우
		int cnt=1;
		while(!q.isEmpty()) {
			int qsize = q.size();
			while(qsize>0) {
				qsize--;
				pair pinfo = q.poll();
				int y = pinfo.y;
				int x = pinfo.x;
				for (int k = 0; k < wx.length; k++) {
					int ny = y+wy[k];
					int nx = x+wx[k];
					if(ny>=0 && nx>= 0 && ny<N && nx <N && !visit[ny][nx] && m[ny][nx]==1) {
						q.add(new pair(ny,nx));
						visit[ny][nx]=true;
						cnt++;
						//System.out.println(cnt);
					}
				}
			}
		}
		return cnt;
	}
	
	private static class pair{
		int y;
		int x;
		public pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
		
	}
	
	
	
}//class
