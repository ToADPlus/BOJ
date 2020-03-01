package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697_숨바꼭질_Main_김형준 {
	static int N;
	static int K;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		bfs();
		System.out.println(ans);
	}// main

	public static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visit = new boolean[100001];
		q.add(N);
		visit[N] = true;
		int cnt = 0;
		if(N==K) {
			ans = 0;
			return;
		}
			
		while (!q.isEmpty()) {
			int qsize = q.size();
			cnt++;

			while (qsize > 0) {
				qsize--;
				int n = q.poll();
				int nn = 0;
				// n 다음 수를 정해준다 = +1 -1 *2 중에하나
				for (int i = 0; i < 3; i++) {
					switch (i) {
					case 0:
						nn = n + 1;
						if (nn < visit.length && !visit[nn]) {
							q.offer(nn);
							visit[nn] = true;
							if(nn == K) {
								ans = cnt;
								return;
							}
						}
						break;
					case 1:
						nn = n - 1;
						if (nn >= 0 && !visit[nn]) {
							q.offer(nn);
							visit[nn] = true;
							if(nn == K) {
								ans = cnt;
								return;
							}
						}
						break;
					case 2:
						nn = n * 2;
						if (nn !=0 && nn < visit.length && !visit[nn]) {
							q.offer(nn);
							visit[nn] = true;
							if(nn == K) {
								ans = cnt;
								return;
							}
						}
						break;

					}
				}

			}

		}

	}

}// class
