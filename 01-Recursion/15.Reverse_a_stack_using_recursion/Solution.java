import java.util.*;
public class Solution {
    public static void main(String[]args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        Stack<Integer> s=new Stack<>();
        for(int i=0;i<n;i++){
            s.push(in.nextInt());
        }
        reverseStack(s);
        System.out.println(s);
    }
    public static void reverseStack(Stack<Integer> st){
        //basecase
        if(st.isEmpty()){
            return;
        }
        int temp=st.pop();
        reverseStack(st);
        InsertLast(st,temp);
    }
    public static void InsertLast(Stack<Integer> st,int temp){
        //basecase
        if(st.isEmpty()){
            st.push(temp);
            return;
        }
        int ele=st.pop();
        InsertLast(st,temp);
        st.push(ele);
    }
}
