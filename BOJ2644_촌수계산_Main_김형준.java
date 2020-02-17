package ����;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2644_�̼���� {
	public static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		int m = sc.nextInt();
		visit = new boolean[n + 1];
		int[][] f = new int[m][2];
		for (int i = 0; i < m; i++) { //�迭�� ���� ���� ����
			f[i][0] = sc.nextInt();
			f[i][1] = sc.nextInt();
		}
		bfs(A, B, f);

	}

	public static void bfs(int v, int t, int[][] f) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(v);
		int cnt = 0;
		visit[v] = true;
		boolean flag = false; //�̼���� �ȵǴ°� üũ
ex:		while (!q.isEmpty()) {
			cnt++; //�̼� ���
			int q_size = q.size();
			while (q_size > 0) {
				q_size--;
				int num = q.poll();
				visit[num] = true;
				for (int i = 0; i < f.length; i++) {
					for (int j = 0; j < 2; j++) {
						if (num == f[i][j]) {
							int nnum = f[i][(j + 1) % 2];
							if (!visit[nnum]) {
								q.offer(nnum);
								if (nnum == t) { //Ÿ�� ������ ����
									System.out.println(cnt);
									flag = true;
									break ex;
								}
							}
						}
					}

				}
			}

		}
		if(!flag)System.out.println("-1"); //�̼����ȵɶ� 
		

	}

}
