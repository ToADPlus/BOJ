import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	
	public static int n = sc.nextInt();
	public static int m = sc.nextInt();
	public static int v = sc.nextInt(); // 시작
	
	public static boolean[] visited = new boolean[n+1];
	public static int[][] arr = new int[n+1][n+1];
	
	public static Stack<Integer> stack = new Stack<Integer>();
	
	public static void main(String[] args) {
		
		for (int i = 0; i <m ; i++) {
			int one = sc.nextInt();
			int two = sc.nextInt();
			arr[one][two] = 1;
			arr[two][one] = 1;
		}
		
		DFS(v);
		System.out.println();
		
		boolean[] check = new boolean[n+1];
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		check[v] = true;
		q.add(v);
		
		while(!q.isEmpty()) {
			int num = q.poll();
			System.out.print(num + " ");
			
			for (int i = 1; i < arr[num].length; i++) {
				if(!check[i] && arr[num][i] == 1) {
					q.add(i);
					check[i] = true;
				}
			}
		}
	}
	
	public static void DFS(int i) {
		stack.push(i);
		System.out.print(i + " ");
		visited[i] = true;
		
		for (int k = 1; k < arr[i].length; k++) {
			if(!visited[k] && arr[i][k] == 1) {
				DFS(k);
			}
		}
			
	}
}