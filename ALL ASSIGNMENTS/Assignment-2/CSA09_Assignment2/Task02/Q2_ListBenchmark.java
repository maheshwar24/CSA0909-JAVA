import java.util.*;
public class Q2_ListBenchmark {
    static volatile long sink;
    static long time(Runnable r){long t=System.nanoTime();r.run();return System.nanoTime()-t;}
    static void test(int n){
        System.out.println("\\nSIZE="+n);
        System.out.println("ArrayList append ns="+time(()->{List<Integer> l=new ArrayList<>();for(int i=0;i<n;i++)l.add(i);sink+=l.size();}));
        System.out.println("LinkedList append ns="+time(()->{List<Integer> l=new LinkedList<>();for(int i=0;i<n;i++)l.add(i);sink+=l.size();}));
        List<Integer> a=new ArrayList<>(), b=new LinkedList<>();
        for(int i=0;i<n;i++){a.add(i);b.add(i);}
        System.out.println("ArrayList get-middle x1000 ns="+time(()->{for(int i=0;i<1000;i++)sink+=a.get(n/2);}));
        System.out.println("LinkedList get-middle x1000 ns="+time(()->{for(int i=0;i<1000;i++)sink+=b.get(n/2);}));
        System.out.println("ArrayList front add/remove x1000 ns="+time(()->{for(int i=0;i<1000;i++){a.add(0,-1);a.remove(0);}}));
        System.out.println("LinkedList front add/remove x1000 ns="+time(()->{for(int i=0;i<1000;i++){b.add(0,-1);b.remove(0);}}));
        System.out.println("ArrayDeque both-end operations ns="+time(()->{Deque<Integer>d=new ArrayDeque<>();for(int i=0;i<n;i++)d.addLast(i);for(int i=0;i<1000;i++){d.addFirst(-1);d.removeFirst();}}));
    }
    public static void main(String[]x){for(int n:new int[]{1000,5000,10000})test(n);}
}
