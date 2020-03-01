import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012_유기농배추_Main_이세련 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int t = Integer.parseInt(st.nextToken());
		int[] garo = {0,0,-1,1}; // 상하좌우
		int[] sero = {-1,1,0,0}; // 상하좌우
		for (int testCase = 1; testCase <= t; testCase++) {
			
			st = new StringTokenizer(br.readLine()," ");
			int m = Integer.parseInt(st.nextToken()); // 가로길이 1<=m<=50
			int n = Integer.parseInt(st.nextToken()); // 세로길이 1<=n<=50
			int k = Integer.parseInt(st.nextToken()); // 위치개수 1<=k<=2500
			int[][] arr = new int[n][m];
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[y][x] = 1;
			}
			
			int answer = 0;
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[n][m];
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					
					if(arr[i][j]==1) { // 배열이 1이면(배추가 심어져있으면)
						
						q.add(new int[] {i,j}); // 큐에 저장
						answer++; // answer 증가 (배추흰지렁이 마리 수 증가)
						
						while(!q.isEmpty()) { // bfs 시작
							int[] xy = q.poll(); // 큐에서 값을 꺼내서
							arr[xy[0]][xy[1]] = 0; // 1의 값을 0으로 바꿔준다. ( 다음에 중복 체크하지 않기 위해서)
							for (int l = 0; l < 4; l++) {
								int x = xy[0] + sero[l];
								int y = xy[1] + garo[l];
								
								// 유효성체크 ( 배열범위, 방문여부, 배추가 심어져있지않은경우)
								if(x<0 || y<0 || x>=n || y>=m ||visited[x][y] ||arr[x][y]==0) {
									continue;
								}
								
								q.add(new int[] {x,y});
								visited[x][y] = true;
							}							
						}
					}
				}
			}
			
			System.out.println(answer);
		}
	}

}
