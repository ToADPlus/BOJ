import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2644_촌수계산_Main_임효진 {

	public static int N;
	public static int m;
	public static ArrayList<ArrayList<Integer>> list;
	public static int cnt, ans;
	public static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		list = new ArrayList<ArrayList<Integer>>(); // 인접리스트 선언

		for (int i = 0; i <= N; i++) { // 인접리스트 초기화 (객체생성)
			list.add(new <Integer>ArrayList());
		}
		visit = new boolean[N + 1];
    
    // 인접리스트 생성
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to);
			list.get(to).add(from);
		}
    
		dfs(p1, p2); // 촌수 계산하려는 정점 보내기
		if (ans == 0) // 촌수가 아닌 경우
			ans = -1;
		System.out.println(ans);
	}

	public static void dfs(int p1, int p2) {
		if (p1 == p2) { // 내가 찾는 정점에 도착한 경우 ans 변수에 저장
			ans = cnt;
			return;
		}
		visit[p1] = true; 
		for (int i = 0; i < list.get(p1).size(); i++) { // 인접리스트 확인
			int v = list.get(p1).get(i);
			if (visit[v])
				continue;
			cnt++;
			visit[v] = true;
			dfs(v, p2); // 내가 찾은 정점에서 다시 dfs
			visit[v] = false;
			cnt--;
		}
		visit[p1] = false;
	}
}
