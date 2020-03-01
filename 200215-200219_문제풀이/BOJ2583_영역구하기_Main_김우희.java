import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Queue;

public class BOJ2583_영역구하기_Main_김우희 {
	public static boolean[][] visited;
	public static Queue<Integer> q = new LinkedList<Integer>();
	public static int n;
	public static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[m][n];
		visited = new boolean[m][n];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			for (int j = a; j < c; j++) {
				for (int j2 = b; j2 < d; j2++) {
					arr[j][j2] = 1;
				}
			}
			
		}
		
		int cnt = 0;
		int[] cntResult = new int[100];
		
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == 0 && !visited[i][j]) {
					int result = 1;
					q.add(i);
					q.add(j);
					visited[i][j] = true;
					int m = bfs(i,j,arr,result);
					cntResult[cnt++] = m;
				}
			}
		}
		
		Arrays.sort(cntResult);
		System.out.println(cnt);
		for (int i = cntResult.length-cnt; i <cntResult.length; i++) {
			System.out.print(cntResult[i] + " ");
		}
		
	}

	public static int bfs(int i, int j, int[][] arr, int result) {
		int[] X = {-1,1,0,0};
		int[] Y = {0,0,-1,1};
		
		while(!q.isEmpty()) {
			int one = q.poll();
			int two = q.poll();
			for (int k = 0; k < Y.length; k++) {
				int newX = one+X[k];
				int newY = two+Y[k];
				if(newX >= 0 && newY >=0 && newX < m && newY < n && !visited[newX][newY] && arr[newX][newY] == 0) {
					q.add(newX);
					q.add(newY);

					visited[newX][newY] = true;
					result++;
				}
			}
		}
		
		return result;
	}

}
