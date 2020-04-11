import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_9466_텀프로젝트_Main_김형준 {
	static int n;
	static boolean ck[];
	static int ans;

	 static boolean visit[] ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int i = 0; i < TC; i++) {
			ans = 0;
			n = Integer.parseInt(br.readLine());
			ck = new boolean[n + 1];
			int[] arr = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());

			}
			visit = new boolean[n + 1];
			for (int j = 1; j <= n; j++) {
				if (!ck[j]) {
					dfs(arr, j, j);
				}
			}
			System.out.println(n - ans);

		}

	}

	private static void dfs(int[] arr, int S, int idx) {
		// 타고들어가서 사이클인지 확인
		visit[idx] = true;
		if (S == arr[idx]) {
			for (int i = arr[S];; i = arr[i]) {
				ans++;
				// System.out.println(idx+" "+i);
				ck[i] = true;
				if (i == S)
					break;
			}
//			
//			for (int i = 0; i < visit.length; i++) {
//				System.out.println(idx+"  "+visit[i]);
//			}

		} else {
			if (!visit[arr[idx]]) {
				dfs(arr, S, arr[idx]);
			}
		}
		
		
	}
}
