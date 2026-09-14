// Input stack:
// Bottom  [4, 1, 3, 2]  TOP
import java.util.*;
class Solution{
    public static void main(String []args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        Stack<Integer> s=new Stack<>();
        for(int i=0;i<n;i++){
            s.push(in.nextInt());
        }
        sortStack(s);
        for(int i=0;i<n;i++){
            System.out.print(s.pop()+" ");
        }
    }
    public static void sortStack(Stack<Integer> st){
        //base case
        if(st.isEmpty()){
            return;
        }

        int temp=st.pop();
        sortStack(st);
        insertAtCorrectPossition(st,temp);
    }

    public static void insertAtCorrectPossition(Stack<Integer> st,int temp){
        //basecase
        if(st.isEmpty() || st.peek()<=temp){
            st.push(temp);
            return;
        }

        int ele=st.pop();
        insertAtCorrectPossition(st,temp);
        st.push(ele);
    }
}