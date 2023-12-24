import java.util.Scanner;

public class Recursion {
    static long fib(int n) {
        if (n == 0 || n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    static int Counter = 0;
    static void Hanoi(char a, char b, char c, int cnt) {
        if (cnt == 0) return;
        Hanoi(a, c, b, cnt - 1);
        System.out.println("put " + cnt + " from " + a + " to " + b);
        Counter ++;
        Hanoi(c, b, a, cnt - 1);
        return;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        System.out.println(fib(n));

        Hanoi('a', 'c', 'b', n);
        System.out.println("Total Steps : " + Counter);
        return;
    }
}
