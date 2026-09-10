import java.util.*;
public class Solution {
    public static void main(String [] args){
        Scanner in=new Scanner(System.in);
        double x=in.nextDouble();
        int n=in.nextInt();
        System.out.println(myPow(x,n));
    }

    public static double myPow(double x,int n){
        long temp=n;
        if(temp<0){
            return 1.0/calculatePower(x,-temp);
        }
        //if n is positive
        return calculatePower(x,temp);
    }

    public static double calculatePower(double x,long n){
        if(n==0) return 1;
        if(n==1) return x;

        //if n is even exponent
        if(n%2==0){
            return calculatePower(x*x,n/2);
        }

        //if odd exponent
        return x*calculatePower(x,n-1);
    }

}
