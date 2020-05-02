import java.io.*;
import java.util.*;

public class BOJ_14500_테트로미노 {
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static int answer = 0;
	static class Pos{
		int x;
		int y;
		int sum;
		public Pos(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}//class Pos.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//end input.
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dfs(i,j,1,map[i][j]);
			}
		}//start(bfs);
		for (int i=1; i<N; i++) {
			for (int j = 1; j<M-1; j++) {
				int temp = map[i][j] + map[i - 1][j] + map[i][j - 1] + map[i][j + 1];
				if (temp > answer) answer = temp;
			}
		} // ㅗ 모양 예외처리.
		for (int i=0; i<N-1; i++) {
			for (int j=1; j<M-1; j++) {
				int temp = map[i][j] + map[i + 1][j] + map[i][j - 1] + map[i][j + 1];
				if (temp > answer) answer = temp;
			}
		} // ㅜ 모양 예외처리.
		for (int i=1; i<N-1; i++) {
			for (int j=1; j<M; j++) {
				int temp = map[i][j] + map[i + 1][j] + map[i - 1][j] + map[i][j - 1];
				if (temp > answer) answer = temp;
			}
		} // ㅓ 모양 예외처리.
		for (int i=1; i<N-1; i++) {
			for (int j=0; j<M-1; j++) {
				int temp = map[i][j] + map[i + 1][j] + map[i - 1][j] + map[i][j + 1];
				if (temp > answer) answer = temp;
			}
		} // ㅏ 모양 예외처리.

		System.out.println(answer);
	}//end main.
	public static void dfs(int x, int y, int cnt, int sum) {
		if(cnt == 4) {
			if(sum > answer) answer = sum;
			return ;
		}
		visited[x][y] = true;
		for(int k=0; k<4; k++) {
			int new_x = x + direction[k][0];
			int new_y = y + direction[k][1];
			if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && !visited[new_x][new_y]) dfs(new_x,new_y,cnt+1, sum+map[new_x][new_y]);
		}
		visited[x][y] = false;
	}//end dfs.
}//end class.
