package 백준;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BOJ1260_DFS와BFS_Main_김형준 {
	public static boolean[] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();// 정점의 개수
		int M = sc.nextInt();// 간선의 개수
		int V = sc.nextInt();// 탐색시작 번호

		int[][] a = new int[N][N];
		for (int i = 0; i < M; i++) {
			int aa = sc.nextInt() - 1;
			int bb = sc.nextInt() - 1;
			a[aa][bb] = 1;
			a[bb][aa] = 1;
		}
		
		visit = new boolean [N];
		DFS(a,V,visit);
		System.out.println();
		BFS(a,V);
		
		
	}// main

	public static void BFS(int[][] arr, int V) {

		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visit = new boolean[arr.length];
		q.offer(V);
		visit[V - 1] = true;
		while (!q.isEmpty()) {
			V = q.poll();
//			visit[V-1]=true;
			System.out.print(V+" ");
			
			for (int i = 0; i < arr.length; i++) {
				if (arr[V - 1][i] == 1 && !visit[i]) {
					q.offer(i + 1);
					visit[i]=true;
				}
			}

		}

	}
	
	
	public static void DFS(int [][]arr,int V, boolean []visit) {
		visit[V-1]=true;
		System.out.print(V+" ");
		for (int i = 0; i < visit.length; i++) {
			if(arr[V-1][i]==1 && !visit[i]) {
				DFS(arr,i+1,visit);
			}
		}
		
		
		
	}
	

}// class
