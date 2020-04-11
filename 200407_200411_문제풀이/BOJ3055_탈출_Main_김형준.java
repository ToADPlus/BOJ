import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055_탈출_Main_김형준2 {
	static int R, C, dx, dy, ans;
	static char[][] M;
	static boolean ck;
	static boolean [][]visit;
	static int wwy[] = { -1, 1, 0, 0 };
	static int wwx[] = { 0, 0, -1, 1 };
	static Queue<pair> qw = new LinkedList<>();
	static Queue<pair> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = new char[R][C];
		visit= new boolean [R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				M[i][j] = s.charAt(j);
				if (s.charAt(j) == 'D') {
					dy = i;
					dx = j;
				} else if (s.charAt(j) == 'S') {
					q.add(new pair(i, j));
					visit[i][j] = true;
				} else if (s.charAt(j) == '*') {
					qw.add(new pair(i, j));
				}

			}
		}
		ck = true;
		ans=0;
		while (true) {
			ans++;
			if(q.size() ==0) {
				System.out.println("KAKTUS");
				return;
			}
			bfs();
			bfs2();
			if(!ck) {
				System.out.println(ans);
				return;
				
			}
		}

	}

	private static void bfs2() {
		int qsize = q.size();

		
		while (qsize-- > 0) {

			pair pi = q.poll();
			for (int i = 0; i < wwx.length; i++) {
				int ny = pi.y + wwy[i];
				int nx = pi.x + wwx[i];
				if (ny >= 0 && nx >= 0 && ny < R && nx < C && !visit[ny][nx] &&M[ny][nx] != 'X' && M[ny][nx] != '*') {

					q.add(new pair(ny, nx));
					visit[ny][nx] = true;
					if (ny == dy && nx == dx) {
						ck = false;
						return;
					}

				}

			}

		}

	}

	private static void bfs() {
		int qwsize = qw.size();
		while (qwsize-- > 0) {
			pair wi = qw.poll();
			for (int i = 0; i < wwx.length; i++) {
				int ny = wi.y + wwy[i];
				int nx = wi.x + wwx[i];
				if (ny >= 0 && nx >= 0 && ny < R && nx < C && M[ny][nx] == '.') {
					qw.add(new pair(ny, nx));
					M[ny][nx] = '*';
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
		 */
		public pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
