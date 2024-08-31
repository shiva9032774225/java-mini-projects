package jdbc;
import java.sql.*;
import java.util.*;


public class jdbc {
	 public static void main(String[] args) throws SQLException, ClassNotFoundException{
     Class.forName("com.mysql.cj.jdbc.Driver");
     String url="jdbc:mysql://localhost:3306/hotal";
     String pass="root";
     String user="root";
     //connection provide database details
     Connection c=DriverManager.getConnection(url,pass,user);
     Statement s=c.createStatement();
     Scanner sc=new Scanner(System.in);
  while(true) {
     System.out.println("HOTAL MANAGEMENT SYSTEM----------------?");
     System.out.println("1Reserve a room");
     System.out.println("2.view reservation");
     System.out.println("3.Get room number");
     System.out.println("4.update reservation");
     System.out.println("5.Delete reservation");
     System.out.println("0.Exit");
     System.out.println("PLEASE CHOOSE THE OPTION");
     int choise=sc.nextInt();
      if(choise<0||choise>6) {
    	 System.out.println("you have enterd invalid option please yenter valid option value");
    	int choise1=sc.nextInt();
    	choise=choise1;
     }
switch(choise) {
 case 1:
    	 String q="insert into reservation (reser_id,holder_name,room_num,contact_num) values(?,?,?,?);";
         PreparedStatement p=c.prepareStatement(q);
 		boolean a=true;
 		while(a) {
 		c.setAutoCommit(false);
 		System.out.println(" ENTER RESERVATION ID: ");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter HOLDER NAME: ");
		String name=sc.next();
		System.out.println("ENTER ROOM NUMBER: ");
		int rno=sc.nextInt();
		System.out.println("ENTER CONTACT NUMBER: ");
		String mobile=sc.next();
	    p.setInt(1,id);
		p.setString(2,name);
		p.setInt(3,rno);
		p.setString(4,mobile);
	
		p.executeUpdate();
	
		System.out.println("Record Inserted Successfully");
	
		System.out.println("Do you want to Insert another Record [yes/no] ?");
		String extra=sc.next();
		if(extra.equalsIgnoreCase("yes")) {
			a=true;
		}
		if(extra.equalsIgnoreCase("no")) {
			a=false;
			System.out.println(".....Thank You ^..^");
		
 		}
		c.commit();
 		}
 		break;
 		
case 2:
 		Statement st=c.createStatement();
 		String q1="select * from reservation;";
 		ResultSet r=s.executeQuery(q1);
 		System.out.println("---+---------+---------------+---------+");
 		while(r.next()) {
 System.out.println(r.getInt(1)+"| "+r.getString(2)+" | "+r.getString(3)+" | "+r.getString(4)+" | "+r.getString(5)+"|");
 System.out.println("---+--------+---------------+---------+");
 		}
 		
 		break;
case 3:
 		System.out.println("ENTER HOLDER NAME:");
 		String name1=sc.next();
 		sc.nextLine();
 		System.out.println("ENTER RESERVATION ID:");
 		int reser_id=sc.nextInt();
 		sc.nextLine();
 		String h="select * from reservation where holder_name=? and reser_id=?";

 		//String q2="select reser_id  where holder_name=?;";
 		PreparedStatement p1=c.prepareStatement(h);
 		p1.setString(1, name1);
 		p1.setInt(2,reser_id );
 		ResultSet r1=p1.executeQuery();
 		while(r1.next()) {
 		System.out.println("ROOM NUMBER IS: "+r1.getString("room_num"));}
 		break;
case 4:
     	boolean b=true;
     	while(b) {
 		System.out.println("WHICH DETAILS DO YOU WANT TO UPDATE ?");
 		System.out.println("1.HOLDER NAME");
 		System.out.println("2.ROOM  NUMBER");
 		System.out.println("3.CONTACT NAME"); 
 		System.out.println("CHOOSE YOUR OPTION:");
 		int opt2=sc.nextInt();
 		if(opt2<0||opt2>3) {
 			System.out.println("INVALID..!! PLEASE SELECT VALID OPTION");
 			int opt3=sc.nextInt();
 			opt2=opt3;
 		}
 		System.out.println("ENTER RESERVATION  ID: ");
 		 int reg=sc.nextInt();
 		switch(opt2) {
 		case 1:
 			System.out.println("ENTER NEW HOLDER NAME:");
 			String n=sc.next();
 			sc.nextLine();
 			String q3="update reservation set holder_name=? where reser_id=?;";
 			PreparedStatement p2=c.prepareStatement(q3);
 			p2.setString(1,n);
 			p2.setInt(2,reg);
 			p2.executeUpdate();
 			System.out.println("HOLDER UPDATED SUCCESSFULLY");
 			break;
 		case 2:
 			System.out.println("ENTER NEW ROOM NUMBER:");
 			String roll=sc.next();
 			String q4="update reservation set room_num=? where reser_id=?;";
 			PreparedStatement p3=c.prepareStatement(q4);
 			p3.setString(1,roll);
 			p3.setInt(2,reg);
 			p3.executeUpdate();
 			System.out.println("NEW ROOM NUMBER IS UPDATED SUCCESSFULLY");
 			break;
 		case 3:
 			System.out.println("ENTER NEW CONTACT NUMBER:");
 			String num=sc.next();
 			String q5="update reservation set contact_num=? where reser_id=?;";
 			PreparedStatement p4=c.prepareStatement(q5);
 			p4.setString(1,num);
 			p4.setInt(2,reg);
 			p4.executeUpdate();
 			System.out.println("CONTACT NUMBER IS UPDATED SUCCESSFULLY");
 			break;	
 		}
 		System.out.println("Do you want to update anything [yes/no] ?");
 		String choice1=sc.next();
 		if(choice1.equalsIgnoreCase("yes")) {
 			b=true;
 		}
 		if(choice1.equalsIgnoreCase("no")) {
 			b=false;
 			System.out.println(".....Thank You ^..^");
 			}
 		}
     	break;
case 5:
     	boolean d=true;
     	while(d) {
     	System.out.println("ENTER THE reserv id THAT YOU WANT TO DELETE: ");
     	int std=sc.nextInt();
     	sc.nextLine();
     	String q6="delete from reservation where reser_id=?;";
     	PreparedStatement p4=c.prepareStatement(q6);
     	p4.setInt(1, std);
     	p4.executeUpdate();
     	System.out.println("DELETED SUCCESSFULLY");
     	System.out.println("Do you want to delete another record [yes/no] ?");
 		String choice2=sc.next();
 		if(choice2.equalsIgnoreCase("yes")) {
 			d=true;
 		}
 		if(choice2.equalsIgnoreCase("no")) {
 			d=false;
 			System.out.println(".....Thank You ^..^");
 			}
 		}
     	break;

     case 0:
     	System.out.println("......EXITING ^._.^");
     	return;   
     
 		}
	  }
    }
}
