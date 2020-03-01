
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178_미로탐색_Main_김우희 {
	public static int[][] arr;
	public static boolean[][] visited;
	
	public static Queue<Integer> q = new LinkedList<>();
	public static int num = 1;
	
	public static int n,m;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			 st = new StringTokenizer(br.readLine());
			 String line = st.nextToken();
			 
			for (int j = 0; j < m; j++) {
				arr[i][j] = line.charAt(j) -  '0';
			}
		}
		
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(!visited[i][j] && arr[i][j] == 1) {
					q.add(i);
					q.add(j);
					arr[i][j] = 1;
					num++;
					bfs(i,j);
				}
			}
		}
		System.out.println(arr[n-1][m-1]);
		
	}

	public static void bfs(int i, int j) {
		
		visited[i][j] = true;
		
		int[] x = {-1,1,0,0};
		int[] y = {0,0,-1,1};
		
		while(!q.isEmpty()) {
			int one = q.poll();
			int two = q.poll();
			
			for (int k = 0; k < y.length; k++) {
				int newX = one+x[k];
				int newY = two+y[k];
				
				if(newX >= 0 && newY >= 0 && newX < n && newY < m
						&& !visited[newX][newY] && arr[newX][newY] == 1) {
					visited[newX][newY] = true;
					q.add(newX);
					q.add(newY);
					arr[newX][newY] = arr[one][two] + 1;
				}
				
			}
			
		}
		
		
	}

}
