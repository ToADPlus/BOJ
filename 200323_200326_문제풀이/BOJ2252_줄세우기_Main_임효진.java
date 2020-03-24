import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252_줄세우기_Main_임효진 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> adjList;
	static int[] inDegree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 인접리스트 초기화
		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
		}

		// 자신에게 들어오는 방향 저장
		inDegree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			inDegree[to]++;
		}

		// 정렬
		topology();
		System.out.println(sb);
	}

	static void topology() {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) // 가장 먼저 시작할 수 있는 조건
				q.add(i);
		}

		for (int i = 0; i < N; i++) {
			int v = q.poll();
			sb.append(v).append(' ');
			for (int j = 0; j < adjList.get(v).size(); j++) {
				int u = adjList.get(v).get(j);
				if (--inDegree[u] == 0) { // 다시 먼저 시작할 수 있는 조건이라면 큐에 저장
					q.add(u);
				}
			}
		}
	}
}
