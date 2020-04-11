import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class BOJ11437_LCA_Main_이세련 {

	private static ArrayList<Integer>[] tree;
	private static int[] depth;
	private static int M;
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 2<=N<=50,000
		// 1. 노드의 정보를 저장할 트리 만들기
		tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		// 2. 주어진 점들로 트리 만들기
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		depth = new int[N+1]; // 정점의 depth를 저장할 배열
		parent = new int[N+1]; // 정점의 부모를 저장할 배열 ( 나중에 부모를 찾아서 타고 올라가야함 )
		
		M = Integer.parseInt(br.readLine());
		
		// 3. 정점들의 depth 구하기
		dfs(1,1); 
		
		StringBuilder sb = new StringBuilder();
		
		// 4. 입력값 받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(depth[a]>depth[b]) {
				// a의 깊이가 b의 깊이보다 큰 경우, a의 깊이 올려주기
				int aDepth = depth[a];
				while(aDepth > depth[b]) {
					aDepth--;
					a = parent[a];
				}				
			}else if(depth[a] < depth[b]){
				// b의 깊이가 a의 깊이보다 큰 경우, b의 깊이 올려주기
				int bDepth = depth[b];
				while(bDepth > depth[a]) {
					bDepth--;
					b = parent[b];
				}	
			}
			
			while(a != b) { // a와 b가 같지 않다면, a와 b의 부모가 같은지 계속 체크해주기
				a = parent[a];
				b = parent[b];
			}
			
			sb.append(a).append('\n'); // a,b 중에 아무거나 append하기
	
		}
		System.out.println(sb);
	}

	private static void dfs(int from, int cnt) {
		
		depth[from] = cnt++; // 나의 depth 저장
		
		for (int i = 0; i < tree[from].size(); i++) {
			int x = (int) tree[from].get(i);
			// x의 깊이가 0이 아니면 내가 자식이고
			// x의 깊이가 0이면 x가 나의 자식
			if(depth[x] == 0) { // x가 나의 자식이므로
				dfs(x, cnt); // dfs
				parent[x] = from; // x의 부모에 나 저장
			}
		}
		
	}

}
