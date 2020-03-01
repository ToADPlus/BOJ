import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468_안정영역_Main_김우희{
	public static int[][] arr;
	public static boolean[][] visited;
	public static Queue<Integer> q;
    
    public static int Max,Min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		Min = Integer.MAX_VALUE;
        Max = Integer.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
                Max = Math.max(arr[i][j], Max);
                Min = Math.min(arr[i][j], Min);
			}
		} // 입력 완료
		
		int max = 1;
		
ex:		for (int k = Min; k <= Max; k++) {
			q = new LinkedList<Integer>();
			visited = new boolean[n][n];
			int result = 0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if(!visited[i][j] && arr[i][j] > k) {
						q.add(i);
						q.add(j);
						result++;
						bfs(i,j,k);
					}
				}
			}
 
			if(max < result) {
				max = result;
			}
		}
		
		System.out.println(max);
		
	} // end of main

	public static void bfs(int i, int j, int m) {
		visited[i][j] = true;
		
		int[] x = {-1,1,0,0};
		int[] y = {0,0,-1,1};
		
		while(!q.isEmpty()) {
			int one = q.poll();
			int two = q.poll();
			
			for (int k = 0; k < y.length; k++) {
				int newX = one+x[k];
				int newY = two+y[k];
				
				if(newX >= 0 && newY >= 0 && newX < arr.length 
						&& newY < arr.length && !visited[newX][newY] && arr[newX][newY] > m) {
					q.add(newX);
					q.add(newY);
					visited[newX][newY] = true;
				}
				
			}
			
		}
		
	}

} // end of class
