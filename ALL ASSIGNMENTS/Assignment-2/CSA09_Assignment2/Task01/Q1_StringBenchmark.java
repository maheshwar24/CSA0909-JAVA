import java.util.*;
public class Q1_StringBenchmark {
    static volatile int sink;
    static String plus(String[] f) { String s=""; for(String x:f) s+=x; return s; }
    static String builder(String[] f) { StringBuilder s=new StringBuilder(); for(String x:f)s.append(x); return s.toString(); }
    static String buffer(String[] f) { StringBuffer s=new StringBuffer(); for(String x:f)s.append(x); return s.toString(); }
    static String[] data(int n,int len) { String[] a=new String[n]; for(int i=0;i<n;i++)a[i]="field"+i+"-"+"x".repeat(len); return a; }
    static long bench(java.util.function.Function<String[],String> fn,String[] a,int reps) {
        for(int i=0;i<200;i++) sink+=fn.apply(a).length();
        long t=System.nanoTime(); for(int i=0;i<reps;i++)sink+=fn.apply(a).length();
        return (System.nanoTime()-t)/reps;
    }
    public static void main(String[] args) {
        int[][] workloads={{5,8},{50,20},{200,50}};
        System.out.println("fields,method,average_ns_per_ticket");
        for(int[] w:workloads) {
            String[] a=data(w[0],w[1]); int reps=1000;
            System.out.println(w[0]+",String+, "+bench(Q1_StringBenchmark::plus,a,reps));
            System.out.println(w[0]+",StringBuilder,"+bench(Q1_StringBenchmark::builder,a,reps));
            System.out.println(w[0]+",StringBuffer,"+bench(Q1_StringBenchmark::buffer,a,reps));
        }
        System.out.println("Repeat runs on your computer; timings depend on JVM and hardware.");
    }
}
