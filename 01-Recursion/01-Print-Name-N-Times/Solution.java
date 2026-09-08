import java.util.*;

class Solution{
    public static void main(String []args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        printName(n);
    }


    public static void printName(int n){
        //base case
        if(n==0) return;
        //Print
        System.out.println("Abi");
        //fun call
        printName(n-1);
    }

}