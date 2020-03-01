import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11724_연결요소의개수_Main_이세련 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken()); // 1<=n<=1000
		int m = Integer.parseInt(st.nextToken()); // 0<=m<= n*(n-1)/2
		int[][] arr = new int[n+1][n+1]; // 점의 연결 여부를 알려주기위한 배열
		int[] check = new int[n+1]; // 점이 다른 점과 연결되어있는지 확인하기 위한 배열
		// 다른 점과 연결되어 있지 않으면 0
		// 다른 점과 연결되어 있으면 1
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 두 점이 연결되어있으면 : 1
			// 두 점이 연결되어있지않으면 : 0
			arr[x][y] = 1; // 배열에 x와 y가 연결되어있음을 표시
			arr[y][x] = 1; // 배열에 x와 y가 연결되어있음을 표시
			check[x] = 1; 
			check[y] = 1; // 다른 점과 연결되어있음을 표시 
		}
		
		Queue<Integer> q = new LinkedList<>();
		boolean[][] visited = new boolean[n+1][n+1];
		int answer = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(arr[i][j]==1 && !visited[i][j]) {
					// 점 i와 j가 연결되있으면서 방문하지 않은 경우 bfs 시작.
					q.add(i);
					q.add(j);
					visited[i][j] = true;
					visited[j][i] = true;
					while(!q.isEmpty()) {
						int x = q.poll();
						for (int k = 1; k <= n; k++) {
							if( arr[x][k] == 1 && !visited[x][k]) {
								q.add(k);
								visited[x][k] = true;
								visited[k][x] = true;
							}
						}	
					}
					// 연결 요소가 끝났으므로 answer 증가
					answer++;
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if(check[i] != 1) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
