import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int totalsum=0;
        sumOfFirstN(n,totalsum);
    }

    public static void sumOfFirstN(int n,int totalsum){
        //basecase contains the sum of first n numbers total sum
        if(n==0){
            System.out.println(totalsum);
            return;
        }

        //add the current number to the total sum
        sumOfFirstN(n-1,totalsum+n);
    }
}
