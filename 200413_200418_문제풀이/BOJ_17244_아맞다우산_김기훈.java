import java.io.*;
import java.util.*;

public class BOJ_17244_아맞다우산 {
	static class Pos{
		int x;
		int y;
		int weight;
		public Pos(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
	}//class Pos.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char cnt = '0';
		int start_x = 0;
		int start_y = 0;
		int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
		char[][] map = new char[N][M];
		Queue<Pos> q = new LinkedList<Pos>();
		for(int i=0; i<N; i++) {
			String row = br.readLine();
			for(int j=0; j<M; j++) {
				if(row.charAt(j) == 'S') {
					start_x = i;
					start_y = j;
				}
				else if(row.charAt(j) == 'X') map[i][j] = cnt++;
				else map[i][j] = row.charAt(j);
			}
		}
		int goal = (int)Math.pow(2, (int)(cnt-'0'))-1;
		int time = 0;
		boolean[][][] visited = new boolean[N][M][goal+1];
		q.add(new Pos(start_x,start_y,0));
		visited[start_x][start_y][0] = true;
ex:		while(!q.isEmpty()) {
			//System.out.println(time);
			int size = q.size();
			for(int i=0; i<size; i++) {
				Pos p = q.poll();
				int now_weight = p.weight;
				if(map[p.x][p.y] == 'E' && now_weight == goal) break ex;
				if(map[p.x][p.y]>='0' && map[p.x][p.y] <='4') {
					int tmp = (int)Math.pow(2, (int)(map[p.x][p.y]-'0'));
					if((now_weight & tmp) == 0) now_weight += tmp;
				}
				for(int k=0; k<4; k++) {
					int new_x = p.x + direction[k][0];
					int new_y = p.y + direction[k][1];
					if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && map[new_x][new_y]!='#' && !visited[new_x][new_y][now_weight]) {
						q.add(new Pos(new_x, new_y, now_weight));
						visited[new_x][new_y][now_weight] = true;
					}
				}
			}
			time++;
		}
		System.out.println(time);
	}//end main.
}//end class.
