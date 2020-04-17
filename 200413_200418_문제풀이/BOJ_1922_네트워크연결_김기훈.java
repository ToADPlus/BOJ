import java.io.*;
import java.util.*;

public class BOJ_1922_��Ʈ��ũ���� {
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
		int[] distance = new int[V+1];//�����Ϸ��� ����������.
		boolean[] visited = new boolean[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);//�ƽ��� �ʱ�ȭ.
		ArrayList<Pos>[] list = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			list[i] = new ArrayList<Pos>();
		}//��������Ʈ �ʱ�ȭ.
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
		}//1���� �����ϴ� �����˰����� �����ϱ� ���� 1���� ����� �� �ִ� �͵鿡 ���ؼ� distance�� �־����.
		visited[1] = true;
		int answer = 0;
		for(int t=0; t<V-1; t++) { //�ٿ���Ƿ��� v-1 ��ŭ�� ����ȴ�.
			int temp = Integer.MAX_VALUE;
			int idx = -1;
			for(int i=1; i<=V; i++) {
				if(!visited[i] && distance[i] < temp) {
					idx = i;
					temp = distance[i];
				}
			}//���� ����ġ�� ���� �� ����.
			answer+=temp;
			visited[idx] = true;
			for(Pos p : list[idx]) {
				if(distance[p.v] > p.w) distance[p.v] = p.w;
			}//���� ���� ������Ʈ.
		}//end for.
		System.out.println(answer);
	}//end main.
}//end class.
