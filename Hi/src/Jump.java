import java.util.Scanner;

public class Jump {
    static void kmp(String model, String text) {
        int n = text.length(), p = 0;
        int[] border = new int [n];
        border[0] = -1;

        for (int i = 1; i < n; i ++) {
            p = i - 1;
            while (p != -1 && text.charAt(border[p] + 1) != text.charAt(i)) {
                p = border[p];
            }
            if (p != -1) border[i] = border[p] + 1;
            else border[i] = -1;
        }

        p = 0;
        for (int i = 0; i < model.length(); i ++) {
            while (p != 0 && text.charAt(p) != model.charAt(i)) {
                p = border[p - 1] + 1;
            }
            if (p == 0 && text.charAt(p) == model.charAt(i)) p ++;
            else if (p != 0) p ++;
            if (p == n) {
                System.out.print((i - n + 1) + " ");
                p = border[p - 1] + 1;
            }
        }
        return;
    }
    public static void main(String[] args) {
        String str1, str2;
        Scanner scan = new Scanner(System.in);

        str1 = scan.next();
        str2 = scan.next();

        kmp(str1, str2);
        return;
    }
}
