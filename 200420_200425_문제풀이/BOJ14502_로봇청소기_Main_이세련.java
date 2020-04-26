import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14502_로봇청소기_Main_이세련 {

	private static boolean[][] visited;
	private static int cnt;
	private static int[][] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 3<=N,M<=50
		int M = Integer.parseInt(st.nextToken()); // 3<=N,M<=50

		st = new StringTokenizer(br.readLine(), " ");
		// 로봇의 위치
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken()); // 0:북 (상), 1:동(우), 2:남(하), 3:서(좌)

		int[] sero = { -1, 0, 1, 0 }; // 상 우 하 좌
		int[] garo = { 0, 1, 0, -1 }; // 상 우 하 좌

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 1;
		visited = new boolean[N][M];

ex:		while (true) {
			visited[r][c] = true;

			boolean bool = true;
			for (int i = 0; i < 4; i++) {

				// 방향 왼쪽으로 바꿔주기
				if (d == 0) {
					d = 3;
				} else {
					d--;
				}

				int nr = r + sero[d];
				int nc = c + garo[d];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || arr[nr][nc] == 1) { // 범위 체크
					continue;
				}
				bool = false;
				r = nr;
				c = nc;
				cnt++;
				break;
			}
			
			
			if(bool) { // 후진
				int nd = d<2? d+2:d-2;
				int nr = r + sero[nd];
				int nc = c + garo[nd];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M || arr[nr][nc] == 1 ) {
					break ex;					
				}
				
				r = nr;
				c = nc;
				//d = nd;		
			}	

		}
		System.out.println(cnt);

	} // end of main

}// end of class
