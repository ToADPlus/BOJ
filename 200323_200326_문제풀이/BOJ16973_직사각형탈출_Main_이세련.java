package d200323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973_직사각형탈출_Main_이세련 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[][] arr = new int[H + 1][W + 1];

		for (int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int[] s = new int[2];
		int[] f = new int[2];
		s[0] = Integer.parseInt(st.nextToken());
		s[1] = Integer.parseInt(st.nextToken());
		f[0] = Integer.parseInt(st.nextToken());
		f[1] = Integer.parseInt(st.nextToken());

		int[] sero = { -1, 1, 0, 0 }; // 상하좌우
		int[] garo = { 0, 0, -1, 1 }; // 상하좌우

		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { s[0], s[1], 0 });
		boolean[][] visited = new boolean[H + 1][W + 1];
		visited[s[0]][s[1]] = true;
		int answer = Integer.MAX_VALUE;
		while (!q.isEmpty()) {

			int[] xy = q.poll();
			
			if (xy[0] == f[0] && xy[1] == f[1]) {
				if (answer > xy[2]) {
					answer = xy[2];
				}
			} else {
				
				for (int i = 0; i < 4; i++) {
					boolean bool = true;

					int nx = xy[0] + sero[i];
					int ny = xy[1] + garo[i];
					int cnt = xy[2];
					
					if(cnt > answer) {
						continue;
					}
					
					switch(i) {
					case 0:
						if (nx < 1 || ny < 1 || nx > H || ny > W || visited[nx][ny]) {
							continue;
						}

						for (int j = 0; j < y; j++) {
							if (arr[nx][ny + j] != 0) {
								bool = false;
								break;
							}
						}
						break;
					case 1:
						if (nx < 1 || ny < 1 || nx + x - 1 > H || ny > W || visited[nx][ny]) {
							continue;
						}

						for (int j = 0; j < y; j++) {
							if (arr[nx + x - 1][ny + j] != 0) {
								bool = false;
								break;
							}
						}
						break;
					case 2:
						if (nx < 1 || ny < 1 || nx > H || ny > W || visited[nx][ny]) {
							continue;
						}

						for (int j = 0; j < x; j++) {
							if (arr[nx + j][ny] != 0) {
								bool = false;
								break;
							}
						}
					case 3:
						if (nx < 1 || ny < 1 || nx > H || ny + y - 1 > W || visited[nx][ny]) {
							continue;
						}

						for (int j = 0; j < x; j++) {
							if (arr[nx + j][ny + y - 1] != 0) {
								bool = false;
								break;
							}
						}
						break;
					}
						
					if (bool) {
						q.add(new int[] { nx, ny, cnt + 1 });
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		System.out.println(answer==Integer.MAX_VALUE? -1:answer);

	}

}
