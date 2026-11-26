package Task04;
import java.io.*; import java.util.*;
public class Hostel implements Serializable { private static final long serialVersionUID=1L; public List<Resident> residents=new ArrayList<>(); public static void save(Hostel h,File f)throws IOException{try(ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream(f))){o.writeObject(h);}} public static Hostel load(File f)throws IOException,ClassNotFoundException{try(ObjectInputStream i=new ObjectInputStream(new FileInputStream(f))){return (Hostel)i.readObject();}} }
