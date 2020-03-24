package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17406_배열돌리기4_Main_김우희 {

	static int N,M,K;
	static int[][] arr;
	
	static int[] temp;
	static boolean[] visited;
	
	static int[][] A;
	
	static int Min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		A = new int[K][3]; 
		
		temp = new int[K]; // 순열 저장
		visited = new boolean[K]; // 순열 중복체크를 하기 위한 배열
		
		Min = Integer.MAX_VALUE;
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		// 순열 먼저 시작
		perm(0);
		System.out.println(Min);

	}

	private static void perm(int cnt) {
		// 순열을 통해 K개의 회전 연산 순서 정하기
		if(cnt == K) {
			rotation();
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if(!visited[i]) {
				temp[cnt] = i;
				visited[i] = true;
				perm(cnt+1);
				visited[i] = false;
			}
		}
	}

	private static void rotation() {
		
		int[][] map = new int[N+1][M+1];
		
		// map 배열에 arr 배열 복사
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = arr[i][j];
			}
		}
		
		// 본격적인 회전 시작
		for (int i = 0; i < K; i++) {
			int order = temp[i]; // 0 1 2 or 0 2 1 ,,,,,, 2 0 1 ,, 등등
			
			// temp[0] = (3,4,2)
			// temp[1] = (4,2,1)
			// temp[2] = (5,4,3)
			
			int r = A[order][0]; // 3
			int c = A[order][1]; // 4
			int s = A[order][2]; // 2
			
			for (int j = s; j > 0; j--) {
				
				int start = map[r-j][c+j];
				
				// -->
				for (int k = c+j; k > c-j; k--) {
					map[r-j][k] = map[r-j][k-1];
				}
				
				// ^
				// |
				for (int k = r-j; k < r+j; k++) {
					map[k][c-j] = map[k+1][c-j];
				}
				
				// <--
				for (int k = c-j; k < c+j; k++) {
					map[r+j][k] = map[r+j][k+1];
				}
				
				
				// |
				// v
				for (int k = r+j; k > r-j; k--) {
					map[k][c+j] = map[k-1][c+j];
				}
				
				map[r-j+1][c+j] = start;
				
			}

		} // end of for

		
		getMin(map);
		
		
	} // end of rotation

	private static void getMin(int[][] map2) {
		int min = Integer.MAX_VALUE;
		
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += map2[i][j];
			}
			
			if(min > sum) {
				min = sum;
			}
		}
		
		if(Min > min) {
			Min = min;
		}
		
	}

}
