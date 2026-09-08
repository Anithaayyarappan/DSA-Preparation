import java.util.Scanner;

public class Solution {
    public static void main(String [] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int fact=factorial(n);
        System.out.println(fact);
    }

    public static int factorial(int n){
        //basecase if 0!=1 and 1!=1
        if(n==0){
            return 1;
        }

        //5!= 5*4*3*2*1  n*factorial(n-1)
        return n*factorial(n-1);
    }
}
