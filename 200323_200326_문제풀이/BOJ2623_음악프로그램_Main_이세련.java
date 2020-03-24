package d200324;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2623_음악프로그램_Main_이세련 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 1<=N<=1,000
		int M = sc.nextInt(); // 1<=M<=100
		List<Integer>[]	arr = new List[N+1];
	
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		int[] cnt = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			int n = sc.nextInt();
			int start = sc.nextInt();
			int end = 0;
			for (int j = 0; j < n-1; j++) {
				end = sc.nextInt();
				arr[start].add(end);
				cnt[end]++;
				start = end;
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if(cnt[i]==0) {
				q.add(i);
			}
		}
		int result = 0;
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			int x = q.poll();
			result++;
			// 출력
			sb.append(x).append("\n");

			for (int i = 0; i < arr[x].size(); i++) {
				cnt[arr[x].get(i)]--;
				if(cnt[arr[x].get(i)] == 0) {
					q.add(arr[x].get(i));
				}
			}
		}
		
		System.out.println(result!=N? 0:sb);
		
	

	}

}
