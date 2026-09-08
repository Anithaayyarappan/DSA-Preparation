import java.util.*;
class Solution{
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        print1toN(n);
    }
    public static void  print1toN(int n){
        //basecase
        if(n==0){
            return;
        }

        //function call because we want to print from 1 to n
        print1toN(n-1);

        System.out.print(n+" ");
    }
}