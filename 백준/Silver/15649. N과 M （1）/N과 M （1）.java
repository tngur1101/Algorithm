import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static int n,m;
	static int[] arr;
	static int[] out;
	static boolean[] visited;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		
		for(int i=0;i<n;i++) {
			arr[i]=i+1;
		}
		visited = new boolean[n];
		out = new int[m];
		
		permutation(arr, out, visited, 0, m);
	}

	public static void permutation(int[] arr, int[] out, boolean[] visited, int depth, int r) {
		if(depth==r) {
			for(int a: out) {
				System.out.print(a+" ");
			}
			System.out.println();
			return;
		}
		
		if(depth==arr.length) {
			return;
		}
		else {
			for(int i=0;i<n;i++) {
				if(!visited[i]) {
					visited[i]=true;
					out[depth]=arr[i];
					permutation(arr, out, visited, depth+1, r);
					visited[i]=false;
				}
			}
		}
		
	}
	
}
