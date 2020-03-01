import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2667_단지번호붙이기_Main_김우희 {
	public static int[][] arr;
	public static boolean[][] visited;
	
	public static int result = 0;
	
	public static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		visited = new boolean[n][n];
		
		ArrayList<Integer> ar = new ArrayList<>();
		
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		} // end of input
		
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(!visited[i][j] && arr[i][j] == 1) {
					result++;
					q.add(i);
					q.add(j);
					visited[i][j] = true;
					int m = bfs(1);
					ar.add(m);
				}
			}
		}
		
		
		Collections.sort(ar);
		
		System.out.println(result);
		
		for (int i = 0; i < ar.size(); i++) {
			System.out.println(ar.get(i));
		}
		
	}
	
	public static int bfs(int cnt) {
		
		int[] x = {-1,1,0,0};
		int[] y = {0,0,-1,1};
		
		while(!q.isEmpty()) {
			int one = q.poll();
			int two = q.poll();
			
			for (int i = 0; i < y.length; i++) {
				int newX = one + x[i];
				int newY = two + y[i];
				
				if(newX >= 0 && newY >= 0 && newX < arr.length && newY < arr.length
						&& !visited[newX][newY] && arr[newX][newY] == 1) {
					q.add(newX);
					q.add(newY);
					visited[newX][newY] = true;
					cnt++;
				}
				
			}
		}
		return cnt;
		
	}

}
