import java.sql.DataTruncation;
import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    static int[] array = new int [300000];

    static int binarySearch(int a[], int l, int r, int x) {
        if (l == r) return a[l];
        int mid = (l + r) >> 1;
        if (a[mid] < x) return binarySearch(a, mid + 1, r, x);
        else return binarySearch(a, l, mid, x);
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 300000; i ++) array[i] = random.nextInt();

        int index = random.nextInt(300000);
        int value = array[index];

        Arrays.sort(array);

        System.out.print(binarySearch(array, 0, 300000, value));
        return;
    }

}
