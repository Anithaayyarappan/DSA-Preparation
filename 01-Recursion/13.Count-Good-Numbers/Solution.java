import java.util.*;
class Solution{
    static final long MOD = 1_000_000_007;
    public static void main(String []args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int result=countGoodNumbers(n);
        System.out.println(result);
    }

    public static int countGoodNumbers(int n){
        //find num of even indices
        long even_count=(n+1)/2;  //if n=5, even_count=3 (0,2,4)
        //find num of odd indices
        long odd_count=n/2;  //if n=5, odd_count=2 (1,3)

        //calculate 5^even_count ... because even indices can have 0,2,4,6,8 (5 choices)
        long even_result= power(5,even_count);
        //calculate 4^odd_count ... because odd indices (prime numbers) can have 2,3,5,7 (4 choices)
        long odd_result=power(4,odd_count);

        //return the product of both results
        return (int)((even_result*odd_result)%MOD);
    }

    public static long power(long x,long n){  //base,exponent
        if(n==0){
            return 1;
        }

        long half=power(x,n/2); //why half? because we can reduce the problem size by half..for eg, 2^8 = 2^4 * 2^4, so we can calculate 2^4 once and multiply it by itself
        if(n%2==0){
            return (half*half)%MOD;  //if n is even, we can just return half*half
        }


        return (x*half*half)%MOD;  //if n is odd, we need to multiply by x one more time
    }

}