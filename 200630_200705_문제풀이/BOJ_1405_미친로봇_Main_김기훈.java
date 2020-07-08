import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1405_미친로봇 {
	static boolean[][] visited;
	static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};//동서남북.
	static double[] prob = new double[4];
	static double answer = 0;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //횟수.
		visited = new boolean[2*N+1][2*N+1];
		for(int i=0; i<4; i++) {
			prob[i] = (double)Integer.parseInt(st.nextToken())/ 100;
		}//end input.
		visited[N][N] = true;
		dfs(N,N,0,1);
		System.out.println(answer);
	}//end main.
	public static void dfs(int x, int y, int cnt, double pb) {
		if(cnt == N) {
			answer += pb;
			return ;
		}
		for(int k=0; k<4; k++) {
			int nx = x + direction[k][0];
			int ny = y + direction[k][1];
			if(!visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx,ny,cnt+1,pb*prob[k]);
				visited[nx][ny] = false;
			}
		}//4방향 탐색.
	}//end dfs.
}//end class.
