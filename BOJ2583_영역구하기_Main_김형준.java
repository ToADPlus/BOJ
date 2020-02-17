package 백준;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기 {
	public static boolean[][] map;
	
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int K = sc.nextInt();
		map = new boolean[M][N];
		
		int[][] in = new int[K][4];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < 4; j++) {
				in[i][j] = sc.nextInt();
			}
			for (int j = in[i][1]; j < in[i][3]; j++) {
				for (int j2 = in[i][0]; j2 < in[i][2]; j2++) {
					map[j][j2] = true;
				}
			}
		}
		int	[]ans = new int [N*M];
		int anscnt = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(!map[i][j]) { // bfs 돌때마다 빈칸 채우게 됨으로 다음 빈칸 만나면 다시 탐색시작
					int cnt = bfs(i,j); 
					ans[anscnt++] = cnt;
				}
			}
		}//빈칸 찾아서 빈칸 숫자만큼  ans에 추가해 준다 
		
		for (int i = 0; i < anscnt; i++) {
			for (int j = i; j < anscnt; j++) {
				if(ans[i]>ans[j]) {
					int tmp = ans[i];
					ans[i]= ans[j];
					ans[j]=tmp;
				}
			}
		}
		System.out.println(anscnt);
		for (int i = 0; i < anscnt; i++) {
			System.out.print(ans[i]+ " ");
		}
		
		

	}// main

	public static int bfs(int y, int x) { //방향 탐색해서 영역 구한다
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		int[] wy = { -1, 1, 0, 0 };// 상하좌우
		int[] wx = { 0, 0, -1, 1 };// 상하좌우
		qx.offer(x);
		qy.offer(y);
		int cnt = 1;
		map[y][x] = true;
		while (!qx.isEmpty()) {
			int xx = qx.poll();
			int yy = qy.poll();
			for (int i = 0; i < wx.length; i++) {
				int ny = yy + wy[i];
				int nx = xx + wx[i];
				if (ny >= 0 && nx >= 0 && ny < map.length && nx < map[0].length) {
					if (!map[ny][nx]) {
						qy.offer(ny);
						qx.offer(nx);
						map[ny][nx] = true; // 탐색된 구역 지나간것으로 바꿔주기
						cnt++;
					}
				}
			}

		}
		return cnt;
	}

}
