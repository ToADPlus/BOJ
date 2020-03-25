package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ1600_말이되고픈원숭이_Main_김우희 {

	static int map[][];
	static int row, col, endX, endY, count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		count = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		endX = row-1;
		endY = col-1;
		
		map = new int[row][col];
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(move());
	}

	static int[][][] directon =  {
			{{-1,0},{1,0}, {0,-1},{0,1}}, // 원숭이의 움직임 (상하좌우)
			{{-1,-2},{1,-2},{-2,-1},{2,-1},{-2,1},{2,1},{-1,2},{1,2}}, // 말의 움직임(8방)
			
	};
	
	private static int move() {
		int moveCnt = 0, temp[] = null, x=0,y=0,nx=0,ny=0,cnt=0;
		
		boolean[][][] visited = new boolean[count+1][row][col]; // 31*200*200 : 120만 바이트		
		
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {count,0,0,0}); // 남은 말의움직임 횟수, x,y,이동한동작횟수
		
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			cnt = temp[0];
			x = temp[1];
			y = temp[2];
			moveCnt = temp[3];
			
			if(x==endX && y==endY) {
				return moveCnt;
			}
			
			
			for (int h = 0; h < 2; h++) { // h:0 ==> 원숭이, 1:말
				
				if(h == 1) { // 말
					
					if(cnt == 0) { // 말의 움직임을 다한 경우
						break;
					} else {
						cnt--;
					}
					
				}
				
				for (int d = 0; d < directon[h].length; d++) {
					nx = x + directon[h][d][0];
					nx=y = y + directon[h][d][1];
					
					if(nx>=0 && nx<row && ny>=0 && ny<col 
							&& map[nx][ny] == 0 && !visited[cnt][nx][ny]) {
						visited[cnt][nx][ny] = true;
						queue.offer(new int[] {cnt,nx,ny,moveCnt+1});
					}
					
				}
			}
			
		}

		return -1;
	}

}
