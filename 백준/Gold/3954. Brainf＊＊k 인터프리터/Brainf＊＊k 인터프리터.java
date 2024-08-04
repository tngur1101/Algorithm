import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int I = Integer.parseInt(st.nextToken());

            int[] arr = new int[M];
            char[] code = br.readLine().toCharArray();
            char[] input = br.readLine().toCharArray();

            Stack<Integer> stack = new Stack<>();

            int[] start = new int[C];
            int[] end = new int[C];

            for(int i = 0; i < C; i++){
                if(code[i] == '['){
                    stack.push(i);
                } else if(code[i] == ']'){
                    int position = stack.pop();
                    start[position] = i;
                    end[i] = position;
                }
            }

            int pointer = 0, inputPointer = 0;
            int cnt = 0;
            int answer = 0;

            for(int i = 0; i < C; i++){
                switch(code[i]) {
                    case '+':
                        arr[pointer] = (arr[pointer]+1)%256;
                        break;
                    case '-':
                        arr[pointer] = (arr[pointer]+255)%256;
                        break;
                    case '<':
                        pointer = (pointer + M -1)%M;
                        break;
                    case '>':
                        pointer = (pointer+1)%M;
                        break;
                    case '[':
                        if(arr[pointer]==0) {
                            i = start[i];
                        }
                        break;
                    case ']':
                        if(arr[pointer]!=0) {
                            if(cnt>50_000_000) {
                                answer = Math.max(answer, i);
                            }
                            i=end[i];
                        }
                        break;
                    case ',':
                        arr[pointer]=(inputPointer>=I)?255:input[inputPointer++];
                        break;
                    default:
                        continue;
                }
                cnt++;
                if(cnt>100_000_000) {
                    break;
                }
            }

            if(cnt>50_000_000) {
                sb.append("Loops ").append(end[answer]).append(" ").append(answer).append("\n");
            }
            else{
                sb.append("Terminates\n");
            }
        }
        System.out.println(sb);
    }
}