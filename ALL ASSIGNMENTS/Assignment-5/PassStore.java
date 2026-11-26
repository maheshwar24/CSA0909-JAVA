package TaskB1;
import java.io.*; import java.nio.file.*; import java.util.*;
public class PassStore { private final Path file; public PassStore(Path f){file=f;}
 private static String enc(String s){return s.replace("\\","\\\\").replace(",","\\c").replace("\n","\\n");}
 private static String[] dec(String s){List<String> a=new ArrayList<>();StringBuilder b=new StringBuilder();boolean esc=false;for(char c:s.toCharArray()){if(esc){b.append(c=='c'?',':c=='n'?'\n':c);esc=false;}else if(c=='\\')esc=true;else if(c==','){a.add(b.toString());b.setLength(0);}else b.append(c);}if(esc)b.append('\\');a.add(b.toString());return a.toArray(String[]::new);}
 public void add(String id,String reg,String name,int route,double amount)throws IOException{Files.createDirectories(file.toAbsolutePath().getParent());try(BufferedWriter w=Files.newBufferedWriter(file,StandardOpenOption.CREATE,StandardOpenOption.APPEND)){w.write(String.join(",",enc(id),enc(reg),enc(name),Integer.toString(route),Double.toString(amount)));w.newLine();}}
 public String[] find(String id)throws IOException{if(!Files.exists(file))return null;try(BufferedReader r=Files.newBufferedReader(file)){String line;while((line=r.readLine())!=null){String[] f=dec(line);if(f.length!=5)continue;if(f[0].equals(id))return f;}}return null;}
 public List<String[]> listByRoute(int route)throws IOException{List<String[]> out=new ArrayList<>();if(!Files.exists(file))return out;try(BufferedReader r=Files.newBufferedReader(file)){String line;while((line=r.readLine())!=null){String[] f=dec(line);if(f.length!=5)continue;try{if(Integer.parseInt(f[3])==route)out.add(f);}catch(NumberFormatException ignored){}}}return out;}
}
