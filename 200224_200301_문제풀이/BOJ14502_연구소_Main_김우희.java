import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ14502_연구소_Main_김우희 {
	public static int n,m;
	
	public static int[][] arr;
	public static int[][] arr2;
	
	public static List[] ll;
	public static int[] trr;
	
	public static boolean[][] visited;
	public static Queue<Integer> q;
	
	public static int Max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			arr = new int[n][m];
			ll = new List[n*m];
			
			int r = 3;
			trr = new int[r];
			
			int cnt = 0;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 0) {
						ll[cnt] = new ArrayList<>();
						ll[cnt].add(i);
						ll[cnt].add(j);
						cnt++;
					}
				}
			}
			
			comb(cnt ,r);
			
			System.out.println(Max);
			
	}

	public static void comb(int k, int r) {
		if(r == 0) {
			visited = new boolean[n][m];
			q = new LinkedList<Integer>();
			
			// arr 복사
			arr2 = new int[n][m];
			for (int i = 0; i < n; i++) {
				arr2[i] = arr[i].clone();
			}
			
			
			for (int i = 0; i < 3; i++) {
				arr2[(int)ll[trr[i]].get(0)][(int) ll[trr[i]].get(1)] = 1;
			}
			
			// 바이러스 퍼트리기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(!visited[i][j] && arr2[i][j] == 2) {
						visited[i][j] = true;
						q.add(i);
						q.add(j);
						bfs();
					}
				}
			}
			
			
			// 안정영역 개수 확인
			int cnt_1 = 0; 
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(arr2[i][j] == 0) {
						cnt_1++;
					}
				}
			}
			 
			// 최대값 찾기
			if(cnt_1 > Max) {
				Max = cnt_1;
			}

			
		} else if (k < r) {
			return;
		} else {
			trr[r-1] = k-1;
			comb(k-1,r-1);
			comb(k-1,r);
 		}
		
	}

	public static void bfs() {
		int[] x = {-1,1,0,0};
		int[] y = {0,0,-1,1};
		
		while(!q.isEmpty()) {
			int one = q.poll();
			int two = q.poll();
			
			for (int i = 0; i < y.length; i++) {
				int newX = one + x[i];
				int newY = two + y[i];
				
				if(newX >= 0 && newY >= 0 && newX < n && newY < m && !visited[newX][newY] && arr2[newX][newY] == 0) {
					visited[newX][newY] = true;
					arr2[newX][newY] = 2;
					q.add(newX);
					q.add(newY);
				}
			}
					
		} // end of while		
	}

}
