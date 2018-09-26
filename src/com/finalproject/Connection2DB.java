package com.finalproject;


import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.mysql.jdbc.PreparedStatement;


public class Connection2DB { 
	
	private final String driver = "com.mysql.jdbc.Driver";  	
	private final String protocol = "jdbc:mysql://localhost:3306/abcd"; 	
	private final String USER = "root"; 	
	private final String PASS = "admin"; 
	private Connection connection = null; 
	private Statement statement = null; 
	
	private static Connection2DB DBConnectors ;

	public static Connection2DB Instance() throws SQLException
	{
		if (DBConnectors == null)
		{
			return new Connection2DB();
		}
		else return DBConnectors;
	}
		


	private Connection2DB() throws SQLException
	 {
		 try
			{ 
				Class.forName(driver);//loading the driver to memory 
				connection=DriverManager.getConnection(protocol,USER,PASS);
				connection.setAutoCommit(false);
				tableCreation();//method for creating the tables in DB
				enterData();//method for entering data to DB
			} 
			catch(Exception e)
			{
				//e.printStackTrace(); 
				connection.rollback();
			}
			finally 	
			{ 	
				if(statement!=null) try{statement.close();}catch(Exception e){/*e.printStackTrace();*/} 
				if(connection!=null) try{connection.close();}catch(Exception e){/*e.printStackTrace();*/} 		
	 		} 
	 }
 
	public void tableCreation() throws SQLException
	{
		statement = connection.createStatement();
		/*creation of the tables*/
		statement.execute("create table IF NOT EXISTS Class(ClassNumber int not null,BuildingNumber int not null,Floor int not null,PRIMARY KEY (ClassNumber))"); 		
		statement.execute("create table IF NOT EXISTS Course(CourseNumber int not null,Name varchar(255) not null, Semester CHAR not null,HourseAmount int not null,Year int not null,Day int not null,Time_Hour int not null,Time_Minute int not null,PRIMARY KEY (CourseNumber))");
		statement.execute("create table IF NOT EXISTS Lecture(ID int not null,Name_FirstName varchar(255) not null,Name_LastName varchar(255) not null,Birthday DATE not null, Address_City varchar(255) not null,Address_street_Number int not null,Address_Name varchar(255) not null,PRIMARY KEY (ID))"); 		
		statement.execute("create table IF NOT EXISTS Takeplace(Course_CourseNumber int, Class_ClassNumber int,PRIMARY KEY (Course_CourseNumber,Class_ClassNumber))");
		statement.execute("create table IF NOT EXISTS Teaching(Course_CourseNumber int , Lecture_ID int,PRIMARY KEY (Course_CourseNumber,Lecture_ID));"); 
		statement.execute("create table IF NOT EXISTS LecturePhone(ID int not null,PhoneNumber int ,PRIMARY KEY (PhoneNumber),FOREIGN KEY(ID) references Lecture(ID) ON DELETE CASCADE ON UPDATE CASCADE)"); 
	}
	
