import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697_숨바꼭질_Main_김우희 {
	public static Queue<Integer> q = new LinkedList<>();
	public static boolean[]visited = new boolean[100001];
	
	public static int k;
	public static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		visited[n] = true;
		q.add(n);
		q.add(0);
		bfs();
		
		System.out.println(result);
		
	}

	public static void bfs() {
		
		while(!q.isEmpty()) {
			int num = q.poll();
			int cnt = q.poll();

			if(num+1 <= 100000 && !visited[num+1]) {
				if(num+1 == k) {
					result = cnt+1;
					break;
				}
				q.add(num+1);
				q.add(cnt+1);
				visited[num+1] = true;
			}
				
			if(num-1 >= 0 && !visited[num-1]) {
				if(num-1 == k) {
					result = cnt+1;
					break;
				}
				q.add(num-1);
				q.add(cnt+1);
				visited[num-1] = true;
			}
				
			if(num*2 <= 100000 && !visited[num*2]) {
				if(num*2 == k) {
					result = cnt+1;
					break;
				}
				q.add(num*2);
				q.add(cnt+1);
				visited[num*2] = true;
			}

				
		}

		return;
		
	}

}
