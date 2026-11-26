package Task02;
import java.io.*;
import java.nio.file.*;
public class LineCounter {
  public static long unbuffered(Path p) throws IOException { long n=0; try(InputStream in=Files.newInputStream(p)){ int b; while((b=in.read())!=-1) if(b=='\\n') n++; } return n; }
  public static long buffered(Path p) throws IOException { long n=0; try(BufferedInputStream in=new BufferedInputStream(Files.newInputStream(p))){ byte[] buf=new byte[8192]; int len; while((len=in.read(buf))!=-1) for(int i=0;i<len;i++) if(buf[i]=='\\n') n++; } return n; }
  public static void main(String[] a) throws Exception { Path p=Path.of(a.length==0?"attendance.log":a[0]); long t=System.nanoTime(), x=unbuffered(p); long u=System.nanoTime()-t; t=System.nanoTime(); long y=buffered(p); long b=System.nanoTime()-t; System.out.printf("Unbuffered=%d lines, %.3f ms%nBuffered=%d lines, %.3f ms%n",x,u/1e6,y,b/1e6); }
}
