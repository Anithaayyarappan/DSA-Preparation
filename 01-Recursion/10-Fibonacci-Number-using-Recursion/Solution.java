import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int res=fibonacci(n);
        System.out.println(res);
    }

    public static int fibonacci(int n){
        //base case
        if(n<=1) {
            return n;
        }

        //multiple recursive calls to calculate the fibonacci number ....formula is fib(n)=fib(n-1)+fib(n-2)
        return fibonacci(n-1)+fibonacci(n-2);
    }
}
