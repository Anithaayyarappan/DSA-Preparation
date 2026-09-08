import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int sum=sumOfFirstN(n);
        System.out.println(sum);
    }

    public static int sumOfFirstN(int n){
        //base case
        if(n==0) return 0;

        //return the current number + sum of first n-1 numbers
        return n+sumOfFirstN(n-1);
    }
}
