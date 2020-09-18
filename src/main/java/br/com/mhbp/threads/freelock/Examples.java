package br.com.mhbp.threads.freelock;
//
//import sun.misc.Unsafe;
//
//import java.util.function.IntBinaryOperator;

//http://javabypatel.blogspot.com/2016/01/infinite-loop-in-hashmap.html
//https://www.baeldung.com/lmax-disruptor-concurrency
//https://herbsutter.com/2008/11/02/out-of-order-effective-concurrency-writing-lock-free-code-a-corrected-queue/
//https://docs.microsoft.com/en-us/windows/win32/dxtecharts/lockless-programming?redirectedfrom=MSDN
//https://www.drdobbs.com/parallel/writing-lock-free-code-a-corrected-queue/210604448
//https://www.baeldung.com/java-unsafe
//https://dzone.com/articles/understanding-sunmiscunsafe
//http://mishadoff.com/blog/java-magic-part-4-sun-dot-misc-dot-unsafe/
public class Examples {
//
//    private static final Unsafe unsafe = Unsafe.getUnsafe();
//    private static final long valueOffset;
//    private volatile int value;
//
//    public boolean compareAndSet(int currentValue, int newValue) {
//        return unsafe.compareAndSwapInt(this, valueOffset, currentValue, newValue);
//    }
//
//    public final int getAndIncrement() {
//        return unsafe.getAndAddInt(this, valueOffset, 1);
//    }
//    public final int getValue() {
//        return this.value;
//    }
//
//    public final int getAndAccumulate(int var1, IntBinaryOperator var2) {
//        int var3;
//        int var4;
//        do {
//            var3 = this.getValue();
//            var4 = var2.applyAsInt(var3, var1);
//        } while(!this.compareAndSet(var3, var4));
//
//        return var3;
//    }
//
//    public final int getAndAddInt(Object var1, long var2, int var4) {
//        int var5;
//        do {
//            var5 = unsafe.getIntVolatile(var1, var2);
//        } while(!unsafe.compareAndSwapInt(var1, var2, var5, var5 + var4));
//
//        return var5;
//    }
//
//    static {
//        try {
//            valueOffset = unsafe.objectFieldOffset(Examples.class.getDeclaredField("value"));
//        } catch (Exception var1) {
//            throw new Error(var1);
//        }
//    }
}
/*

Next month Martin Thompson is coming to Singapore for a 1-day workshop “High-Performance Messaging with Aeron”. I have already bought a ticket, and it makes sense to review the material and notes from his workshop which I attended last year. It is also a good opportunity to share my impression about it.
A few words about Martin. First, he is a consultant and a coach who specialise in design of high-performance systems. Next, Martin is a java champion and an author of Mechanical Sympathy blog, he also organised the Mechanical Sympathy google discussion group.
Last year workshop was about “Lock-free & high-performance concurrent algorithms”. A couple of words about organization of workshop. All tickets were sold out in advance. Workshop took one day and had two parts. During the morning session Martin discussed the CPU and memory subsystem architecture. The afternoon session was more practical with five exercises for 10–20 minutes each. The time flew by quickly. Actually, Martin conducts two versions of this workshop. First is a 1-day session and second is a 3-days sessions. After attending a 1-day session I clearly understand why the 3-days sessions exists, and what I would like to discuss deeper.
It was good that the workshop was in a quite flexible form, despite Martin had a particular agenda and presentation. He immediately answered all questions. As for me, the opportunity to discuss participant’s questions is a big advantage for workshops comparing with a conference. This is why I prefer to watch videos from the conference and attend workshops.
I would like to highlight Martin’s skill to present the material in the way when it is interesting for people with a small experience in the high-performance area, as well for those who are interested in this topic for a long time. First can discover new areas for learning, second can discuss different solutions for particular problems they faced.
Brief list of topics that were discussed:
Modern Hardware:
— CPU architecture — memory perspective;
— Inside the Processor — Pipelined Execution, ports;
— Memory ordering;
— The Cache System — L1/L2/L3-cahces, MOB, TLB, Pre-fetchers;
Main Memory:
— Memory controller;
— Memory Access Patterns;
Memory models:
— Hardware Memory Model;
— Language/Runtime Memory Models;
— Java Memory Model;
Queuing Theory;
 */