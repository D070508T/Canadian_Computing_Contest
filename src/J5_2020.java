import java.util.Scanner;

public class J5_2020 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int[][] arr = new int[M][N];
        scanner.nextLine();
        for (int i = 0; i < M; i++) {
            load(scanner.nextLine(), arr[i], 0);
        }

        if (next(arr, 0, 0)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public static boolean next(int[][] arr, int x, int y) {
        if (x == arr.length-1 && y == arr[0].length-1) {
            return true;
        }

        for (int n = 1; n <= arr[x][y]; n++) {
            if (n-1 < arr.length && arr[x][y] % n == 0) {
                int n2 = arr[x][y] / n;

                if (n-1 <= arr.length-1 && n2-1 <= arr[0].length-1 && next(arr, n-1, n2-1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void load(String str, int[] arr, int index) {
        if (str.indexOf(' ') == -1) {
            arr[index] = Integer.parseInt(str);
        } else {
            arr[index] = Integer.parseInt(str.substring(0, str.indexOf(' ')));
        }

        if (index == arr.length-1) {
            return;
        }

        load(str.substring(str.indexOf(' ')+1), arr, index+1);
    }
}