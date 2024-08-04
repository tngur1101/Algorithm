import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N개의 기프티콘
 * 기한 연장을 최소한으로 하고 싶어하고 기프티콘 기한 연장은 한번 하면 30일이 늘어난다.
 * 남은 기프티콘 중 기한이 가장 적게 남은 기포티콘만 사용 가능
 * 기한이 가장 작게 남은게 여러개라면 그 중 아무거나 선택 가능
 * 하루에 여러 기프티콘 사용 혹은 연장 가능
 * 최소 횟수로 기한 연장을 하면서 기프티콘을 ㅅ사용하기
 *
 * 입력
 * 기프티콘 수
 * 기프티콘의 남은 기한들
 * 기프티콘을 사용할 계획들
 *
 * 그럼 일단 연장을 계속하면서
 * B(i)일 후에 써야할 i번째 기프티콘의 사용 기한이 제일 적게 남아야 하는데
 *
 * 그럼 먼저 B값을 보면서 정렬을 한다.
 * 만일 B값이 같다면 A값이 작은걸로 정렬을 한다.
 * 이때 A값과 B의 값을 비교해서 A의 값이 B보다 크게 만들어야 하므로 30씩 더해주면서 더 큰지 안큰지 확인한다.
 *
 */

public class Main {
    static class Gifticon implements Comparable<Gifticon>{
        int a;
        int b;
        public Gifticon(int a, int b){
            this.a = a;
            this.b = b;
        }
        public int compareTo(Gifticon g){
            if(this.b == g.b){
                return this.a - g.a;
            }
            return this.b - g.b;
        }
    }
    static int N;
    static int[] A;
    static int[] B;
    static Gifticon[] arr;
    static long answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[N];
        arr = new Gifticon[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++){
            arr[i] = new Gifticon(A[i], B[i]);
        }

        Arrays.sort(arr);

        answer = 0;

        int prevDueDate = arr[0].b;
        int curMax = -1;

        for(int i = 0; i < N; i++){
            if(prevDueDate > arr[i].a){
                // 이전 구간의 최댓값보다 기프티콘 사용날짜가 크면 갱신
                if(prevDueDate < arr[i].b){
                    prevDueDate = arr[i].b;
                }


                int cnt = ((prevDueDate - arr[i].a) + 29) / 30;
                arr[i].a += (cnt * 30);

                answer += cnt;
            }

            // 같은 구간의 최댓값 찾기
            curMax = Math.max(curMax, arr[i].a);

            // 구간 변경 시 같은 구간 값 중 최댓값을 이전값으로 갱신
            if((i + 1) < N && arr[i].b != arr[i + 1].b){
                prevDueDate = curMax;
            }
        }
        System.out.println(answer);
    }


}