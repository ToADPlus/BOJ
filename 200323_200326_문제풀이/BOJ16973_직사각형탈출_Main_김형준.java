import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973_직사각형탈출_Main_김형준 {
	static int N, M, H, W, Sx, Sy, Fy, Fx, cnt;
	static int[][] m;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		Sy = Integer.parseInt(st.nextToken()) - 1;
		Sx = Integer.parseInt(st.nextToken()) - 1;
		Fy = Integer.parseInt(st.nextToken()) - 1;
		Fx = Integer.parseInt(st.nextToken()) - 1;
		flag = false;
		cnt = 0;
		bfs();
		if(flag) {
			System.out.println(cnt);
		}else {
			System.out.println(-1);
		}

	}

	private static void bfs() {
		int[] wy = { -1, 1, 0, 0 }; // 상 하 좌 우
		int[] wx = { 0, 0, -1, 1 };
		int[] boxy = { 0, H - 1, 0, 0 };
		int[] boxx = { 0, 0, 0, W - 1 };
		boolean[][] visit = new boolean[N][M];
		Queue<pair> q = new LinkedList<pair>();
		q.add(new pair(Sy, Sx));
		visit[Sy][Sx] = true;
		while (!q.isEmpty()) {
			int qsize = q.size();
			cnt++;
			while (qsize-- > 0) {
				pair pi = q.poll();
				int y = pi.y;
				int x = pi.x;
				if(y==Fy && x ==Fx) {
					flag = true;
					cnt--;
					return;
				}
				co:	for (int i = 0; i < 4; i++) {
					int ny = y + wy[i];
					int nx = x + wx[i];
					if (ny >= 0 && nx >= 0 && ny + boxy[i] < N && nx + boxx[i] < M && !visit[ny][nx]) {
						int nny = ny +boxy[i];
						int nnx = nx +boxx[i];
						// 탐색 올라간 라인 전부다 검사
						if (i < 2) {
							for (int j = 0; j < W; j++) {
								if(m[nny][nnx+j]==1) continue co;
							}
						}else {
							for (int j = 0; j < H; j++) {
								if(m[nny+j][nnx]==1) continue co;
							}
						} 
						//1이 없으면 여기 까지옴
						visit[ny][nx] = true;
						q.add(new pair(ny, nx));
						
						
						

					}

				}

			}

		}

	}

	private static class pair {
		int y;
		int x;

		/**
		 * @param y
		 * @param x
		 * @param cnt
		 */
		public pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;

		}

	}

}
