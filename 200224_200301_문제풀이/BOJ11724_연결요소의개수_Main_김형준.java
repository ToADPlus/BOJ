package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11724_연결요소의개수_Main_김형준 {
	static int N;
	static int M;
	static  List[] Graph;
	static boolean visit[];
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Graph = new List[N+1];
		for (int i = 0; i < Graph.length; i++) {
			Graph[i] = new ArrayList<>();
		} // 그래프 초기화
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Graph[a].add(b);
			Graph[b].add(a);
			
			
		}
		int cnt=0;
		visit = new boolean [N+1];
		for (int i = 1; i < N+1; i++) {
			if(!visit[i]) {
				bfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);
		
		//System.out.println(Arrays.toString(Graph));
		
	}
	private static void bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(i);
		visit[i] = true;
		while(!q.isEmpty()) {
			Integer k = q.poll();
			List<Integer> childs =  Graph[k];
			for (int j = 0; j < childs.size(); j++) {
				Integer child = childs.get(j);
				if(!visit[child]) {
					visit[child]=true;
					q.add(child);
				}
			}
			
		}
	}	
}
