import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ11052_카드구매하기_Main_김우희 {
	public static int n;
	public static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i+1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 2; i < n+1; i++) {
			int max = 0;
			for (int j = 1; j < i; j++) {
				int k = i/j;
				int l = i%j;
				
				int sum = arr[j]*k + arr[l];
				if(sum > arr[i]) {
					arr[i] = sum;
				}
			}
		}
		
		System.out.println(arr[n]);
		
	}

}
