package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ6603_로또_Main_김형준 {
	static int K;
	static int []arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		do {
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		arr = new int [K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		boolean []visit =  new boolean [K];
		int []l = new int [6];
		per(l,visit,0);
		System.out.println();}while(K!=0);
	
	}
	private static void per(int []l,boolean []visit, int i) {
		if(i==6) {//종료
			for (int j = 0; j < l.length-1; j++) {
				if(l[j]>l[j+1])return;
			}
			for (int j = 0; j < l.length; j++) {
				System.out.print(l[j]+" ");
			}System.out.println();
			return;
		}else {
			for (int j = 0; j < K; j++) {
				if(!visit[j]) {
					visit[j] =true;
					l[i] =arr[j];
					per(l,visit,i+1);
					visit[j]=false;
				}
			}
		}
	}
}
