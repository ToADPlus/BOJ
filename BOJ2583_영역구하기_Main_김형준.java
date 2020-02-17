package ����;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_2583_�������ϱ� {
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
				if(!map[i][j]) { // bfs �������� ��ĭ ä��� ������ ���� ��ĭ ������ �ٽ� Ž������
					int cnt = bfs(i,j); 
					ans[anscnt++] = cnt;
				}
			}
		}//��ĭ ã�Ƽ� ��ĭ ���ڸ�ŭ  ans�� �߰��� �ش� 
		
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

	public static int bfs(int y, int x) { //���� Ž���ؼ� ���� ���Ѵ�
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		int[] wy = { -1, 1, 0, 0 };// �����¿�
		int[] wx = { 0, 0, -1, 1 };// �����¿�
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
						map[ny][nx] = true; // Ž���� ���� ������������ �ٲ��ֱ�
						cnt++;
					}
				}
			}

		}
		return cnt;
	}

}
