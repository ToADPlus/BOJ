import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ6603_로또_Main_김우희 {
	public static int k;
	
	public static int[] arr;
	public static int[] trr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			
			if(k == 0) {
				break;
			} else {
				int r = 6;
				arr = new int[k];
				trr = new int[r];
				
				for (int i = 0; i < arr.length; i++) {
					arr[i] = Integer.parseInt(st.nextToken());
				}
				
				comb(k,r);
				
				System.out.println();
			}
		}
		
	}

	public static void comb(int k, int r) {
		if(r == 0) {
			for (int i = 0; i < 6; i++) {
				System.out.print(trr[i] + " ");
			}
			System.out.println();
		} else if (k < r) {
			return;
		} else {
			trr[r-1] = arr[k-1];
			comb(k-1,r-1);
			comb(k-1,r);
		}
	}

}
