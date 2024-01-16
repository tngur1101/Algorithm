import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N,K;
    private static class Friend {
        int nameLen;
        int rank;
        public Friend(int nameLen, int rank){
            this.nameLen = nameLen;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Friend{" +
                    "nameLen=" + nameLen +
                    ", rank=" + rank +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Friend[] friends = new Friend[N];
        long answer= 0;

        int rank=0;
        for(int i=0; i<N; i++){
            String s = br.readLine();
            int tempNameLen = s.length();
            friends[i] = new Friend(tempNameLen, rank++);
        }

//        System.out.println(Arrays.toString(friends));

        //이름의 길이를 담은 배열
        int[] names = new int[21];

        int temp = 0;
        Queue<Friend> queue = new LinkedList<>();

        for(Friend f : friends){
            //개수 증가
            temp++;

            if(temp > K+1){
                Friend friend = queue.poll();
                names[friend.nameLen]--;
                temp--;
            }

            names[f.nameLen]++;
            queue.offer(f);
            if(names[f.nameLen]>=2){
                int a = names[f.nameLen];
                answer += --a;
            }
//            System.out.println(Arrays.toString(names));
        }

        System.out.println(answer);
    }
}