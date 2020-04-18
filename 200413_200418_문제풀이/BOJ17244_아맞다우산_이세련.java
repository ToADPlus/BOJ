package d20200415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17244_아맞다우산_이세련 {

	private static int N, M;
	private static int[][] stuff;
	private static int[] order;
	private static int cnt = 0;
	private static boolean[] visited;
	private static char[][] arr;
	private static int x, y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 3<=N,M<=50
		M = Integer.parseInt(st.nextToken()); // 3<=N,M<=50
		arr = new char[M][N];
		stuff = new int[5][2];
		x = 0;
		y = 0;

		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j);
				if (arr[i][j] == 'X') {
					stuff[cnt][0] = i;
					stuff[cnt++][1] = j;
				} else if (arr[i][j] == 'S') {
					x = i;
					y = j;
				}
			}
		}

//		1. 물건을 가지러 가는 경우의 수 구하기
		order = new int[cnt];
		visited = new boolean[cnt];
		perm(0);
		System.out.println(answer);

	}

	private static void perm(int index) {
		if (index == cnt) {
//			2. 물건을 가지러가는 경우의 수를 구했으면 가지러 가기
			search();
			return;
		}

		for (int i = 0; i < cnt; i++) {
			if (visited[i]) {
				continue;
			}
			order[index] = i;
			visited[i] = true;
			if (index < cnt) {
				perm(index + 1);
			}
			visited[i] = false;
		}

	}

	private static void search() {

		int min = 0;
		int sx = x;
		int sy = y;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { sx, sy, 0 });
		boolean[][] v = new boolean[M][N];
		v[sx][sy] = true;

//		3. 처음 시작점부터 물건 모두 가져가기
		for (int i = 0; i < cnt; i++) {
			int ex = stuff[order[i]][0];
			int ey = stuff[order[i]][1];
			if (min > answer) {
				return;
			}

			ex: while (!q.isEmpty()) {
				int[] xy = q.poll();
				int x = xy[0];
				int y = xy[1];
				int cnt = xy[2]; // 움직인거리

				for (int d = 0; d < 4; d++) {
					int nx = x + sero[d];
					int ny = y + garo[d];

					if (nx < 0 || ny < 0 || nx >= M || ny >= N || v[nx][ny] || arr[nx][ny] == '#' || min > answer) {
						continue;
					}

					if (nx == ex && ny == ey) {
						min += cnt + 1;
						sx = ex;
						sy = ey;
						break ex;
					}

					v[nx][ny] = true;
					q.add(new int[] { nx, ny, cnt + 1 });

				}

			}

			v = new boolean[M][N];
			q.clear();
			q.add(new int[] { sx, sy, 0 });
			v[sx][sy] = true;

		}

//		4. 마지막 물건에서 E까지 가기

ex2:		while (!q.isEmpty()) {

			int[] xy = q.poll();
			int x = xy[0];
			int y = xy[1];
			int cnt = xy[2]; // 움직인거리

			for (int d = 0; d < 4; d++) {
				int nx = x + sero[d];
				int ny = y + garo[d];

				if (nx < 0 || ny < 0 || nx >= M || ny >= N || v[nx][ny] || arr[nx][ny] == '#' || min > answer) {
					continue;
				}

				if (arr[nx][ny] == 'E') {
					min += cnt + 1;
					break ex2;
				}

				v[nx][ny] = true;
				q.add(new int[] { nx, ny, cnt + 1 });

			}
		}

		if (min < answer) {
			answer = min;
			return;
		}

	}

	private static int answer = Integer.MAX_VALUE;
	private static int[] sero = { -1, 1, 0, 0 };
	private static int[] garo = { 0, 0, -1, 1 };

}
