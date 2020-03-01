import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012_유기농배추_Main_김우희 {
	public static int[][] arr;
	public static boolean[][] visited;
	
	public static int n,m;
	public static int result;
	
	public static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= t; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			arr = new int[m][n];
			visited = new boolean[m][n];
			
			result = 0;
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				arr[x][y] = 1;
			}
	
			
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(!visited[i][j] && arr[i][j] == 1) {
						q.add(i);
						q.add(j);
						visited[i][j] = true;
						result++;
						bfs();
					}
				}	
			}
			System.out.println(result);
		} // end of testCase
		
	} // end of main

	public static void bfs() {
		int[] x = {-1,1,0,0};
		int[] y = {0,0,-1,1};
		
		
		while(!q.isEmpty()) {
			int one = q.poll();
			int two = q.poll();
			
			for (int i = 0; i < y.length; i++) {
				int newX = one+x[i];
				int newY = two+y[i];
				
				if(newX >=0 && newY >= 0 && newX < m && newY < n 
						&& !visited[newX][newY] && arr[newX][newY] == 1) {
					q.add(newX);
					q.add(newY);
					visited[newX][newY] = true;
				}
			}
		}
		
	}
} // end of class
