import java.io.*;
import java.util.*;

public class BOJ_9466_텀프로젝트 {
	static int N;
	static int[] num;
	static boolean[] visited;
	static boolean[] selected;
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			N = Integer.parseInt(br.readLine());
			num = new int[N+1];
			visited = new boolean[N+1];
			selected = new boolean[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}//end input.
			answer = 0; //사이클 만든 원소 갯수.
			for(int i=1; i<=N; i++) {
				if(!visited[i]) dfs(i);
			}
			sb.append(N-answer).append("\n");
		}//end testCase.
		System.out.print(sb);
	}//end main.
	public static void dfs(int now) {
		visited[now] = true;
		int next = num[now];
		if(!visited[next]) dfs(next);
		else {
			if(!selected[next]) {
				for(int i=next ;; i=num[i]) { //현재 사이클을 끝점부터 돌린다. 
					answer++;
					if(i==now) break;
				}
			}
		}
		selected[now] = true; //now로 들어온거 다처리.
	}
}//end class.
