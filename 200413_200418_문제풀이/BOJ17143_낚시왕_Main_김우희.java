package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17143_낚시왕_Main_김우희 {
	
	static int R,C,M;
	static int result = 0;// 최종결과

	static Sharks[][] map; // 격자판 & 상어위치
	
	static final int LEFT = 4, RIGHT = 3, DOWN = 2, UP =1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Sharks[R][C];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int v3 = Integer.parseInt(st.nextToken());
			int v4 = Integer.parseInt(st.nextToken());
			int v5 = Integer.parseInt(st.nextToken());
			
			map[v1-1][v2-1] = new Sharks(v3, v4, v5);
		} // 입력 완료
		
		
		// 이동
		for (int i = 0; i < C; i++) {
			
			// 가장 가까운 상어잡기
			for (int j = 0; j < R; j++) {
				if(map[j][i] != null) {
					result += map[j][i].z; // 잡은 상어 크기의 합 더하기
					map[j][i] = null; // 잡은상어 자리 널로만들기
					break;
				}
			}
			
			// 상어이동
			move();
		}
		
		System.out.println(result);
	} // end of main
	
	private static void move() {
		// 격자판 크기만큼 반복
		Sharks[][] shark = new Sharks[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
				// 상어가 존재한다면
				if(map[i][j] != null) {
					
					int tempX = i;
					int tempY = j;
					
					if(map[i][j].dir == LEFT || map[i][j].dir == RIGHT) {
						int cnt = -1; // 방향 확인을 위한 변수
						int num = map[i][j].speed%(2*(C-1));
						
						cnt = map[i][j].dir == LEFT? -1:1;
						while(num-- > 0) {
							if(tempY+cnt < 0 || tempY+cnt >= C) {
								cnt *=  -1;
							}
							 tempY += cnt;
						}
						
						map[i][j].dir = cnt == -1? LEFT:RIGHT;
						
					} else {
						int cnt = -1;
						int num = map[i][j].speed%(2*(R-1));
						
						cnt = map[i][j].dir == UP? -1:1;
						while(num-- > 0) {
							if(tempX+cnt < 0 || tempX+cnt >= R) {
								cnt *= -1;
							}
							tempX += cnt;
						}
						
						map[i][j].dir = cnt == -1? UP:DOWN;
					}
					
					
					if(shark[tempX][tempY] != null) { // 한자리에 두마리의 상어가 있을 경우
						if(shark[tempX][tempY].z < map[i][j].z) {
							shark[tempX][tempY] = map[i][j];
						} 
						
					} else {
						shark[tempX][tempY] = map[i][j];
					}
				} // 상어 존재
				
			} // end of C
		} // end of R
		
		for (int k = 0; k < R; k++) {
			for (int k2 = 0; k2 < C; k2++) {
				map[k][k2] = shark[k][k2];
			}
			
		}
		
	} // end of move

	static class Sharks{
		private int speed; // 속력
		private int dir; // 방향
		private int z; // 크기
		
		
		public Sharks(int speed, int dir, int z) {
			super();
			this.speed = speed;
			this.dir = dir;
			this.z = z;
		}
	} // end of sharks class
 
} // end of class
