import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922_Main_네트워크연결_김형준 {
	static int N,M;
	static int []parents;
	static int []rank;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //컴퓨터수
		M = Integer.parseInt(br.readLine()); //간선수
		parents = new int [N];
		rank = new int [N];
		int [][]edges = new int [M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken())-1;
			edges[i][1] = Integer.parseInt(st.nextToken())-1;
			edges[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[2], o2[2]);
			}
			
		});
		
		//각정점에 대해 유니온파인드연산준비
		for (int i = 0; i < N; i++) {
			makeSet(i);
		}
		int result = 0;
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			int a = findSet(edges[i][0]);
			int b = findSet(edges[i][1]);
			if(a==b)continue;
			union(a,b);
			result +=edges[i][2];
			cnt++;
			if(cnt==N-1)break;
		}
		
		System.out.println(result);
		
		
		
	}
	private static void union(int a, int b) {
		int px = findSet(a);
		int py = findSet(b);
		if(rank[px]>rank[py]) {
			parents[py] = px;
		}else {
			parents[px] = py;
			if(rank[px] == rank[py]) {
				rank[py]++;
			}
		}
		
	}
	private static int findSet(int i) {
		if(i==parents[i]) {
			return i;
		}else {
			parents[i] = findSet(parents[i]);
			return parents[i];
		}
	}
	private static void makeSet(int i) {
		parents[i] = i;
		
	}
	
	
}
