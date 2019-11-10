package br.com.mhbp;

import br.com.mhbp.threads.Solution;

import java.util.Stack;

public class Solution2 {

    public static void main(String[] args) {
        example2();
    }

    public static void example2() {
        Solution sol = new Solution();
        sol.push(4);
        sol.begin();                    // start transaction 1
        sol.push(7);                    // stack: [4,7]
        sol.begin();                    // start transaction 2
        sol.push(2);                    // stack: [4,7,2]
//        assert sol.rollback() == true;  // rollback transaction 2
        System.out.println(sol.rollback() == true);
//        assert sol.top() == 7;          // stack: [4,7]
        System.out.println(sol.top() == 7);

        sol.begin();                    // start transaction 3
        sol.push(10);                   // stack: [4,7,10]
//        assert sol.commit() == true;    // transaction 3 is committed
        System.out.println(sol.commit() == true);

//        assert sol.top() == 10;
        System.out.println(sol.top() == 10);

//        assert sol.rollback() == true;  // rollback transaction 1
        System.out.println(sol.rollback() == true);

//        assert sol.top() == 4;          // stack: [4]
        System.out.println(sol.top() == 4);
        //assert sol.commit() == false;   // there is no open transaction
        System.out.println(sol.commit() == false);
    }



    Stack<Integer> stack;

    public Solution2() {
        stack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
    }

    public int top() {
        if (stack.isEmpty()) {
            return 0;
        }
        return stack.peek();
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public void begin() {
        stack.push(-1);
    }

    public boolean rollback() {

        if (stack.isEmpty()) {
            return false;
        }

        Stack<Integer> cloneStack = (Stack) stack.clone();
        while(!cloneStack.isEmpty() && cloneStack.peek() != -1 ) {
            cloneStack.pop();
        }

        if (cloneStack.isEmpty()) {
            return false;
        }
        else {
            while(stack.peek() != -1 && !stack.isEmpty()) {
                stack.pop();
            }
            stack.pop();
            return true;
        }
    }

    public boolean commit() {
        if (stack.isEmpty()) {
            return false;
        }


        Stack<Integer> cloneStack = (Stack) stack.clone();

        while(!cloneStack.isEmpty() && cloneStack.peek() != -1) {
            cloneStack.pop();
        }

        if (cloneStack.isEmpty()) {
            return false;
        }
        else {
//            while(stack.peek() != -1 && !stack.isEmpty()) {
//                stack.pop();
//            }
//            stack.pop();
            return true;
        }
    }

}
