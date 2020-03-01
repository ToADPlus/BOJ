import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 풀이
// 1. 벽 3개를 세우기 위해 조합 찾기
// 2. 조합을 찾아서 벽을 세웠으면 바이러스 퍼트리기
// 3. 안전영역 최댓값 찾기

public class BOJ14502_연구소_Main_이세련 {

	public static int zeroCnt = 0;// 제로 배열의 값이 몇개까지 있는지 체크.
	public static int[] wall = new int[3]; // 벽 배열
	public static int[][] zeroArr;
	public static int[][] arr;
	public static int n, m, answer;
	public static Queue<int[]> q = new LinkedList<>();
	private static int[][] newArr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken()); // 행 3<=n<=8
		m = Integer.parseInt(st.nextToken()); // 열 3<=m<=8
		arr = new int[n][m]; // 연구소 지도
		zeroArr = new int[n*m][2]; // 빈칸 ( 0 )인 지역만 담을 배열
		answer = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==0) {
					zeroArr[zeroCnt][0] = i;
					zeroArr[zeroCnt++][1] = j;
				}
			}
		}
		
		// 0 <= ? < zeroCnt 까지 for문 이용해서 3개 조합 combi 구하기.
		combi(0);
		
		System.out.println(answer);
		
	}

	public static void combi(int x) {
		if(x==3) {
			// 조합 3개를 찾았으면 벽을 세워라.
			buildWall();
		}else {
			// 재귀
			for (int i = 0; i < zeroCnt; i++) {
				if(x>0) { // x가 첫번째가 아니면 이전의 배열보다 값이 큰지 check (3개 조합이므로)
					if(i<=wall[x-1]) { // 이전의 배열과 값이 같거나 작으면 continue
						continue;
					}
				}
				wall[x] = i;
				combi(x+1);
			}
		}
		
	}

	public static void buildWall() {
		
		newArr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newArr[i][j] = arr[i][j];
			}
		}
		
		for (int i = 0; i < 3; i++) {
			newArr[zeroArr[wall[i]][0]][zeroArr[wall[i]][1]] = 1; // 벽 설치
		}
		
		// 바이러스 퍼트리기
		virus();
		
		// 벽세웠고, 바이러스 퍼트렸으므로 마지막 단계로 안전영역 구하기
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(newArr[i][j]==0) {
					max++;
				}
			}
		}
		
		// 안전 영역 최대값 구하기
		if(max > answer) {
			answer = max;
		}
		
	}
	
	public static void virus() { // bfs 사용
		
		int[] garo = {0,0,-1,1}; // 상하좌우
		int[] sero = {-1,1,0,0}; // 상하좌우
		
		boolean[][] visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(newArr[i][j] == 2 && !visited[i][j]) { // 바이러스가 있으면서 방문하지 않은 경우
					q.add(new int[] {i,j});
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						int[] xy = q.poll();
						
						for (int k = 0; k < 4; k++) {
							int x = xy[0] + sero[k]; // 행
							int y = xy[1] + garo[k]; // 열
							
							if(x<0 || x>=n || y<0 || y>=m || newArr[x][y] == 1 || visited[x][y]) { 
								// 연구소 영역을 벗어나거나 방문했으면 continue
								continue;
							}
							
							newArr[x][y] = 2; // 바이러스 퍼트리기
							q.add(new int[] {x,y});
							visited[x][y] = true;
						}
					}
				}
			}
		}
		
	}

}
