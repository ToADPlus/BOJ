import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2583_영역구하기 {
	public static int N;
	public static int M;
	public static int K;
	public static int[][] map;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static ArrayList<Integer> area = new ArrayList<Integer>();
	public static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for (int i = 0; i < K; i++) { // 주어진 좌표를 이용해서 색칠한 부분은 1로 세팅
			st= new StringTokenizer(br.readLine(), " ");
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					map[x][y] = 1;
				}
			}
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) // 1인 곳을 제외하고 dfs해서 영역 넓이 구하기
					continue;
				cnt = 0;
				int areaCnt = dfs(i, j);
				area.add(areaCnt);
			}
		}
		Collections.sort(area); // 오름차순 정렬
		System.out.println(area.size());
		for (int i = 0; i < area.size(); i++) {
			System.out.print(area.get(i) + " ");
		}
	}

	public static int dfs(int x, int y) {
		if (map[x][y] == 1)
			return cnt;
		map[x][y] = 1; // 첫 위치 방문 체크
		cnt++; // 영역 추가

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= M || ny >= N)
				continue;
			if (map[nx][ny] == 1)
				continue;
			dfs(nx, ny);
		}
		return cnt;
	}
}
