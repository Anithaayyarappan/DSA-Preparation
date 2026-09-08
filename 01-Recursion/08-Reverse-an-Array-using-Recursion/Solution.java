
public class Solution {
    public static void main(String[] args) {
        int []arr={1,2,3,4,5};
        reverseArr(arr,0,arr.length-1);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void reverseArr(int []arr,int low,int high){
        //base case
        if(low>=high){
            return;
        }

        //swapping the elements
        int temp=arr[low];
        arr[low]=arr[high];
        arr[high]=temp;

        //function call
        reverseArr(arr,low+1,high-1);
    }
}
