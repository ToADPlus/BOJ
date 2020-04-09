import java.io.*;
import java.util.*;

public class BOJ_11437_LCA {
	static int N;
	static ArrayList<Integer>[] list;
	static int[] depth;
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		depth = new int[N+1];
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}//인접리스트 만들기.
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		dfs(1,1);
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			sb.append(find(x1,x2)).append("\n");
		}
		System.out.print(sb);
	}//end main.
	public static void dfs(int idx, int cnt) {
		depth[idx] = cnt;
		for(int i=0; i<list[idx].size(); i++) {
			if(depth[list[idx].get(i)] == 0) {
				dfs(list[idx].get(i) , cnt+1);
				parent[list[idx].get(i)] = idx; 
			}
		}
	}//end dfs.
	public static int find(int x, int y) {
		int d_x = depth[x];
		int d_y = depth[y];
		while(x != y) {
			if(d_x > d_y) {
				d_x--;
				x = parent[x];
			}
			else if(d_x < d_y) { //y가 x보다 더 밑에쪽 자손이면.
				d_y--;
				y = parent[y];
			}
			else {
				x = parent[x];
				y = parent[y];
			}
		}
		return x; //y리턴해도됨.
	}
}//end class.
