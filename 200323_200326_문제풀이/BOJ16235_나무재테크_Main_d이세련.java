import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
// 큐로 하면 조금 느림
public class BOJ16235_나무재테크_Main_d이세련 {
	
	static class Tree implements Comparable<Tree>{
		int r, c, age; // 행, 열, 나이

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
		
	}
	
	// s2d2가 겨울에 뿌릴 양분의 양
	static int[][] s2d2;
	// 각 칸 별로 현재 양분의 양
	static int[][] map;
	// 현재 살아 있는 모든 나무들
	static PriorityQueue<Tree> trees = new PriorityQueue<>(); // 우선순위가 높은게 먼저 나오게 되어있다.
	// 봄을 지나 살아남은 나무들이 잠시 머물 곳
	static Queue<Tree> alive = new LinkedList<>();
	// 봄을 지나 죽은 나무들이 잠시 머물 곳
	static Queue<Tree> dead = new LinkedList<>();
	static int N,M,K;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt(); // 내가 몇년뒤에 나무를 확인할지 
//  0. 각 맵의 현재 양분양을 저장할 자료 (2차원)
		map = new int[N+1][N+1];
//  0. 각 맵의 S2D2가 겨울에 추가할 양분의 양을 저장할 자료 (2차원)
		s2d2 = new int[N+1][N+1];
		
		// 입력
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				s2d2[i][j] = sc.nextInt();
				map[i][j] = 5;
			}
		}
		
		for (int i = 0; i < M; i++) {
			trees.add(new Tree(sc.nextInt(),sc.nextInt(),sc.nextInt()));
		}
		
		for (int k = 0; k < K; k++) {
			// 봄
			spring();
			// 여름
			summer();
			// 가을
			autumn();
			// 겨울
			winter();
		}
		
		// 최종 나무 개수 출력
		System.out.println(trees.size());
	}

	static void spring() {
		// trees 큐를 하나씩 꺼내면서
		// 나무의 위치에 양분이 충분하다면 나무의 나이만큼 양분을 없애고 나이한살늘리고 alive 큐로 삽입
		// 아니라면 dead큐로 삽입
		
		while(!trees.isEmpty()) {
				
			Tree t = trees.poll();
			
			if(map[t.r][t.c]>=t.age) {
				// alive
				t.age++;
				alive.add(t);
			}else {
				// dead
				dead.add(t);
			}
		}
	}

	static void summer() {
		// dead큐를 모두 돌면서, 나무의 나이의 반만큼을 나무의 위치에 양분 누적함
		while(!dead.isEmpty()) {
			Tree deadt = dead.poll();
			map[deadt.r][deadt.c] += deadt.age/2;
		}
	}
	private static int[] sero = {-1, -1, -1, 0,0,1,1,1}; // 좌상, 상, 우상, 좌, 우, 좌하, 하, 우하
	private static int[] garo = {-1, 0, 1, -1, 1, -1, 0,1};

	static void autumn() {
		// alive큐를 모두 돌면서, 나이가 5의 배수라면 8방에 대해서 나무를 생성해 trees에 삽입,
		while(!alive.isEmpty()) {
			Tree alivet = alive.poll();
			if(alivet.age%5 ==0 ) {
				int x = alivet.r;
				int y = alivet.c;
				for (int i = 0; i < 8; i++) {
					int nx = x + sero[i];
					int ny = y + garo[i];
					
					if(nx < 1 || ny < 1 || nx >= N+1 || ny >= N+1) { // 배열의 범위를 벗어남
						continue;
					}
					trees.add(new Tree(nx,ny,1));
				}
			}
			// 현재 나무도 삽입
			trees.add(alivet);
		}
	}

	static void winter() {
		// N*N을 탐색하며 map의 각 자리에 s2d2의 각 자리 값만큼을 누적함
		// map 이 현재 갖고 있는 양분 + s2d2 정해져있는 양분의 수
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += s2d2[i][j];
			}
		}
	}
}
