package πÈ¡ÿ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class BOJ2178_πÃ∑Œ≈Ωªˆ_Main_±Ë«¸¡ÿ {
	public static int [][]mat ;
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				mat[i][j] = s.charAt(j)-'0';
			}
		}
		bfs();
		
		
	}
	
	
	public static void bfs() {
		Queue<Integer> qy = new LinkedList<Integer>	();
		Queue<Integer> qx = new LinkedList<Integer>	();
		boolean visit[][] = new boolean [mat.length][mat[0].length];
		int[]wy = {-1,1,0,0};//ªÛ«œ¡¬øÏ
		int[]wx = {0,0,-1,1};
		qy.add(0);
		qx.add(0);
		visit[0][0] = true;
		int cnt = 0;
ex:	while(!qy.isEmpty()) {
			int qsize = qy.size();
			cnt++;
			while(qsize>0) {
				qsize --;
				int y = qy.poll();
				int x = qx.poll();
				for (int i = 0; i < wx.length; i++) {
					int ny = y+wy[i];
					int nx = x+wx[i];
					if(ny >=0 && nx>=0 && ny<mat.length && nx<mat[0].length && mat[ny][nx]==1 &&!visit[ny][nx] ) {
						qy.offer(ny);
						qx.offer(nx);
						visit[ny][nx] = true;
						if(ny==mat.length-1 && nx == mat[0].length-1)
							break ex;
					}
				}
				
			}
			
			
		}
		System.out.println(cnt+1);
		
	}
	
	
}