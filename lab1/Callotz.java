public class Callotz {
    public static void main(String[] args) {
        if (args.length < 1) {
            return;
        }
        int n = Integer.valueOf(args[0]).intValue();
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
