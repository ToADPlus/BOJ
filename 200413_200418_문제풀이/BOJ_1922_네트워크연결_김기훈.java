import java.io.*;
import java.util.*;

public class BOJ_1922_네트워크연결 {
	static class Pos{
		int v;
		int w;
		public Pos(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}//class Pos.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		int[] distance = new int[V+1];//프림하려고 만들어놓은거.
		boolean[] visited = new boolean[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);//맥스로 초기화.
		ArrayList<Pos>[] list = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			list[i] = new ArrayList<Pos>();
		}//인접리스트 초기화.
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(start==end) continue;
			list[start].add(new Pos(end,weight));
			list[end].add(new Pos(start, weight));
		}
		for(int i=0; i<list[1].size(); i++) {
			distance[list[1].get(i).v] = list[1].get(i).w;
		}//1에서 시작하는 프림알고리즘을 구현하기 위해 1에서 뻗어나갈 수 있는 것들에 대해서 distance에 넣어줬다.
		visited[1] = true;
		int answer = 0;
		for(int t=0; t<V-1; t++) { //다연결되려면 v-1 만큼만 돌면된다.
			int temp = Integer.MAX_VALUE;
			int idx = -1;
			for(int i=1; i<=V; i++) {
				if(!visited[i] && distance[i] < temp) {
					idx = i;
					temp = distance[i];
				}
			}//가장 가중치가 낮은 곳 선정.
			answer+=temp;
			visited[idx] = true;
			for(Pos p : list[idx]) {
				if(distance[p.v] > p.w) distance[p.v] = p.w;
			}//간선 정보 업데이트.
		}//end for.
		System.out.println(answer);
	}//end main.
}//end class.
