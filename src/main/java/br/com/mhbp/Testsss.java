package br.com.mhbp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testsss {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
    public static void main3(String[] args) {
        String teste = "/Beneficiários/R/Renata Maria Da Silva/documentação/chamados/";
        if(!teste.endsWith("/")){
            teste = teste.concat("/");
        }

        String  p = "200";
        List<String> subs = new ArrayList<>();
        Matcher m = Pattern.compile("[/]").matcher(teste);

        System.out.println(p);
        while (m.find()){
            String sub = teste.substring(0, m.start()) + "/";
            subs.add(sub);
        }

        System.out.println(subs);
    }

}
