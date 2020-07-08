import java.awt.Point;
import java.io.*;
import java.util.*;

public class BOJ_10026_적록색약 {
	static int N;
	static boolean[][] visited;
	static char[][] map;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		int cnt1 = 0;
		int cnt2 = 0;
		for(int i=0; i<N; i++) {
			String tmp_str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = tmp_str.charAt(j);
			}
		}//end input.
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					cnt1++;
					bfs(i,j,map[i][j]);
				}
			}
		}//첫번째 BFS.
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'G') map[i][j] = 'R';
			}
		}//초록색 빨강색으로 바꾸기.
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					cnt2++;
					bfs(i,j,map[i][j]);
				}
			}
		}//두번쨰 BFS.
		System.out.println(cnt1 + " " + cnt2);
	}//end main.
	public static void bfs(int x, int y , char color) {
		visited[x][y] = true;
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x,y));
		while(!q.isEmpty()) {
			Point p = q.poll();
			int dx = p.x;
			int dy = p.y;
			for(int k=0; k<4; k++) {
				int nx = dx + direction[k][0];
				int ny = dy + direction[k][1];
				if(nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny] == color && !visited[nx][ny]) {
					visited[nx][ny]= true;
					q.add(new Point(nx,ny));
				}
			}
		}//end while.
	}//end bfs.
}//end class.
