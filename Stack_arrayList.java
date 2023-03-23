import java.util.*;
public class Stack_arrayList {
    static class stack{
         ArrayList<Integer> s = new ArrayList<>();
        public boolean isEmpty(){
            return s.size() == 0;
        }
        // PUSH
        public void push(int data){
            s.add(data);
        }
        // POP 
        public int pop(){
            if(isEmpty()) {
                return -1;
            }
            int top = s.remove(s.size()-1);
            return top;
        }
        // peek
        public int peek(){
            if(isEmpty()) {
                return -1;
            }
            return s.get(s.size()-1);
        }
    }
    public static void main(String...arg){
        stack s1 = new stack();
        s1.push(1);
        s1.push(2);
        s1.push(3);
        while(!s1.isEmpty()){
            System.out.println(s1.peek());
            s1.pop();
        }
    }
} 
