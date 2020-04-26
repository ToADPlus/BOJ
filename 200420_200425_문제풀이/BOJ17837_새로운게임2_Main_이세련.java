import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17837_새로운게임2_Main_이세련 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 체스판의 크기 , 4<=N<=12
		int K = Integer.parseInt(st.nextToken()); // 말의 개수 , 4<=K<=10
		int[][] chess = new int[N][N];
		ArrayList<Integer>[][] horsechess = new ArrayList[N][N];
		for (int i = 0; i < N; i++) { // 체스판의 정보
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
				horsechess[i][j] = new ArrayList<>();
			}
		}
		int[][] horse = new int[K][3]; // 1 : 우 , 2 : 좌 , 3 : 상 , 4 : 하
		// 말의 정보
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			horse[i][0] = Integer.parseInt(st.nextToken()) - 1;
			horse[i][1] = Integer.parseInt(st.nextToken()) - 1;
			horse[i][2] = Integer.parseInt(st.nextToken()) - 1;
			horsechess[horse[i][0]][horse[i][1]].add(i); // 체스판에서 말 추가
		}

		int turn = 0;
		int[] sero = { 0, 0, -1, 1 }; // 우 좌 상 하
		int[] garo = { 1, -1, 0, 0 }; // 우 좌 상 하
ex:		while (turn <= 1000) {
			for (int i = 0; i < K; i++) { // 모든 말 움직이기
				int nx = horse[i][0] + sero[horse[i][2]];
				int ny = horse[i][1] + garo[horse[i][2]];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || chess[nx][ny] == 2) { // 범위 밖으로 벗어나면
					// 방향 반대로하고 이동
					if (horse[i][2] % 2 == 0) { // 짝수면 +1
						horse[i][2]++;
					} else {
						horse[i][2]--;
					}
					
					nx = horse[i][0] + sero[horse[i][2]];
					ny = horse[i][1] + garo[horse[i][2]];
					if(nx < 0 || ny < 0 || nx >= N || ny >= N || chess[nx][ny] == 2) { // 방향을 바꿨는데도 조건 만족못하면 아무데도 못가고 그냥 멈춰있어야됨.
						continue;
					}
				}
				int hx = horse[i][0];
				int hy = horse[i][1];
				int start = horsechess[hx][hy].indexOf(i);
				int horsecnt = horsechess[hx][hy].size();
				if (chess[nx][ny] == 0) { // 흰색
					for (int j = start; j < horsecnt; j++) {
						int index = horsechess[hx][hy].get(start);
						horsechess[nx][ny].add(index); // 새로운 자리 말 더해주기
						horsechess[hx][hy].remove(start); // 기존자리 지워주기
						horse[index][0] = nx;
						horse[index][1] = ny;
					}
					if(horsechess[nx][ny].size() >= 4) {
						turn++;
						break ex;
					}
					
				} else if (chess[nx][ny] == 1) { // 빨간색
					for (int j = horsecnt-1; j >= start; j--) {
						int index = horsechess[hx][hy].get(j);
						horsechess[nx][ny].add(index); // 새로운 자리 말 더해주기
						horsechess[hx][hy].remove(j); // 기존자리 지워주기
						horse[index][0] = nx;
						horse[index][1] = ny;
					}
					if(horsechess[nx][ny].size() >= 4) {
						turn++;
						break ex;
					}
				}
			}
			turn++;
			
			
		}
		System.out.println(turn>1000?-1:turn);

	}

}
