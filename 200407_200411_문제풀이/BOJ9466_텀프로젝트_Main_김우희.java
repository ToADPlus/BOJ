import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class BOJ9466_텀프로젝트_Main_김우희 {
	
	static int R;
	
	static int[] arr;
	
	static boolean[] visited;
	static boolean[] check;
	
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		for (int testCase = 1; testCase <= T; testCase++) {
			R = Integer.parseInt(br.readLine()); // 학생 수
			arr = new int[R+1];
			
			check = new boolean[R+1];
			result = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= R; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new boolean[R+1];
			
			// 팀에 속하는지 아닌지를 구분하기 위해
			for (int i = 1; i <= R; i++) {
			
				if(!check[i]) {
					dfs(i);
				}
			}
			
			System.out.println(R-result);

		} // end of testCase
	} // end of main

	private static void dfs(int now) {
		visited[now] = true;
		int next = arr[now];
		
		if(!visited[next]) {
			dfs(next);
		} else {
			if(!check[next]) {
				for(int i = next; ; i=arr[i]) {
					result++;
					if(i == now) {
						break;
					}
				}
			}
		}
		check[now] = true;
	}

} // end of class
