package d200324;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2252_줄세우기_Main_이세련 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 1<=N<=32,000
		int M = sc.nextInt(); // 1<=M<=100,000
		List<Integer>[] arr = new List[N+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		int[] cnt = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a].add(b);
			cnt[b]++;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(cnt[i] == 0) {
				q.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			int x = q.poll();
			
			sb.append(x).append(" ");
			
			for (int i = 0; i < arr[x].size(); i++) {
				cnt[arr[x].get(i)]--;
				if(cnt[arr[x].get(i)] == 0) {
					q.add(arr[x].get(i));
				}
			}
		}
		
		sb.append("\n");
		System.out.println(sb);
		
		

	}

}
