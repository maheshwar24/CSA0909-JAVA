package Task04;
import java.io.*;
public class Resident implements Serializable { private static final long serialVersionUID=1L; public String name,registerNo; public transient String password; public transient Object liveConnection; public String phoneNumber; public Resident(String n,String r,String p){name=n;registerNo=r;password=p;} }
