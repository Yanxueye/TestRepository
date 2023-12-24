import java.util.Scanner;

public class permutation {
    static boolean[] vis = new boolean[20];
    static int[] per = new int [20];

    static void dfs(int p, int n) {
        if (p == n + 1) {
            for (int i = 1; i <= n; i ++) System.out.print(per[i] + " ");
            System.out.println();
            return;
        }
        for (int i = 1; i <= n; i ++) {
            if (vis[i]) continue;
            vis[i] = true; per[p] = i;
            dfs(p + 1, n);
            vis[i] = false; per[p] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        dfs(1, n);

        return;
    }
}
