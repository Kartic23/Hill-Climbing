import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HillClimbing bfs = new HillClimbing();
        int n = sc.nextInt();
        double start = System.currentTimeMillis();
        Board result = bfs.solve(n);
        System.out.println((System.currentTimeMillis() - start)/1000);
        System.out.println(result);

    }
}
