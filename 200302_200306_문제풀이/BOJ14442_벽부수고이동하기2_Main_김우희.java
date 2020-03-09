import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ14442_벽부수고이동하기2_Main_김우희 {
	static int n,m,k;
	static int Min;
	
	static int[][] arr;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		Min = Integer.MAX_VALUE;
		
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			 st = new StringTokenizer(br.readLine());
			 String line = st.nextToken();
			for (int j = 0; j < m; j++) {		
				arr[i][j] = line.charAt(j)-'0';
			}
		}

		bfs(0,0,0,1);
		
		Min = Min == Integer.MAX_VALUE? -1:Min;
		System.out.println(Min);
		
		
	}

	private static void bfs(int i, int j, int cnt, int result) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[][][] visited = new boolean[n][m][k+1];
		
		q.add(i);
		q.add(j);
		q.add(cnt);
		q.add(result);
		
		visited[i][j][cnt] = true;
		

		while(!q.isEmpty()) {
			int one = q.poll();
			int two = q.poll();
			int num = q.poll();
			int total = q.poll();
			
			if(one == n-1 && two == m-1) {
				if(Min > total) {
					Min = total;
					return;
				}
			}
			
			for (int l = 0; l < dx.length; l++) {
				int newX = one + dx[l];
				int newY = two + dy[l];
				
				if(newX >= 0 && newY >= 0 && newX < n && newY < m
						&& !visited[newX][newY][num]) {
					if(arr[newX][newY] == 1 && num < k) {
						q.add(newX);
						q.add(newY);
						q.add(num+1);
						q.add(total+1);
						visited[newX][newY][num+1] = true;
						
						
					} else if (arr[newX][newY] == 0) {
						q.add(newX);
						q.add(newY);
						q.add(num);
						q.add(total+1);
						visited[newX][newY][num] = true;
						
					}
					
					
				}
			}
		}
		
	}

}
