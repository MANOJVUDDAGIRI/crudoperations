package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class crudoperations {
	
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	
	private static final String Username = "root";
	
	private static final String Password = "Manoj@123";
	
	private static Connection conn;
	
	private static PreparedStatement pmst;

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int ch;
		
		do {
		
			display();
			
			System.out.println("enter your choice");
			
			ch = Integer.parseInt(sc.nextLine());
			
			switch (ch) {
			case 1:
			createdatabase();
				break;
			case 2:
				creatingtable();
				break;
			case 3:
				datainsertion();
				break;
			case 4:
				datadeletion();
				break;
			case 5:
				getall();
				break;
			case 6:
				getbyid();
				break;
			case 7:
				droptable();
				break;
			case 8:
				dropdatabase();
				break;
			case 9:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice");
			}
		} while (ch > 0);
	}

	private static void dropdatabase() { //database deletion
		
		String url = "jdbc:mysql://localhost:3306/";
		
		Scanner sc = new Scanner(System.in);
		
		try {
		
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(url, Username, Password);
			
			System.out.println("Enter database name :");
			
			String sql = "drop database " + sc.next();
			
			pmst = conn.prepareStatement(sql);
			
			int i = pmst.executeUpdate();
			
			if (i == 0) {
			
				System.out.println("database deleted");
			
			} else {
			
				System.out.println("error");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void droptable() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter database name");
		
		String Url = "jdbc:mysql://localhost:3306/"+sc.next();
		
		try {
		
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(Url, Username, Password);
			
			System.out.println("enter table name");
			
			String sql = "drop table " + sc.next();
			
			pmst = conn.prepareStatement(sql);
			
			int i = pmst.executeUpdate();
			
			if (i == 0) {
			
				System.out.println("created");
			
			} else {
			
				System.out.println("error");
			
			}
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}

	}

	private static void getbyid() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter database name");
		
		String Url = "jdbc:mysql://localhost:3306/"+sc.next();
		
		try {
		
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(Url, Username, Password);
			
			String sql = "select * from emp where id = ?";
			
			pmst = conn.prepareStatement(sql);
			
			System.out.println("enter employee id");
			
			pmst.setInt(1, sc.nextInt());
			
			ResultSet rs = pmst.executeQuery();
			
			while (rs.next()) {
			
				System.out.println(
				
						"id" + rs.getInt("id") + "name" + rs.getString("name") + "email" + rs.getString("email"));
			
			}
		
		} catch (Exception e) {
	
			e.printStackTrace();
		
		}
	
	}

	private static void getall() { //fetching data
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter database name");
		
		String Url = "jdbc:mysql://localhost:3306/"+sc.next();
		
		try {
		
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(Url, Username, Password);
			
			System.out.println("enter table name");
			
			String sql = "select * from " + sc.next();
			
			pmst = conn.prepareStatement(sql);
			
			ResultSet rs = pmst.executeQuery();
			
			while (rs.next()) {
			
				System.out.println("id" + rs.getInt("id") + "name" + rs.getString("name") + "email" + rs.getString("email"));
			
			}
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
	
	}
	
	private static void datadeletion() {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter database name");
		
		String Url = "jdbc:mysql://localhost:3306/"+sc.next();
		
		try {
		
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(Url, Username, Password);
			
			System.out.println("enter table name :");
			
			String sql = "delete from "+sc.next()+" where id = ?";
			
			pmst = conn.prepareStatement(sql);
			
			System.out.println("enter employee id");
			
			pmst.setInt(1, sc.nextInt());
			
			int i = pmst.executeUpdate();
			
			if (i > 0) {
			
				System.out.println("deleted");
			
			} else {
			
				System.out.println("error");
			
			}
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
	
	}

	private static void datainsertion() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name");
		String Url = "jdbc:mysql://localhost:3306/"+sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("enter table name :");
			String sql = "insert into "+sc.next()+"(id,name,email) values(?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter employee id");
			pmst.setInt(1, sc.nextInt());
			System.out.println("enter employee name");
			pmst.setString(2, sc.next());
			System.out.println("enter employee email");
			pmst.setString(3, sc.next());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Inserted");
			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private static void creatingtable() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name");
		String Url = "jdbc:mysql://localhost:3306/"+sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("enter table name");
			String sql = "create table " + sc.next() + "(id int,name varchar(90),email varchar(90))";
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("created");
			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void createdatabase() {
		String url = "jdbc:mysql://localhost:3306/";
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter database name :");
			String sql = "create database " + sc.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("database created");
			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void display() {
		System.out.println("operations");
		System.out.println("\t1 .creating database");
		System.out.println("\t2 .creating table");
		System.out.println("\t3 .data insertion");
		System.out.println("\t4 .data deletion");
		System.out.println("\t5 .fetch all data");
		System.out.println("\t6 .get data by id");
		System.out.println("\t7 .droping table");
		System.out.println("\t8 .droping database");
		System.out.println("\t9 .terminate process");
	}
}
