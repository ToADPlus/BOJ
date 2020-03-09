import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ1389_케빈베이컨의6단계법칙_Main_김우희 {
	public static int n,m;
	
	public static int[][] arr;
	public static int[] trr;
	public static boolean[] check;
	
	public static int Min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1];
		trr = new int[n+1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		int result = Integer.MAX_VALUE;
		
		for (int i = 1; i <= n; i++) {
			int total = 0;
			
			for (int j = 1; j <= n; j++) {
				check = new boolean[n+1];
				Min = Integer.MAX_VALUE;
				
				if(i != j) {
					dfs(i,j,0);
					total += Min;
				}
			}
			
			trr[i] = total;
			result = Math.min(trr[i], result);
		}
		
		for (int i = 1; i <= n; i++) {
			if(result == trr[i]) {
				System.out.println(i);
				break;
			}
		}

		
	}

	public static void dfs(int i, int j, int sum) {
		check[i] = true;
		
		if(i == j) {
			if(Min > sum) {
				Min = sum;
				return;
			} 
		}
		
		for (int k = 1; k <= n; k++) {
			if(!check[k] && arr[i][k] == 1) {
//				if(k == j) {
//					if(Min > sum+1) {
//						Min = sum+1;
//						return;
//					} 
//				} else {
//					dfs(k,j,sum+1);
//					check[k] = false;
//				}
				dfs(k,j,sum+1);
				check[k] = false;
			}
		}

	}
}
