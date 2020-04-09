import java.io.*;
import java.util.*;

public class BOJ_3055_탈출 {
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//class Pos.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
		Queue<Pos> q1 = new LinkedList();
		Queue<Pos> q2 = new LinkedList();
		Pos answer = new Pos(0,0);
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = tmp.charAt(j);
				if(map[i][j] == 'S') q1.add(new Pos(i,j));
				else if(map[i][j] == '*') q2.add(new Pos(i,j));
				else if(map[i][j] == 'D') answer = new Pos(i,j);
			}
		}//end input.
		int time = 0;
		int size=0;
		boolean can_end = false;
ex:		while(!can_end) {
			size = q1.size();
			if(size == 0) break;
			for(int i=0; i<size; i++) {
				Pos p = q1.poll();
				if(p.x == answer.x && p.y == answer.y) {
					can_end = true;
					break ex;
				}
				if(map[p.x][p.y] == '*') continue;
				for(int k=0; k<4; k++) {
					int new_x = p.x + direction[k][0];
					int new_y = p.y + direction[k][1];
					if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && (map[new_x][new_y] == '.' || map[new_x][new_y] == 'D')){
						map[new_x][new_y] = 'S';
						q1.add(new Pos(new_x,new_y));
					}
				}//방향검사.
			}//고슴도치 큐 사이즈 만큼.
			size = q2.size();
			for(int i=0; i<size; i++) {
				Pos p = q2.poll();
				for(int k=0; k<4; k++) {
					int new_x = p.x + direction[k][0];
					int new_y = p.y + direction[k][1];
					if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && (map[new_x][new_y] == '.' || map[new_x][new_y] == 'S')){
						map[new_x][new_y] = '*';
						q2.add(new Pos(new_x,new_y));
					}
				}
			}
			time++;
		}
		if(can_end)System.out.println(time);
		else System.out.println("KAKTUS");
	}//end main.
}//end class.
