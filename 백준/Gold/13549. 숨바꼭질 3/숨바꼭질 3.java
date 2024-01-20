import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤
 * 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로
 * 이동하게 된다. 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다. 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을
 * 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.
 *
 * 문제 해결을 위한 고민)
 * 기존 문제는 다 같은 1초였는데 이번엔 숨바꼭질이 0초
 * turn을 object에 포함시켜서 해보자
 *
 * @author GODJUHYEOK
 *
 */


public class Main {

    private static class Info {
        int turn;
        int point;

        public Info(int point, int turn) {
            this.point = point;
            this.turn = turn;
        }

    }

    private static final int END = 100_001;
    private static int N, K, visited[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N>=K) {
            System.out.println(N-K);
            return;
        }

        visited = new int[END];

        Arrays.fill(visited, END);

        Queue<Info> q = new ArrayDeque<Info>();

        visited[N] = 0;
        q.offer(new Info(N, 0));

        while (!q.isEmpty()) {

                int qSize = q.size();
                for(int i=0; i<qSize; i++) {

                    Info temp = q.poll();
                    if (temp.point == K) {
                        System.out.println(visited[K]);
                        return;
                    }
                    if (visited[temp.point] < temp.turn - 1)
                        continue;

                    if (temp.point * 2 < END && visited[temp.point * 2] >= temp.turn) {
                        visited[temp.point * 2] = temp.turn;
                        q.offer(new Info(temp.point * 2, temp.turn));
                    }

                    if (temp.point + 1 < END && visited[temp.point + 1] > temp.turn + 1) {
                        visited[temp.point + 1] = temp.turn + 1;
                        q.offer(new Info(temp.point + 1, temp.turn + 1));
                    }

                    if (temp.point - 1 >= 0 && visited[temp.point - 1] > temp.turn + 1) {
                        visited[temp.point - 1] = temp.turn + 1;
                        q.offer(new Info(temp.point - 1, temp.turn + 1));
                    }



                }
        }

    }

}