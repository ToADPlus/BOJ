package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1238_파티_Main_김우희 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(arr[a][b] != 0) {
				if(arr[a][b] > c) {
					arr[a][b] = c;
				}
			} else {
				arr[a][b] = c;
			}
		
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(k == i) continue;
				for (int j = 1; j <= N; j++) {
					if(k == j || i == j) continue;
					
					if(arr[i][j] == 0) {
						if(arr[i][k] != 0 && arr[k][j] != 0) {
							arr[i][j] = arr[i][k]s + arr[k][j];
						}
					} else {
						if(arr[i][k] != 0 && arr[k][j] != 0 &&
								arr[i][j] > arr[i][k] + arr[k][j]) {
							arr[i][j] = arr[i][k] + arr[k][j];
						}
					}
				}
			}
			
		}
		
		int max = 0;
		for (int i = 1; i <= N; i++) {
			if(i != X && arr[i][X] + arr[X][i] > max) {
				max = arr[i][X] + arr[X][i];
			}
		}
		System.out.println(max);
		
	} // end of main

} // end of class
