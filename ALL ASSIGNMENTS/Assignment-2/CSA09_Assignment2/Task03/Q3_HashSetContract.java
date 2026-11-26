import java.util.*;
public class Q3_HashSetContract {
    static class Student {
        final String regNo,name;
        Student(String r,String n){regNo=r;name=n;}
        public boolean equals(Object o){if(this==o)return true;if(!(o instanceof Student))return false;return regNo.equals(((Student)o).regNo);}
        // TODO 1 completed: hash code uses the same identity field as equals.
        public int hashCode(){return Objects.hash(regNo);}
        public String toString(){return regNo+" "+name;}
    }
    static class BrokenStudent {
        String regNo,name;
        BrokenStudent(String r,String n){regNo=r;name=n;}
        public boolean equals(Object o){return o instanceof BrokenStudent && regNo.equals(((BrokenStudent)o).regNo);}
        // Deliberately missing hashCode to demonstrate the broken contract.
    }
    public static void main(String[] args){
        Set<Student> good=new HashSet<>();
        good.add(new Student("192311001","Arun"));good.add(new Student("192311002","Divya"));good.add(new Student("192311001","Arun"));
        System.out.println("Corrected unique students: "+good.size());
        Set<BrokenStudent> bad=new HashSet<>();
        bad.add(new BrokenStudent("192311001","Arun"));bad.add(new BrokenStudent("192311001","Arun"));
        System.out.println("Broken implementation size (may show duplicate): "+bad.size());
        Set<Student> own=new HashSet<>();own.add(new Student("REG10","A"));own.add(new Student("REG10","Different name"));
        System.out.println("Same registration number size: "+own.size());
    }
}
