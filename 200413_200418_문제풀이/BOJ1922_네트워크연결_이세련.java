package d20200415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선이 많으면 프림
// 간선이 적으면 크루스칼
// 1000개당 100,000이므로 최대 100의 선이 있으므로 크루수칼로 하기 
public class BOJ1922_네트워크연결_이세련 {
	static class Edge implements Comparable<Edge>{
		int to;
		int price;
		
		public Edge(int to, int price) {
			super();
			this.to = to;
			this.price = price;
		}

		@Override
		public int compareTo(Edge o) {
			return this.price - o.price;
		}
		
	}

	private static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); // 컴퓨터의 수, 1<=N<=1000
		st = new StringTokenizer(br.readLine()," ");
		int M =  Integer.parseInt(st.nextToken()); // 연결할 수 있는 선의 수, 1<=M<=100,000
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		arr = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			arr[x][y] = z;
			arr[y][x] = z;
		}
		
		boolean[] visited = new boolean[N];
		int cnt = 0;
		int answer = 0;
		pq.add(new Edge(0,0));
		
		while(!pq.isEmpty()) {
			
			Edge edge = pq.poll();
			if(visited[edge.to]) {
				continue;
			}
			
			visited[edge.to] = true;
			answer += edge.price;
			cnt++;
			if(cnt == N) break;
			
			for (int i = 1; i < N; i++) {
				if(!visited[i]) {
					if(arr[edge.to][i] > 0) {
						pq.add(new Edge(i,arr[edge.to][i]));						
					}
				}
			}
			
		}
		
		System.out.println(answer);

	}

}
