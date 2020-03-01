import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 풀이 : HashSet을 사용해서 중복인지 체크하는 dfs

public class BOJ1987_알파벳_Main_이세련 {
	private static HashSet hs = new HashSet();
	private static boolean[][] visited;
	private static int r, c;
	private static char[][] arr;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		r = Integer.parseInt(st.nextToken()); // 1<=r<=20
		c = Integer.parseInt(st.nextToken()); // 1<=c<=20
		arr = new char[r][c];
		visited = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			String s = new StringTokenizer(br.readLine()," ").nextToken();
			for (int j = 0; j < c; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		

		hs.add(arr[0][0]); // 처음 시작 알파벳 hashset에 넣어주기
		visited[0][0] = true; // 방문 체크
		
		dfs(0,0,1); // 시작 위치(0,0) 과 answer(이동할 수 있는 갯수) 담아서 보내기
		
		System.out.println(answer);
		
	}

	private static int[] sero = {-1,1,0,0}; // 상하좌우
	private static int[] garo = {0,0,-1,1}; // 상하좌우
	private static int answer = 1;
	
	// x : 현재 행, y : 현재 열, cnt : 움직인 갯수
	public static void dfs(int x, int y, int cnt) {
		
		for (int i = 0; i < 4; i++) {
			int x_future = x + sero[i]; // 행
			int y_future = y + garo[i]; // 열
			
			// 내가 이동할 곳 유효성 체크 ( 배열 범위 벗어났는지 , 방문했던 곳인지 , 해당 알파벳을 갖고있는지)
			if( x_future<0 || x_future>=r || y_future<0 || y_future>=c || visited[x_future][y_future] || hs.contains(arr[x_future][y_future])) {
				continue;
			}
			
			hs.add(arr[x_future][y_future]); // 조건을 만족하면 hashset에 저장
			visited[x_future][y_future] = true; // 방문체크
			dfs(x_future,y_future,cnt+1); 
			
			// dfs가 끝나고 돌아오면
			// cnt가 원래의 값으로 돌아오므로 cnt+1과 비교해주기
			if(answer< (cnt+1)) { // answer보다 cnt+1 ( 움직인 갯수 ) 가 더 크면 answer에 cnt+1 대입
				answer = cnt+1;
			}
			
			visited[x_future][y_future] = false; // 방문한 것 복구
			hs.remove(arr[x_future][y_future]); // 저장했던 알파벳 삭제
			
		}
		
	}

}
