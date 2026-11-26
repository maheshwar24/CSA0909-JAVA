import java.util.*;
public class Q7_RedesignBenchmark {
    static long original(int n){List<Integer> list=new ArrayList<>();for(int i=0;i<n;i++)list.add(i);long t=System.nanoTime();while(!list.isEmpty())list.remove(0);return System.nanoTime()-t;}
    static long redesigned(int n){Deque<Integer> q=new ArrayDeque<>();for(int i=0;i<n;i++)q.addLast(i);long t=System.nanoTime();while(!q.isEmpty())q.removeFirst();return System.nanoTime()-t;}
    static long median(long[]a){Arrays.sort(a);return a[a.length/2];}
    public static void main(String[]args){
        System.out.println("size,ArrayList_front_remove_ns,ArrayDeque_front_remove_ns");
        for(int n:new int[]{5000,10000,20000}){long[]a=new long[5],d=new long[5];for(int i=0;i<5;i++){a[i]=original(n);d[i]=redesigned(n);}System.out.println(n+","+median(a)+","+median(d));}
        System.out.println("Trade-off: ArrayDeque is efficient at both ends but does not support indexed get(k).");
    }
}
