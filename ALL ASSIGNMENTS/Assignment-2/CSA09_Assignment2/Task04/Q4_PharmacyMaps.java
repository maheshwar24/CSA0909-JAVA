import java.util.*;
public class Q4_PharmacyMaps {
    static class Medicine {final String code,name;final int stock;Medicine(String c,String n,int s){code=c;name=n;stock=s;}public String toString(){return code+" "+name+" stock="+stock;}}
    static void test(String name,Map<String,Medicine> map,int n){
        for(int i=n-1;i>=0;i--){String c=String.format("MED%05d",i);map.put(c,new Medicine(c,"Medicine"+i,100+i));}
        List<String> keys=new ArrayList<>(map.keySet());
        long t=System.nanoTime();for(int i=0;i<100000;i++)map.get(keys.get(i%keys.size()));long lookup=System.nanoTime()-t;
        t=System.nanoTime();for(Medicine m:map.values()){if(m.stock<0)throw new AssertionError();}long iteration=System.nanoTime()-t;
        System.out.println(name+" n="+n+" lookups100k_ns="+lookup+" iteration_ns="+iteration);
        System.out.println("First iteration keys="+keys.subList(0,Math.min(5,keys.size())));
    }
    public static void main(String[]x){for(int n:new int[]{1000,10000,50000}){System.out.println("\\nInventory "+n);test("HashMap",new HashMap<>(),n);test("TreeMap",new TreeMap<>(),n);test("LinkedHashMap",new LinkedHashMap<>(),n);}}
}
