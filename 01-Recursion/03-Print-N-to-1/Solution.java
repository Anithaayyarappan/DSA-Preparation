import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        printNto1(n);
    }
    public static void printNto1(int n){
        //basecase
        if(n==0) return;

        //1st print the current number because we want to print from n to 1
        System.out.print(n+" ");

        //function call
        printNto1(n-1);
    }
}
