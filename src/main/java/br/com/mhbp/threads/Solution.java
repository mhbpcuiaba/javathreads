package br.com.mhbp.threads;

import java.util.Stack;

public class Solution {

    public static void main(String args[]) {
        example1();
        example2();
        System.out.println("END");

    }

    public static void example1() {
        Solution sol = new Solution();
        sol.push(5);
        sol.push(2);                    // stack: [5,2]
        assert sol.top() == 2;
        sol.pop();                      // stack: [5]
        assert sol.top() == 5;

        Solution sol2 = new Solution();
        assert sol2.top() == 0;         // top of an empty stack is 0
        sol2.pop();                     // pop should do nothing
    }

    public static void example2() {
        Solution sol = new Solution();
        sol.push(4);
        sol.begin();                    // start transaction 1
        sol.push(7);                    // stack: [4,7]
        sol.begin();                    // start transaction 2
        sol.push(2);                    // stack: [4,7,2]
        assert sol.rollback() == true;  // rollback transaction 2
        assert sol.top() == 7;          // stack: [4,7]
        sol.begin();                    // start transaction 3
        sol.push(10);                   // stack: [4,7,10]
        assert sol.commit() == true;    // transaction 3 is committed
        assert sol.top() == 10;
        assert sol.rollback() == true;  // rollback transaction 1
        assert sol.top() == 4;          // stack: [4]
        assert sol.commit() == false;   // there is no open transaction
    }






    Stack<Integer> stack;

    public Solution() {
        stack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
    }

    public int top() {
        return stack.peek();
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }

    }

    public void begin() { //consider -1 == begin
        stack.push(-1);
    }

    public boolean rollback() {         //consider -2 == rollback
        if (stack.isEmpty())
            return false;


        Stack<Integer> cloneStack = (Stack) stack.clone();
        while(!cloneStack.isEmpty() && cloneStack.peek() != -1 ) {
            cloneStack.pop();
        }

        if (cloneStack.isEmpty())
            return false;
        else {
            while(stack.peek() != -1) {
                stack.pop();
            }
            stack.pop();
            return true;
        }
    }

    public boolean commit() {
        if (stack.isEmpty())
            return false;


        Stack<Integer> cloneStack = (Stack) stack.clone();

        while(!cloneStack.isEmpty() && cloneStack.peek() != -1 ) {
            cloneStack.pop();
        }

        if (cloneStack.isEmpty())
            return false;
        else {
//            while(stack.peek() != -1 && !stack.isEmpty()) {
//                stack.pop();
//            }
//            stack.pop();
            return true;
        }
    }

    public static void test() {
        // Define your tests here
        Solution sol = new Solution();
        sol.push(42);
        assert sol.top() == 42 : "top() should be 42";
    }
}



/*

    public static void main(String args[])
    {
        // Creating an empty Stack
        Stack<String> STACK = new Stack<String>();
        STACK.push("e");
        System.out.println(STACK.peek());
        System.out.println(STACK.peek());
        System.out.println(STACK.peek());
//
//        // Use push() to add elements into the Stack
//        STACK.push("Welcome");
//        STACK.push("To");
//        STACK.push("Geeks");
//        STACK.push("For");
//        STACK.push("Geeks");
//
//        // Displaying the Stack
//        System.out.println("Initial Stack: " + STACK);
//
//        // Fetching the element at the head of the Stack
//        System.out.println("The element at the top of the"
//                + " stack is: " + STACK.peek());
//
//        // Displaying the Stack after the Operation
//        System.out.println("Final Stack: " + STACK);
    }

 */