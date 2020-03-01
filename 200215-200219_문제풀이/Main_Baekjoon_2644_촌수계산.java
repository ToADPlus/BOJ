import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class Main_Baekjoon_2644_촌수계산 {
	public static int[][] arr;
	public static boolean[] visited;
	
	public static int result;
	public static int result2;
	
	public static int real = -1;
	public static int num = 0;
	
//	public static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		result = sc.nextInt();
		result2 = sc.nextInt();
		
		int cnt = sc.nextInt();
		
		arr = new int[n+1][n+1];
		visited = new boolean[n+1];

		for (int i = 0; i < cnt; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			arr[parent][child] = 1;
			arr[child][parent] = 1;
		}
		
		dfs(result,num);
		
		if(real == result2) {
			System.out.println(num);
		} else {
			System.out.println(-1);
		}

		
	}

	public static void dfs(int a, int sum) {
		visited[a] = true;
		
		for (int i = 1; i <arr.length; i++) {
			if(arr[a][i] == 1 && !visited[i]) {
				if(i == result2) {
					real = i;
					num = sum+1;
					return;
				}
				dfs(i,sum+1);
			}
		}
	}


}
