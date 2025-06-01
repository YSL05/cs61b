public class Collatz {
    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = getNextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public static int getNextNumber(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return n * 3 + 1;
        }
    }
}
