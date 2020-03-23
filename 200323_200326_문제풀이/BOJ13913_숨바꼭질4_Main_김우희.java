import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;


public class BOJ13913_숨바꼭질4_Main_김우희 {
	
	static int N, K; // 수빈, 동생위치
	static boolean[] visited; // 방문처리를 int 배열로 해줬을 경우 걸린 시간을 입력해두면 더 좋을 것 같음
	static int[] path;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];
		path = new int[100001];
		Arrays.fill(path, -1);
		
		System.out.println(bfs());
		
		int temp = K;
		Stack<Integer> stack = new Stack<>();
		do {
			stack.push(temp);
			temp = path[temp];
		} while (temp != -1);
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb.toString());
		
	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited[N] = true;
		q.offer(new int[] {N,0});
		
		int nx;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[0] == K) {
				return cur[1];
			}
			
			// 걷는 경우
			// 위
			nx = cur[0]-1;
			if(nx >= 0 && !visited[nx]) {
				q.offer(new int[] {nx, cur[1]+1});
				visited[nx] = true;
				path[nx] = cur[0];
			}
			
			// 아래
			nx = cur[0]+1;
			if(nx <= 100000 && !visited[nx]) {
				q.offer(new int[] {nx, cur[1]+1});
				visited[nx] = true;
				path[nx] = cur[0];
			}
			
			// 순간이동 하는 경우
			nx = cur[0]<<1;
			if(nx <= 100000 && !visited[nx]) {
				q.offer(new int[] {nx, cur[1]+1});
				visited[nx] = true;
				path[nx] = cur[0];
			}
		}
		return -1;
	
	}

}
