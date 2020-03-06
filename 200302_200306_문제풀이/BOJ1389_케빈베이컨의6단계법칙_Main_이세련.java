import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1389_케빈베이컨의6단계법칙_Main_이세련 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 2<=n<=100, 유저의 수
		int m = sc.nextInt(); // 1<=m<=5000, 친구관계의 수
		int[][] arr = new int[n + 1][n + 1];

		for (int i = 0; i < m; i++) { // 입력값 저장
			int x = sc.nextInt();
			int y = sc.nextInt();

			arr[x][y] = 1;
			arr[y][x] = 1;
		}

		int[] answer = new int[n + 1]; // 점 이동 거리 체크
		int ansNum = 0; // 점 번호 저장
		int min = Integer.MAX_VALUE; // 최소값 비교
		boolean[] check = new boolean[n + 1]; // 방문 체크
		Queue<int[]> q = new LinkedList<>();
		
		
		for (int i = 1; i <= n; i++) {			
			check[i] = true; // 시작점 방문 체크
			q.add(new int[] { i, 0 }); // 시작점 : i, cnt : 0
			
			while (!q.isEmpty()) {
				int[] xCnt = q.poll();
				int x = xCnt[0];
				int cnt = xCnt[1];
				answer[i] += cnt;

				if (answer[i] > min) { 
					break;
				}
				
				for (int j = 1; j <= n; j++) {
					if (arr[x][j] == 1 && !check[j]) {
						check[j] = true;
						q.add(new int[] { j, cnt + 1 });
					}
				}
				
			}
			if (min > answer[i]) { // 최소값보다 작으면
				min = answer[i];
				ansNum = i; // 점 저장
			}
			check = new boolean[n+1]; // 방문 체크 리셋
		}

		System.out.println(ansNum); // 답 출력

	}


}
