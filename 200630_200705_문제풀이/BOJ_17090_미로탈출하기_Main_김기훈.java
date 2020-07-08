import java.io.*;
import java.util.*;

public class BOJ_17090_미로탈출하기 {
	static int N; //행.
	static int M; //열.
	static char[][] map; //맵정보저장.
	static int[][] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		check = new int[N][M];
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}//end input.
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(check[i][j] == 0) start(i,j);
				if(check[i][j] == 1) cnt++;
			}
		}
		System.out.println(cnt);
	}//end main.
	
	public static int start(int x, int y) {
		if(check[x][y] != 0) return check[x][y];
		int num = 2;
		check[x][y] = num; //못나간다고 일단 적어놔.
		switch(map[x][y]) {
		case 'U':{
			if(x==0) num = 1;
			else num = start(x-1,y);
			break;
		}
		case 'D':{
			if(x==N-1) num = 1;
			else num = start(x+1,y);
			break;
		}
		case 'L':{
			if(y==0) num = 1;
			else num = start(x,y-1);
			break;
		}
		case 'R':{
			if(y==M-1) num = 1;
			else num = start(x,y+1);
			break;
		}
		}//end switch.
		check[x][y] = num; //쭉 돌고와서 결과값 반영.
		return num;
	}
}//end class.
