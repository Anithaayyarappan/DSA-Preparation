import java.util.Scanner;

public class Solution {
    public static void main(String [] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int total=1;
        factorial(n,total);
        
    }

    public static void factorial(int n, int total){
        //basecase if 0!=1 and 1!=1
        if(n==0){
            System.out.println(total);
            return;
        }

        //5!= 5*4*3*2*1  n=3   2,3 => 1,6 => 0,6 => print 6
        factorial(n-1,total*n);
    }
}
