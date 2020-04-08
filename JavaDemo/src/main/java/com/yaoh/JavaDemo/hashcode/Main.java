package com.yaoh.JavaDemo.hashcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * @author yaoh
 * @date 2020-03-28 15:46
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        HashMap<Student, String> map = new HashMap<>();

        Student student1 = new Student();
        Student student2 = new Student();
        System.out.println("student1=======> " + student1.hashCode());
        System.out.println("student2=======> " + student2.hashCode());

        map.put(student1, "========student1======");
        map.put(student2, "========student2======");

        System.out.println("student1=======> " + map.get(student1));
        System.out.println("student2=======> " + map.get(student2));

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Student student = (Student) entry.getKey();
            System.out.println("student========> " + student
                    + " hashcode: " + student.hashCode()
                    + " identityHashCode： " + System.identityHashCode(student));
        }

        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        System.out.println(" stack111=========> " + stack);
//        stack.pop();
        int result = stack.search("1");

        System.out.println(" stack222=========> " + stack + " result: " + result);


    }


}
