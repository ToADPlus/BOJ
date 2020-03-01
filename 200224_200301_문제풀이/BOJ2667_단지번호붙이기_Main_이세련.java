import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2667_단지번호붙이기_Main_이세련 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = new StringTokenizer(br.readLine()," ").nextToken();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
		int[] answer = new int[n*n]; // 정답 배열 생성
		int index = 0; // 정답 배열에 값이 몇개 들어가는지 체크하기 위한 변수
		int[] garo = {0,0,-1,1}; // 상하좌우
		int[] sero = {-1,1,0,0}; // 상하좌우
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]==1) { // 1이면 집이 있으므로 검사 시작
					q.add(new int[] {i,j});
					while(!q.isEmpty()) {
						int[] xy = q.poll();
						arr[xy[0]][xy[1]] = 0; // 1. 가지치기 : 1을 0으로 바꿔서 다음에 체크하지 않도록 해주기 
						answer[index]++; // 단지가 연결되어 있으면 해당 index의 배열 값을 증가시켜주기
						for (int k = 0; k < 4; k++) {
							int x = xy[0] + sero[k];
							int y = xy[1] + garo[k];
							// 유효성 체크 (배열 범위, 단지가 끊겨있는지, 방문했는지)
							if (x<0 || y<0 || x>=n || y>=n || arr[x][y]==0 || visited[x][y]) {
								continue;
							}
							
							q.add(new int[] {x,y});
							visited[x][y] = true;
						}
					}
					index++; // 연결된 단지가 끝났으므로 다음 배열의 index에 저장할 수 있도록 index값을 1개 올려주기.
				}
			}
		}
		
		Arrays.sort(answer); // 오름차순 정렬해주기
		System.out.println(index);
		for (int i = answer.length-index; i<answer.length; i++) {
			System.out.println(answer[i]); // 오름차순으로 정렬했으므로 제일 큰 숫자부터 출력해주기.
		}

	}

}
