import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ11724_연결요소의개수_Main_김우희 {
	public static int[][] arr;
	public static boolean[] visited;
	public static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			arr[u][v] = 1;
			arr[v][u] = 1;
			
		}
		
		int result = 0;
		
		for (int i = 1; i <= n; i++) {
			if(!visited[i]) {
				result++;
				visited[i] = true;
				q.add(i);
				bfs();
			}
		}
		
		System.out.println(result);
		
		
	}

	public static void bfs() {
		
		while(!q.isEmpty()) {
			int one = q.poll();
			
			for (int i = 1; i < arr.length; i++) {
				if(!visited[i] && arr[one][i] == 1) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		
	}

}
