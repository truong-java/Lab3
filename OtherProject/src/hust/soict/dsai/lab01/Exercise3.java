import java.util.Scanner;

public class Exercise3{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the high of the triagle (n): ");
        int n=scanner.nextInt();
        for (int i=1; i<=n;i++){
            for(int j=1;j<= n-i;j++){
                System.out.print(" ");
            }
            System.out.println();
            for(int k=1;k <= (2 * i-1);k++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}