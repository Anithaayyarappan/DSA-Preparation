import java.util.*;
public class Solution {
    public static void main(String[] args){
        // Scanner in=new Scanner(System.in);
        String s="   -1234";
        int ans = atoi(s);
        System.out.println(ans);
    }

    public static int atoi(String s){
        int i=0;

        while(i<s.length() && s.charAt(i)==' '){
            i++;
        }

        //sign
        int sign=1;
        if(i<s.length() && (s.charAt(i)=='+' || s.charAt(i)=='-')){
            if(s.charAt(i)=='+'){
                sign=1;
            }
            else{
                sign=-1;
            }
            i++;  //move to next character after sign
        }

        return helper(s,i,0,sign);  //(string,i,num(actual calculate num initially 0),sign)

    }

    public static int helper(String s,int i,long num,int sign){
        //basecase
        if(i>=s.length() || !Character.isDigit(s.charAt(i))){
            return (int)num*sign;
        }
        //calculate the number 
        //1st understand convert string to  integer => s.charAt(i)-'0'
        //2nd calculation of num=  num=num*10  => 0*10 = 0   1=1*10-(s.charAt(i)'0')=12  12*10-(s.charAt(i)'0')=123
        num=num*10+(s.charAt(i)-'0');

        //for overflow check
        if(num*sign>Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        if(num*sign<Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }

        return helper(s,i+1,num,sign);

    }
}
