import java.util.*;
public class Q6_HospitalTriage {
    static class Patient {
        final String name;final int severity;final long arrival;
        Patient(String n,int s,long a){if(s<1||s>5)throw new IllegalArgumentException("Severity must be 1..5");name=n;severity=s;arrival=a;}
        public String toString(){return name+"(S"+severity+")";}
    }
    private long counter=0;
    private final PriorityQueue<Patient> queue=new PriorityQueue<>(
        Comparator.comparingInt((Patient p)->p.severity).thenComparingLong(p->p.arrival));
    void admit(String name,int severity){queue.add(new Patient(name,severity,counter++));}
    Patient next(){return queue.poll();}
    public static void main(String[]args){
        Q6_HospitalTriage er=new Q6_HospitalTriage();
        er.admit("Ravi",3);er.admit("Meena",1);er.admit("John",3);er.admit("Asha",5);er.admit("Kumar",1);er.admit("Lata",2);
        Patient p;StringBuilder result=new StringBuilder();while((p=er.next())!=null)result.append(p).append(' ');
        System.out.println(result.toString().trim());
    }
}
