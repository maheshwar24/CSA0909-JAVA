package TaskB1;
import java.nio.file.*; import java.util.*;
public class FileStoreDemo { public static void main(String[] x)throws Exception{Path p=Path.of("passes.csv");Files.deleteIfExists(p);PassStore s=new PassStore(p);s.add("P1001","192311001","Arun",4,3500);s.add("P1003","192311003","Divya",7,3800);Files.writeString(p,"P1002,DAMAGED LINE\n",StandardOpenOption.APPEND);System.out.println(Arrays.toString(s.find("P1003")));System.out.println(s.find("P9999"));System.out.println("Route 7 count="+s.listByRoute(7).size());}}