	public void enterData() throws SQLException, ParseException
	{
		statement = connection.createStatement();
		/*INSERT into class table*/
		statement.executeUpdate("INSERT INTO class VALUES (200,3,2)");
		statement.executeUpdate("INSERT INTO class VALUES (201,3,2)");
		statement.executeUpdate("INSERT INTO class VALUES (202,2,2)");
		statement.executeUpdate("INSERT INTO class VALUES (203,3,2)");
		statement.executeUpdate("INSERT INTO class VALUES (204,3,2)");
		statement.executeUpdate("INSERT INTO class VALUES (300,2,3)");
		statement.executeUpdate("INSERT INTO class VALUES (301,2,3)");
		statement.executeUpdate("INSERT INTO class VALUES (302,2,3)");
		statement.executeUpdate("INSERT INTO class VALUES (303,2,3)");
		statement.executeUpdate("INSERT INTO class VALUES (304,2,3)");
		connection.commit();
		/*INSERT into Course table*/
		statement.executeUpdate("INSERT INTO course VALUES(5000,'courseA','A',5,2,1,18,00)");
		statement.executeUpdate("INSERT INTO course VALUES(5001,'courseB','A',2,3,1,12,00)"); 		
		statement.executeUpdate("INSERT INTO course VALUES(5002,'courseB','A',3,1,1,18,30)"); 		
		statement.executeUpdate("INSERT INTO course VALUES(5003,'courseC','B',4,1,2,10,30)"); 		
		statement.executeUpdate("INSERT INTO course VALUES(5004,'courseD','B',5,1,2,21,00)"); 		
		statement.executeUpdate("INSERT INTO course VALUES(5006,'courseE','B',6,2,3,13,45)"); 		
		statement.executeUpdate("INSERT INTO course VALUES(5007,'courseF','B',2,3,3,12,30)"); 		
		statement.executeUpdate("INSERT INTO course VALUES(5008,'courseG','S',2,4,3,10,00)"); 		
		statement.executeUpdate("INSERT INTO course VALUES(5009,'courseH','S',2,4,4,15,30)"); 		
		statement.executeUpdate("INSERT INTO course VALUES(5010,'courseI','S',3,2,5,12,00)"); 
		connection.commit();
		/*INSERT into Lecture table*/		
		SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  
		java.util.Date myDate = format.parse( "10/10/2009" ); 
		PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement("INSERT INTO Lecture(ID,Name_FirstName,Name_LastName,Birthday, Address_City,Address_street_Number,Address_Name) VALUES (?,?,?,?,?,?,?);");
		pstmt.setInt( 1, 305444440 );
		pstmt.setString( 2, "Shalom"); 
		pstmt.setString( 3, "Shloshe" ); 
		java.sql.Date sqlDate = new java.sql.Date( myDate.getTime() ); 
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Tel-Aviv");
		pstmt.setInt(6, 34);
		pstmt.setString(7, "Mozkin");
		pstmt.executeUpdate();
		///////////////////////////////////////////////
		pstmt.setInt( 1, 305444441 );
		pstmt.setString( 2, "Shalom1"); 
		pstmt.setString( 3, "Shloshe1" ); 
		myDate = format.parse( "08/20/2012" ); 
		sqlDate = new java.sql.Date( myDate.getTime() );	
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Tel-Aviv1");
		pstmt.setInt(6, 341);
		pstmt.setString(7, "Mozkin1");
		pstmt.executeUpdate();
		/////////////////////////////////////////////////
		pstmt.setInt( 1, 305444442 );
		pstmt.setString( 2, "Naor"); 
		pstmt.setString( 3, "Yoni" ); 
		myDate = format.parse( "03/12/1991" ); 
		sqlDate = new java.sql.Date( myDate.getTime() ); 
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Bear-Sheva");
		pstmt.setInt(6, 1);
		pstmt.setString(7, "Bavli");
		pstmt.executeUpdate();
		//////////////////////////////////////
		pstmt.setInt( 1, 305444443 );
		pstmt.setString( 2, "Shalomi"); 
		pstmt.setString( 3, "Sheldon" ); 
		myDate = format.parse( "11/30/1987" ); 
		sqlDate = new java.sql.Date( myDate.getTime());
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Tel-Aviv");
		pstmt.setInt(6, 23);
		pstmt.setString(7, "Lamed");
		pstmt.executeUpdate();
		////////////////////////////
		pstmt.setInt( 1, 305444444 );
		pstmt.setString( 2, "Uri"); 
		pstmt.setString( 3, "Dudi" ); 
		myDate = format.parse( "06/02/1995" ); 
		sqlDate = new java.sql.Date( myDate.getTime() ); 
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Tel-Aviv");
		pstmt.setInt(6, 50);
		pstmt.setString(7, "Ramat Aviv");
		pstmt.executeUpdate();
		////////////////////////////
		pstmt.setInt( 1, 305444445 );
		pstmt.setString( 2, "Hamoiv"); 
		pstmt.setString( 3, "Dudidi" ); 
		myDate = format.parse( "01/05/1993" ); 
		sqlDate = new java.sql.Date( myDate.getTime() );
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Ashdod");
		pstmt.setInt(6, 5);
		pstmt.setString(7, "Ramat Aviv G");
		pstmt.executeUpdate();	
		////////////////////////////
		pstmt.setInt( 1, 305444446 );
		pstmt.setString( 2, "Cris"); 
		pstmt.setString( 3, "Nedved" ); 
		myDate = format.parse( "02/08/1970" ); 
		sqlDate = new java.sql.Date( myDate.getTime() );
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Ashdod");
		pstmt.setInt(6, 30);
		pstmt.setString(7, "New Ramat Aviv");
		pstmt.executeUpdate();	
		////////////////////////////
		pstmt.setInt( 1, 305444447 );
		pstmt.setString( 2, "David"); 
		pstmt.setString( 3, "Del-Piero" ); 
		myDate = format.parse( "10/10/1990" ); 
		sqlDate = new java.sql.Date( myDate.getTime() );
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Ashdod");
		pstmt.setInt(6, 30);
		pstmt.setString(7, "Shalom");
		pstmt.executeUpdate();	
		////////////////////////////
		pstmt.setInt( 1, 305444448 );
		pstmt.setString( 2, "Aviv"); 
		pstmt.setString( 3, "Davids" ); 
		sqlDate = new java.sql.Date( myDate.getTime() );
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Ofakim7");
		pstmt.setInt(6, 18);
		pstmt.setString(7, "Arlozrov");
		pstmt.executeUpdate();
		////////////////////////////
		pstmt.setInt( 1, 305444449 );
		pstmt.setString( 2, "Dekel"); 
		pstmt.setString( 3, "Cafu" ); 
		sqlDate = new java.sql.Date( myDate.getTime() );
		pstmt.setDate( 4, sqlDate ); 
		pstmt.setString(5, "Ranana");
		pstmt.setInt(6, 18);
		pstmt.setString(7, "Ana Frank");
		pstmt.executeUpdate();
		/*INSERT into TakePlace table*/
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5000,200)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5001,201)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5002,202)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5003,203)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5004,204)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5005,300)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5006,301)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5007,302)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5008,303)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5009,304)"); 
		statement.executeUpdate("INSERT INTO Takeplace VALUES(5010,200)"); 
		connection.commit();
		/*INSERT into LecturePhone table*/
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444440,305468050)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444440,0526336132)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444441,0543334557)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444441,675849387)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444442,584339485)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444443,575774839)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444443,347544326)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444444,234775829)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444445,247839562)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444445,238998743)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444446,429182736)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444447,547889234)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444448,723885958)");
		statement.executeUpdate("INSERT INTO LecturePhone VALUES(305444449,345776543)");
		connection.commit();
		/*INSERT into Teaching table*/
		statement.executeUpdate("INSERT INTO Teaching VALUES(5000,305444440)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5001,305444441)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5002,305444442)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5003,305444443)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5004,305444444)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5005,305444445)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5006,305444446)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5007,305444447)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5008,305444448)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5009,305444449)"); 
		statement.executeUpdate("INSERT INTO Teaching VALUES(5010,305444449)"); 
		connection.commit();	
	}	
	
	public void Exectuequery(String Query) throws SQLException
	{
		try{
			connection=DriverManager.getConnection(protocol,USER,PASS);
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.executeUpdate(Query);
			connection.commit();
		}
		finally 	
		{ 
			if(statement!=null) try{statement.close();}catch(Exception e){e.printStackTrace();} 
			if(connection!=null) try{connection.close();}catch(Exception e){e.printStackTrace();} 	
		} 
	}
	
	 public Connection getConnection() throws SQLException {
		connection=DriverManager.getConnection(protocol,USER,PASS);
		return connection;
	}
	
	public ArrayList<Classes> getClasses(String query) throws SQLException
	{
		try{
			if (query.equals(""))query = "SELECT * from class;";
			connection=DriverManager.getConnection(protocol,USER,PASS);
			connection.setAutoCommit(false);
		    statement = connection.createStatement();
		    ResultSet rs = statement.executeQuery(query);
		    ArrayList<Classes> cls = new ArrayList<Classes>();
		    while(rs.next())
		    {
		    	cls.add(new Classes(rs.getInt("ClassNumber"),rs.getInt("BuildingNumber"),rs.getInt("Floor")));
		    }
		    connection.close();
		    return cls;
		}
		finally 	
		{ 
			if(statement!=null) try{statement.close();}catch(Exception e){e.printStackTrace();} 
			if(connection!=null) try{connection.close();}catch(Exception e){e.printStackTrace();} 	
		} 
	}
	
	public ArrayList<Lecture> getLecture(String query) throws SQLException 	
	{
		try{
			if (query.equals(""))query = "SELECT * from lecture;";
			connection=DriverManager.getConnection(protocol,USER,PASS);
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			ArrayList<Lecture> crs = new ArrayList<Lecture>();
			while(rs.next())
		    {
				crs.add(new Lecture(rs.getInt("ID"),rs.getString("Name_FirstName"),rs.getString("Name_LastName"),rs.getDate("Birthday"),rs.getString("Address_City"),rs.getInt("Address_street_Number"),rs.getString("Address_Name"))); 	    	
		    }
		    connection.close();
		    return crs;
		}
		finally 	
		{ 
			if(statement!=null) try{statement.close();}catch(Exception e){e.printStackTrace();} 
			if(connection!=null) try{connection.close();}catch(Exception e){e.printStackTrace();} 	
		} 
	}
	
	public ArrayList<Course> getCourse(String query) throws SQLException 	
	{
		try{
			if (query.equals(""))query = "SELECT * from course;";
			connection=DriverManager.getConnection(protocol,USER,PASS);
			connection.setAutoCommit(false);
		    statement = connection.createStatement();
		    ResultSet rs = statement.executeQuery(query);
		    ArrayList<Course> crs = new ArrayList<Course>();
		    while(rs.next())
		    {
		    	crs.add(new Course(rs.getInt("CourseNumber"),rs.getString("Name"),rs.getString("Semester"),rs.getInt("HourseAmount"),rs.getInt("Year"),rs.getInt("Day"),rs.getInt("Time_Hour"),rs.getInt("Time_Minute"))); 	
		    }
		    connection.close();
		    return crs; 
		}
		finally 	
		{ 
			if(statement!=null) try{statement.close();}catch(Exception e){e.printStackTrace();} 
			if(connection!=null) try{connection.close();}catch(Exception e){e.printStackTrace();} 	
		} 
	}
	
	/*Answer to query about full schedule*/
	public ArrayList<FullScheduleQuery> getSchedule() throws SQLException 	
	{
		try{
			connection=DriverManager.getConnection(protocol,USER,PASS);
			connection.setAutoCommit(false);
		    statement = connection.createStatement();
		    String sql = "SELECT * FROM course left JOIN takeplace ON CourseNumber=takeplace.Course_CourseNumber left JOIN class ON ClassNumber=takeplace.Class_ClassNumber left JOIN teaching ON CourseNumber=teaching.Course_CourseNumber left JOIN lecture ON ID=teaching.Lecture_ID ORDER BY course.CourseNumber;";
		    ResultSet rs = statement.executeQuery(sql);
		    ArrayList<FullScheduleQuery> sch = new ArrayList<FullScheduleQuery>();
		    while(rs.next())
		    {   	
		    	FullScheduleQuery schedule = new FullScheduleQuery();
				//course information 
		    	schedule.setCourseNumber(rs.getInt("CourseNumber"));
		    	schedule.setName(rs.getString("Name"));
		    	schedule.setSemester(rs.getString("Semester"));
		    	schedule.setHoursAmount(rs.getInt("HourseAmount"));
		    	schedule.setYear(rs.getInt("Year"));
		    	schedule.setDay(rs.getInt("Day"));
		    	schedule.setTime_Hour(rs.getInt("Time_Hour"));
		    	schedule.setTime_Minute(rs.getInt("Time_Minute"));
		    	//Lecture information
		    	schedule.setLecture_ID(rs.getInt("Lecture_ID"));
		    	schedule.setFirstName(rs.getString("Name_FirstName"));
		    	schedule.setLastName(rs.getString("Name_LastName"));
		    	schedule.setBirthday(rs.getDate("Birthday"));
		    	schedule.setAdressCity(rs.getString("Address_City"));
		    	schedule.setAdressStreetName(rs.getString("Address_Name"));
		    	schedule.setAdressStreetNumber(rs.getInt("Address_street_Number"));
		    	//class information
		    	schedule.setFloor(rs.getInt("Floor"));
		    	schedule.setBuildingNumber(rs.getInt("BuildingNumber"));
		    	schedule.setClassNumber(rs.getInt("Class_ClassNumber"));
		    	sch.add(schedule);
		    }
		    connection.close();
		    return sch; 
		}
		finally 	
		{ 
			if(statement!=null) try{statement.close();}catch(Exception e){e.printStackTrace();} 
			if(connection!=null) try{connection.close();}catch(Exception e){e.printStackTrace();} 	
		} 
	}
	
	
	public ArrayList<ClassInformationQuery> getClassInformation() throws SQLException 	
	{
		try{
			connection=DriverManager.getConnection(protocol,USER,PASS);
			connection.setAutoCommit(false);
		    statement = connection.createStatement();
		    String sql= "SELECT * FROM takeplace right JOIN teaching ON teaching.Course_CourseNumber=takeplace.Course_CourseNumber ORDER BY takeplace.Class_ClassNumber;";
		    ResultSet rs = statement.executeQuery(sql);
		    ArrayList<ClassInformationQuery> sch = new ArrayList<ClassInformationQuery>();
		    while(rs.next())
		    {   
				sch.add(new ClassInformationQuery(rs.getInt("Class_ClassNumber"),rs.getInt("Lecture_ID"),rs.getInt("Course_CourseNumber")));
		    }
		    connection.close();
		    return sch; 
		}
		finally 	
		{ 
			if(statement!=null) try{statement.close();}catch(Exception e){e.printStackTrace();} 
			if(connection!=null) try{connection.close();}catch(Exception e){e.printStackTrace();} 	
		} 
	}

	
	//need to complete the object below
	public ArrayList<lectureCourseClassQuery> getClassCourseList() throws SQLException 	
	{
		try{
			connection=DriverManager.getConnection(protocol,USER,PASS);
			connection.setAutoCommit(false);
		    statement = connection.createStatement();
		    String sql= "SELECT S.Lecture_ID, T.*,Q.Class_ClassNumber FROM course AS T JOIN teaching AS S ON T.CourseNumber=S.Course_CourseNumber JOIN takeplace AS Q ON T.CourseNumber=Q.Course_CourseNumber;";
		    ResultSet rs = statement.executeQuery(sql);
		    ArrayList<lectureCourseClassQuery> lectureCourseClasslist = new ArrayList<lectureCourseClassQuery>();
		    while(rs.next())
		    {   	
		    	lectureCourseClasslist.add(new lectureCourseClassQuery(rs.getInt("Lecture_ID"),rs.getInt("CourseNumber"),rs.getInt("HourseAmount"),rs.getInt("Year"),rs.getInt("Day"),rs.getInt("Time_Hour"),rs.getInt("Time_Minute"),rs.getInt("Class_ClassNumber"),rs.getString("Name")));
		    }
		    connection.close();
		    return lectureCourseClasslist; 
		}
		finally 	
		{ 
			if(statement!=null) try{statement.close();}catch(Exception e){e.printStackTrace();} 
			if(connection!=null) try{connection.close();}catch(Exception e){e.printStackTrace();} 	
		} 
	}
	

	public ArrayList<LecturesPhonesQuery> getLecturesPhoneslist(int lectureID) throws SQLException 	
	{
		connection=DriverManager.getConnection(protocol,USER,PASS);
		connection.setAutoCommit(false);
	    statement = connection.createStatement();
	    String query = "SELECT * FROM abcd.lecturephone join lecture on lecture.ID=lecturephone.ID WHERE lecture.ID= "+lectureID;
	    ResultSet rs = statement.executeQuery(query);
	    ArrayList<LecturesPhonesQuery> lecturesphoneslist = new ArrayList<LecturesPhonesQuery>();
	    while(rs.next())
	    {   	    		
	    	lecturesphoneslist.add(new LecturesPhonesQuery(rs.getInt("PhoneNumber"),rs.getString("Name_FirstName"),rs.getString("Name_LastName")));	
	    }
	    connection.close();
	    return lecturesphoneslist; 
	}
	
}
