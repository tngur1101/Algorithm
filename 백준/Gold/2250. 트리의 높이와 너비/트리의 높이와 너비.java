import java.util.*;
import java.io.*;

/**
 * 이진트리를 다음의 규칙에 따라 격자 안에 그림
 * 1. 이진트리에서 같은 레벨에 있는 노드는 같은 행에 위치
 * 2. 한 열에는 한 노드만 존재
 * 3. 임의의 노드의 left subtree에 있는 노드들은 해당 노드보다 왼쪽 열에 위치하고 right subtree에 있느 노드들은 해당 노드보다 오른쪽 열에 위치
 * 4. 노드가 배치된 가장 왼쪽 열과 가장 오른쪽 열 사이엔 아무 노드도 없이 비어 있는 열은 없다.
 *  트리 구성 -> 노드를 선언할 때 1번이 root 노드인가? -> 아무거나 노드가 될 수도 있음
 *
 *  순회 방법
 *  1. preorder(전위 순회) : root - left - right
 *  2. inorder(중위 순회) : left - root - right
 *  3. postorder(후위 순회) : left- right - root
 *
 *  흠... 저 위에서 어떤 걸 선택해야 할까
 */

public class Main {
    static class Node{
        int parent;
        int node;
        int left;
        int right;
        public Node(int parent, int node, int left, int right){
            this.parent = parent;
            this.node = node;
            this.left = left;
            this.right = right;
        }
    }
    static int N; // 노드의 개수
    static List<Node> tree = new ArrayList<>();
    static int[] minX;
    static int[] maxX;
    static int root, maxLevel =0, x=1;
    static int ansLevel, ansWidth;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++){
            tree.add(new Node(-1, i, -1, -1));
        }

        minX = new int[N+1];
        maxX = new int[N+1];

        Arrays.fill(minX, Integer.MAX_VALUE);

        for(int i = 1; i <= N; i++){
//            System.out.println("i: "+i);
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

//            System.out.println("node: " + node + ", left: "+left + ", right: "+right);
            tree.get(node).left = left;
            tree.get(node).right = right;

            if(left != -1) tree.get(left).parent = node;
            if(right != -1) tree.get(right).parent = node;
        }

        setRoot();

        inOrder(root, 1);

        ansLevel = 1;
        ansWidth = 1;

        getAnswer();
        System.out.println(ansLevel + " " + ansWidth);
    }

    private static void setRoot(){
        for(int i = 1; i <= N; i++){
            if(tree.get(i).parent == -1) root = i;
        }
    }

    private static void inOrder(int root, int level){
        Node now = tree.get(root);

        if(maxLevel < level) maxLevel = level;

        // 가장 왼쪽에 있는 노드 탐색
        if(now.left != -1) inOrder(now.left, level+1);

        // 가장 왼쪽에 있는 노드에 왔으면
        minX[level] = Math.min(minX[level], x);
        maxX[level] = x++;

        if(now.right != -1) inOrder(now.right, level+1);
    }

    private static void getAnswer(){
        for(int i = 1; i <= maxLevel; i++){
            int temp = maxX[i] - minX[i]+1;
            if(ansWidth < temp){
                ansLevel = i;
                ansWidth = temp;
            }
        }
    }

}