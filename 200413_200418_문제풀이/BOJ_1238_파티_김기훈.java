import java.io.*;
import java.util.*;

public class BOJ_1238_파티 {
	static class Pos implements Comparable<Pos>{
		int now;
		int weight;
		public Pos(int now, int weight) {
			this.now = now;
			this.weight = weight;
		}
		@Override
		public int compareTo(Pos p) {
			return this.weight > p.weight ? 1: -1;
		}
	}//class Pos.
	
	static int N;
	static int M;
	static int K;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<Pos>[] list1 = new ArrayList[N+1];
		ArrayList<Pos>[] list2 = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			list1[i] = new ArrayList<Pos>();
			list2[i] = new ArrayList<Pos>();
		}//초기화.
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list1[start].add(new Pos(end, weight));
			list2[end].add(new Pos(start, weight));
		}//정방향 역방향 2개넣기.
		int[] dist1 = dijkstra(list1, K);
		int[] dist2 = dijkstra(list2, K);
		int answer = 0;
		for(int i=1; i<=N; i++) {
			//System.out.println(dist1[i] + " , " + dist2[i]);
			answer = answer < dist1[i]+dist2[i] ? dist1[i]+dist2[i] : answer; 
		}
		System.out.println(answer);
	}//end main.
	public static int[] dijkstra(ArrayList<Pos>[] list, int K) {
		int[] dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
        PriorityQueue<Pos> q = new PriorityQueue<Pos>();
        Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		q.add(new Pos(K,0));
		while(!q.isEmpty()) {
			Pos p = q.poll();
			if(visited[p.now]) continue;
			visited[p.now] = true;
			//System.out.println(p.now + " , " + p.weight);
			for(Pos pos : list[p.now]) {
				if(dist[pos.now] > dist[p.now] + pos.weight) {
					dist[pos.now] = dist[p.now] + pos.weight;
					q.add(new Pos(pos.now , dist[pos.now]));
				}
			}
		}
		return dist;
	}
}//end class.
