package ����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11052_ī�屸���ϱ�_Main_������ {
	static int N;
	static int []price;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		price =  new int [N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			price[i]= Integer.parseInt(st.nextToken());
		}
		
		sel(0,0,0);
		
		
	}//main

	private static void sel(int i,int p,int n) {
		if(n==N) {//N�� ������ ��
			return;
		}else {  
			
			sel(i,p+price[i],n+i);
			sel((i+1)%4,p,n);
		}
		
	}
}//class
