import java.io.PushbackInputStream;
import java.util.*;
import java.util.Stack;
public class StackJFW {

    // 1 push at bottom of the stack
    public static void pushAtBottom(Stack<Integer> s, int data){
        if(s.isEmpty()){
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(s,data);
        s.push(top);
    }

    // 2 Reverse String
    public static String reverseString(String str){
        Stack<Character> s = new Stack<>();
        int idx = 0;
            while(idx < str.length()){
                s.push(str.charAt(idx));
                idx++;
            }
        StringBuilder result = new StringBuilder("");
            while(!s.isEmpty()){
                char curr = s.pop();
                result.append(curr);
            }
        return result.toString();
    }

    // 3 reverse a Stack
    public static void reverseStack(Stack<Integer> s1){
        if(s1.isEmpty()){
            return;
        }
        int top = s1.pop();
        reverseStack(s1);
        pushAtBottom(s1,top);
    }
    // print Stack
    public static void printStack(Stack<Integer> s1){
        while(!s1.isEmpty()){
            System.out.println(s1.pop());
        }
    }

    // 4 Stock Span problem   ????
    public static void stockSpan(int stocks[], int span[]){
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(0);  // push index
        for(int i=1; i<stocks.length; i++){
            int currPrice = stocks[i];
                while(!s.isEmpty() && currPrice > stocks[s.peek()]){
                    s.pop();
                }
                    if(s.isEmpty()){
                        span[i] = i+1;
                    } else{
                        int prevHigh = s.peek();
                        span[i] = i-prevHigh;
                    }
                
            s.push(i);
        }
    }

    // 5 valid parenthesis 
    public static boolean isValid(String str){
        Stack<Character> c = new Stack<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(' || ch == '{' || ch == '['){
                c.push(ch);      // push opening ({[
            } else{
                if(c.isEmpty()){ // there is no opening ({[ in stack
                    return false;
                }
                if( (c.peek() == '(' && ch == ')') ||    // () pair
                    (c.peek() == '{' && ch == '}') ||    // {}
                    (c.peek() == '[' && ch == ']') ){    // []
                        c.pop();
                } else{
                    return false;
                }
            }
        }
        if(c.isEmpty()){
            return true;
        } else{
            return false;
        }
    }

    // 6 duplicate parentheses
    public static boolean isDuplicate(String str){
        Stack<Character> s = new Stack<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            // closing )
            if(ch == ')'){
                int count = 0;
                while(s.pop() != '('){
                    count++;
                }
                if(count < 1){
                    return true; // duplicate exist
                }
            } else{
                s.push(ch);
            }
        }
        return false;
    }

    // 7 maximum area in histogram - O(n)t64    WERYIOP[]
    public static void maxArea(int arr[]){
        int maxArea = 0;
        int nsl[] = new int[arr.length];
        int nsr[] = new int[arr.length];
        // next smaller right (j) - O(n)
        Stack<Integer> s = new Stack<>();
        for(int i=arr.length-1; i>=0; i--){
            while(!s.isEmpty() && arr[s.peek()] >= arr[i]){
                s.pop();
            }
            if(s.isEmpty()){
                nsr[i] = arr.length;
            } else{
                nsr[i] = s.peek();
            }
            s.push(i);
        }
        // next smaller left (i) - O(n)
        s = new Stack<>();     // make the stack s empty 
        for(int i=0; i<arr.length; i++){
            while(!s.isEmpty() && arr[s.peek()] >= arr[i]){
                s.pop();
            }
            if(s.isEmpty()){
                nsl[i] = arr.length;
            } else{
                nsl[i] = s.peek();
            }
            s.push(i);
        }
        // current area - O(n)
        for(int i=0; i<arr.length; i++){
            int height = arr[i];
            int width = nsr[i]-nsl[i]-1;  // j-i-1
            int currArea = height * width;
            maxArea = Math.max(maxArea, currArea);
        }
        System.out.println("Maximum area of histogram is : " + maxArea);
    }

    public static void main(String args[]) {
        Stack<Integer> s = new Stack<>();
     
        s.push(1);
        s.push(2);
        s.push(3);

        while(!s.isEmpty()) {
            System.out.println(s.pop()); 
        }

        // 1
        pushAtBottom(s,4);
        while(!s.isEmpty()) {
            System.out.println(s.pop());
        }                                   

        // 2
        String str = "acbd";
        String result = reverseString(str);
        System.out.println(str + "  -->  After reverse -->  " + result);

        // 3 
        Stack<Integer> s1 = new Stack<>();
        s1.push(1);
        s1.push(2);
        s1.push(3);       // 3->2->1
        reverseStack(s1); // 1->2->3
        printStack(s1);

        // 4 
        int stocks[] = {100,80,60,70,60,85,100};
        int span[] = new int[stocks.length];
        stockSpan(stocks, span);
        for(int i=0; i<span.length; i++){
            System.out.print(span[i] + " ");
        }

        // next greater element in right
        int arr[] = {6,8,0,1,3};
        Stack<Integer> s1 = new Stack<>();
        int nxtGreater[] = new int[arr.length];
        for(int i=arr.length-1; i>=0; i++){
            // 1 while loop
            while(!s1.isEmpty() && arr[s1.peek()] <= arr[i]){
                s1.pop();
            }
            // 2 if-else check
            if(s1.isEmpty()){
                nxtGreater[i] = -1;
            } else{
                nxtGreater[i] = arr[s1.peek()];
            }
            // push in stack
            s1.push(i);
        }
        for(int i=0; i<nxtGreater.length; i++){
            System.out.print(nxtGreater[i] + " ");
        }

        // 5 
        String str = "(){[]}";
        String str2 = "(({}}";
        System.out.println(str + "  valid parentheses -> " + isValid(str));   // true
        System.out.println(str2 + "  valid parentheses -> " + isValid(str2));  // false

        // 6
        String str = "((a+b))"; // true - duplicate exist
        String str2 = "(a+b)";  // false - not exist
        System.out.println(str + " duplicate parentheses exist -> " + isDuplicate(str));
        System.out.println(str2 + " duplicate parentheses exist -> " + isDuplicate(str2));

        // 7
        int arr[] = {2,1,5,6,2,3};
        maxArea(arr);
    }}
