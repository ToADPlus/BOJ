import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ_11437_LCA {
	static int N, M;
	static ArrayList<Integer>[] T;
	static int[] par;
	static int[] dep;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		T = new ArrayList[N + 1];
		for (int i = 0; i < T.length; i++) {
			T[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			T[a].add(b);
			T[b].add(a);
		}
		
		int S = 1; //시작 1
		par= new int[N+1];
		dep = new int[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(S);
		par[S] = S;
		dep[S] = S;
		//트리정보 만들기
		while(!q.isEmpty()) {
			
			int from = q.poll();
			for (int to : T[from]) {
				if(par[to] ==0) {//부모의 정보가 없는 노드일때 큐에추가하고 부모의정보와 깊이 갱신
					q.add(to);
					par[to] = from;
					dep[to] = dep[from]+1;//부모깊이 +1
						
					
					
				}
				
				
			}
			
			
		}
		
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//깊이가 더깊은쪽을 b로 설정한다
			if(dep[a]>dep[b]) {
				int temp = a;
				a = b;
				b=temp;
			}
			
			//같은 높이가 되도록 끌어올린다. 같은 깊이에서 같이 올라가서 찾기
			while(dep[a]!=dep[b]) {
				b = par[b];
			}
			if(a==b) {
				sb.append(a).append("\n");
			}else {
				//같은 부모가 될때 까지 올라가기
				while(par[a]!=par[b]) {
					a = par[a];
					b = par[b];
					
					
				}
				sb.append(par[a]).append("\n");
				
			}
			
			
			
		}
		System.out.println(sb);

	}
}
