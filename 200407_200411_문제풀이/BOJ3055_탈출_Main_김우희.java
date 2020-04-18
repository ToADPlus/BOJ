package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ3055_탈출_Main_김우희 {
	static int x,y;
//	static int result_x, result_y = 0;
	static int result = 0;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static char[][] str;
	static Queue<Point> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		str = new char[x][y];
		
		q = new LinkedList<Point>();
		Point player = new Point();
		
		for (int i = 0; i < x; i++) {
			 st = new StringTokenizer(br.readLine());
			 String line = st.nextToken();
			for (int j = 0; j < y; j++) {
				str[i][j] = line.charAt(j);
				if(str[i][j] == '*') {
					q.offer(new Point(i, j, 0,true));
				} else if (str[i][j] == 'S') {
					player = new Point(i, j, 0,false);
				} 
			}
		}
		
		q.offer(player);
		
ex:		while(!q.isEmpty()) {
			
			Point s = q.poll();
			
			
			for (int i = 0; i < dx.length; i++) {
				int newX = dx[i] + s.x;
				int newY = dy[i] + s.y;
				
				if(newX >= 0 && newY >= 0 && newX < x && newY < y) {
					if(s.isWater) {
						if(str[newX][newY] == '.' || str[newX][newY] == 'S') {
							str[newX][newY] = '*';
							q.offer(new Point(newX, newY, s.dept+1,true));
						}
						
					} else {
						if(str[newX][newY] == 'D') {
							result = s.dept+1;
							break ex;
						}
						if(str[newX][newY] == '.') {
							str[newX][newY] = 'S';
							q.offer(new Point(newX, newY, s.dept+1, false));
						}
						
					}

				}
				
				
			} // end of for
			
			
		} // end of while
		
		if(result == 0) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(result);
		}
		
	} // end of main

	static class Point {
		private int x;
		private int y;
		private int dept;
		private boolean isWater;
		
		public Point() {
			// TODO Auto-generated constructor stub
		}

		public Point(int x, int y, int dept, boolean isWater) {
			super();
			this.x = x;
			this.y = y;
			this.dept = dept;
			this.isWater = isWater;
		}
		
		
		
		
		
		
	}

}


