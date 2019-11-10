package br.com.mhbp.threads.pcd;

import java.util.Stack;

public class RuntimePCD {

    private static final ThreadLocal<Stack<BaseTask>> threadLocalTaskStack = new ThreadLocal<Stack<BaseTask>>() {
        protected Stack<BaseTask> initialValue(){
            return new Stack<>();
        }
    };
}
