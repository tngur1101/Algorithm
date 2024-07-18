import java.util.*;
import java.io.*;

public class Main {

    static int[] dice = new int[7];
    static int N,M,X,Y;
    static int[][] map;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }


    }

    private static void move(int d) {
        int nx = X + dx[d-1];
        int ny = Y + dy[d-1];
        if(isInRange(nx, ny)) return;
        int temp = dice[3];

        if(d == 1){
            dice[3] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        } else if(d == 2){
            dice[3] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        } else if(d == 3){
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[1];
            dice[1] = temp;
        } else if(d == 4){
            dice[3] = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }

        if(map[ny][nx] == 0){
            map[ny][nx] = dice[6];
        } else {
            dice[6] = map[ny][nx];
            map[ny][nx] = 0;
        }
        System.out.println(dice[3]);
        X = nx; Y = ny;

    }

    private static boolean isInRange(int r, int c){
        return r<0 || c < 0 || r > M-1 || c > N-1;
    }
}