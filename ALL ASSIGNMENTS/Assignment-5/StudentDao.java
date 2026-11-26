package Task03;
import java.sql.*; import java.util.*;
public class StudentDao implements Dao<Student,Integer> {
 private final Connection con; public StudentDao(Connection con){this.con=con;}
 public void insert(Student s)throws SQLException{try(PreparedStatement p=con.prepareStatement("INSERT INTO student(id,name,email) VALUES(?,?,?)")){p.setInt(1,s.id());p.setString(2,s.name());p.setString(3,s.email());p.executeUpdate();}}
 public Optional<Student> findById(Integer id)throws SQLException{try(PreparedStatement p=con.prepareStatement("SELECT id,name,email FROM student WHERE id=?")){p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?Optional.of(new Student(r.getInt(1),r.getString(2),r.getString(3))):Optional.empty();}}}
 public List<Student> findAll()throws SQLException{List<Student> out=new ArrayList<>();try(Statement s=con.createStatement();ResultSet r=s.executeQuery("SELECT id,name,email FROM student")){while(r.next())out.add(new Student(r.getInt(1),r.getString(2),r.getString(3)));}return out;}
 public void update(Student s)throws SQLException{try(PreparedStatement p=con.prepareStatement("UPDATE student SET name=?,email=? WHERE id=?")){p.setString(1,s.name());p.setString(2,s.email());p.setInt(3,s.id());p.executeUpdate();}}
 public void delete(Integer id)throws SQLException{try(PreparedStatement p=con.prepareStatement("DELETE FROM student WHERE id=?")){p.setInt(1,id);p.executeUpdate();}}
}
