package br.com.mhbp.threads.training.threadlocal;

public class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class Service1 {

    public void process() {
        User user = getUser();
        UserContextHolder.holder.set(user);
    }

    private User getUser() {
        return new User();
    }
}

class Service2 {

    public void process() {
        User user = UserContextHolder.holder.get();
        UserContextHolder.holder.remove();
    }
}

class  User {
    public String name;
    public int age;
}

/*

Spring framework uses lot of context holders


    LocaleContextHolder
    TransactionContextHolder
    RequestContextHolder
    SecurityContextHolder
    DateTimeContextHolder
 */