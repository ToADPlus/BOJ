import java.io.*;
import java.util.*;

public class BOJ_14503_로봇청소기 {
	static class Cleaner{
		int x;
		int y;
		int dir;
		public Cleaner(int x, int y, int dir) {
			this.x=x;
			this.y=y;
			this.dir=dir;
		}
	}//class Cleaner.
	static int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+2][M+2];
		for(int i=0;i<N+2;i++) {
			for(int j=0;j<M+2; j++) {
				map[i][j]=1;
			}
		}//일단 다 벽으로 만들어버려.
		int answer = 1;
		int cnt=0;
		st = new StringTokenizer(br.readLine());
		Cleaner cl = new Cleaner(Integer.parseInt(st.nextToken())+1,Integer.parseInt(st.nextToken())+1,Integer.parseInt(st.nextToken()));
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//end input.
		map[cl.x][cl.y] = 2;
ex:		while(true) {
			int temp_dir = (cl.dir+3)%4; //임시방향.	
			int new_x = cl.x + direction[temp_dir][0];
			int new_y = cl.y + direction[temp_dir][1];
			if(map[new_x][new_y]==0) {
				cnt=0;
				cl.x = new_x;
				cl.y = new_y;
				map[cl.x][cl.y] = 2; //청소했다.
				answer++;
			}
			else cnt++;
			cl.dir = temp_dir; //방향바뀐거 넣어주기.
			if(cnt==4) { //4방향에서 청소할 곳 못찾은경우.
				int new_dir = (temp_dir+2)%4;
				new_x = cl.x + direction[new_dir][0];
				new_y = cl.y + direction[new_dir][1];
				if(map[new_x][new_y]==1) break ex;
				else {
					cl.x = new_x;
					cl.y = new_y;
					cnt = 0;
				}
			}
		}//end simulation.
		System.out.println(answer);
	}//end main.
}//end class.
