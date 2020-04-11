import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_탈출_Main_이세련 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] arr = new char[R][C];
		char[][] nArr = new char[R][C];
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		int di = 0;
		int dj = 0;
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = s.charAt(j);
				nArr[i][j] = s.charAt(j);
				if (s.charAt(j) == 'S') {
					q.add(new int[] { i, j, 0 });
					visited[i][j] = true;
				} else if (s.charAt(j) == 'D') {
					di = i;
					dj = j;
				}
			}
		}
		// 빈 곳 .
		// 물 *
		// 돌 X
		// 비버의 굴 D
		// 고슴도치의 위치 S
		int[] sero = { -1, 1, 0, 0 };
		int[] garo = { 0, 0, -1, 1 };
		int answer = -1;

		ex: while (true) {
			int size = q.size();
			if(size == 0) {
				break ex;
			}
			
			// 물 이동
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j] == '*') {
						for (int d = 0; d < 4; d++) {
							int ni = i + sero[d];
							int nj = j + garo[d];
							if (ni < 0 || nj < 0 || ni >= R || nj >= C || arr[ni][nj] == 'X' || arr[ni][nj] == 'D') {
								continue;
							}

							nArr[ni][nj] = '*';
						}
					}
				}
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					arr[i][j] = nArr[i][j];
				}
			}

			// 고슴도치 이동
			
			for (int i = 0; i < size; i++) {
				int[] xy = q.poll();
				int x = xy[0];
				int y = xy[1];
				int cnt = xy[2];

				for (int d = 0; d < 4; d++) {
					int nx = x + sero[d];
					int ny = y + garo[d];
					if (nx < 0 || ny < 0 || nx >= R || ny >= C || arr[nx][ny] == 'X' || arr[nx][ny] == '*'
							|| visited[nx][ny]) {
						continue;
					}

					if (nx == di && ny == dj) {
						answer = cnt+1;
						break ex;
					}
					visited[nx][ny] = true;
					q.add(new int[] { nx, ny, cnt + 1 });
				}
			}

		}
		System.out.println(answer==-1? "KAKTUS":answer);
	}
}
