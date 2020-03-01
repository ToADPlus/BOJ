import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1987_알파벳_Main_김우희 {
	public static int r,c;
	public static char[][] str;
	public static boolean[] visited;
	
	public static int Max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		str = new char[r][c];
		visited = new boolean[26];
		
		Max = 1;
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			
				for (int j = 0; j < c; j++) {
					str[i][j] = line.charAt(j);
				}
		}
		
		dfs(0,0,1);
		
		System.out.println(Max);
		
		
	} // end of main

	public static void dfs(int i, int j, int sum) {
		int[] x = {-1,1,0,0};
		int[] y = {0,0,-1,1};
		
		visited[str[i][j] - 'A'] = true;
		 
		for (int k = 0; k < y.length; k++) {
			int newX = i + x[k];
			int newY = j + y[k];
	
			if(newX >= 0 && newY >= 0 && newX < r && newY < c 
					&& !visited[str[newX][newY]-'A']) {
				dfs(i+x[k], j+y[k], sum+1);
				if(sum+1 > Max) {
					Max = sum+1;
				}
				visited[str[i+x[k]][j+y[k]]-'A'] = false;
 			}
		}
		
	}

} // end of class
