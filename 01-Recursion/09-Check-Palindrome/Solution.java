class Solution{
    public static void main(String []args){
        String word="madam";
        int low=0;
        int high=word.length()-1;
        boolean ans=checkPalindrome(word,low,high);
        System.out.println(ans);
    }

    public static boolean checkPalindrome(String word,int low,int high){
        //if pointers meet or cross each other, then the string is a palindrome ..it is a basecase
        if(low>=high){
            return true;
        }

        //if the characters at the pointers are not equal, then the string is not a palindrome
        if(word.charAt(low)!=word.charAt(high)){
            return false;
        }

        //if the characters at the pointers are equal, then we move the pointers closer to each other and check again
        return checkPalindrome(word,low+1,high-1);
    }
}