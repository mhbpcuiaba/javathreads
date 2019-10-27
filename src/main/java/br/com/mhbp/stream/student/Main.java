package br.com.mhbp.stream.student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Student s1 = new Student(
            "Marcos",
            20,
            new Address("1234"),
            Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

        Student s2 = new Student(
            "Geovanne",
            20,
            new Address("1235"),
            Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

        Student s3 = new Student(
            "Alex",
            20,
            new Address("1236"),
            Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

        List<Student> students = Arrays.asList(s1, s2, s3);


        Optional<Student> alex = students.stream()
                .filter(s -> s.getName().equals("Alex"))
                .findFirst();

        System.out.println(alex.isPresent() ? alex.get().getName() : "No student found");

        Optional<Student> studentWithZipCode1235 = students.stream()
                .filter(s -> s.getAddress().getZipcode().equals("1235"))
                .findFirst();

        System.out.println(studentWithZipCode1235.isPresent() ? studentWithZipCode1235.get().getName() : "No student found");

        List<Student> studentsWithMobile333 = students.stream()
                .filter(
                        s -> s.getMobileNumbers().stream().anyMatch(x -> Objects.equals(x.getNumber(), "333"))
                )
                .collect(Collectors.toList());

        List<String> studentsNameThathaveMobile333= studentsWithMobile333.stream().map(s -> s.getName()).collect(Collectors.toList());

        System.out.println(studentsNameThathaveMobile333);


        TempStudent tmpStud1 = new TempStudent(
                "Junior",
                201,
                new Address("12341"),
                Arrays.asList(new MobileNumber("12331"), new MobileNumber("12341")));

        TempStudent tmpStud2 = new TempStudent(
                "Fabio",
                202,
                new Address("12351"),
                Arrays.asList(new MobileNumber("11111"), new MobileNumber("33331"), new MobileNumber("12331")));

        List<TempStudent> tmpStudents = Arrays.asList(tmpStud1, tmpStud2);

        List<Student> studentList = tmpStudents.stream()
                .map(tmpStud -> new Student(tmpStud.name, tmpStud.age, tmpStud.address, tmpStud.mobileNumbers))
                .collect(Collectors.toList());

        System.out.println(studentList);

        List<String> studentsName = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println(studentsName.stream().collect(Collectors.joining(",")));
        System.out.println(studentsName.stream().collect(Collectors.joining(",", "[", "]")));

        Stream<Student> sorted = students.stream().sorted(Comparator.comparing(Student::getName));
        System.out.println(sorted);
    }
}
