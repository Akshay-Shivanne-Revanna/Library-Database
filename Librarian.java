import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Librarian {
	
	index obj = new index();
	int bookOption,bSelectedBId,bookSelectedId,bookSelectedNoOfCopies,bookSelectedBId;
	String branchName, branchAddress,bookSelectedTitle;
	public static int temp =0;
	public static int branchId=0;
	
	public void listLibrarybranch(){
		
		try {
			 
			 String selectSQL = "select branchName from tbl_library_branch";
			 PreparedStatement stmt = index.conn.prepareStatement(selectSQL);
			 ResultSet rs = stmt.executeQuery(selectSQL );
			 
			
			int count=1;
			 while(rs.next()){
				 System.out.println(count + ")" + rs.getString("branchName"));
				 String branchName = rs.getString("branchName");
				 
				 count++;
			 }
			 
			 System.out.println(count + ")" + "Quit to previous");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
	
	public int selectBranch(int branchChoice ){
		
		 String selectSQL = "select branchName,branchId from tbl_library_branch";
		 
		 int count2=1;
		 
		try {
			 PreparedStatement stmt = index.conn.prepareStatement(selectSQL);
			 ResultSet rs = stmt.executeQuery(selectSQL );
	
			 while(rs.next()){
				 if(branchChoice == count2){
					 index.branchCh = rs.getString("branchName");
					 branchId = rs.getInt("branchId");
					 System.out.println("branch choosed:" + index.branchCh );
				 }
				
				 count2++;
			 }
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count2;
	}
	

	
	public void branchDetails(){
		
				
		try {
			 PreparedStatement stmt = index.conn.prepareStatement("select * from tbl_library_branch where branchName=?");
			 stmt.setString(1,index.branchCh);
			 ResultSet rs = stmt.executeQuery();
	
			 while (rs.next()) {
				 
				 System.out.println(rs.getInt("branchId") + "  branchName : " + rs.getString("branchName") + "  branchAddress : " + rs.getString("branchAddress"));
	 			 System.out.println();
				    branchId = rs.getInt("branchId");
				    branchName = rs.getString("branchName");	
	 				branchAddress = rs.getString("branchAddress");
	 		 }
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateLibraryDetails(){
		
		System.out.println("Please enter new branch name or enter N/A for no change:");
	    String BName = index.scan.next();
	    System.out.println();
	    System.out.println("Please enter new branch address or enter N/A for no change:");
	    String Address = index.scan.next();
	    
	    if(BName.equals("N/A")){
	    	BName = index.branchCh;
	    }
	    
	    if(Address.equals("N/A")){
	    	Address = branchAddress;
	    }
	    
		System.out.println("addr--" + Address);
		try {
				 PreparedStatement stmt = index.conn.prepareStatement("update tbl_library_branch set branchName =?, branchAddress=? where branchName =? and branchId=?");
				 stmt.setString(1,index.branchCh);
				 stmt.setString(1,BName);
				 stmt.setString(2,Address);
				 stmt.setString(3,index.branchCh);
				 stmt.setInt(4, branchId);
				 stmt.executeUpdate();
				 System.out.println("BRANCH DETAILS UPDATED SUCCESSFULLY");
				 } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				 }
			 
	}
	
	//DISPLAY BOOKS LIST
	public int booksList(){
		
		PreparedStatement stmt;
		try {
			System.out.println("branch id :" +branchId );
			stmt = index.conn.prepareStatement("select lb.branchId,b.title,a.authorName from tbl_book b inner join tbl_book_authors as ba on b.bookId = ba.bookId inner join tbl_author a on ba.authorId = a.authorId inner join tbl_book_copies bc on bc.bookId = b.bookId inner join tbl_library_branch lb on bc.branchId = lb.branchId where lb.branchId =?");
			stmt.setInt(1, branchId);
			ResultSet rs=stmt.executeQuery();
			
			temp=1;
			while(rs.next()){
		       	 System.out.println(temp + ") " + rs.getString("title") +" By " + rs.getString("authorName"));
		       	 temp++;
		        }
		        System.out.println(temp + " ) " + "Quit to cancel operation");
		        
		        bookOption = index.scan.nextInt();
		        
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookOption;
	}
	
	//SELECT THE BOOK TO BE ADDED FROM THE LIST
	public int selectBookAdd(int bookOp){
		
		PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("select b.bookId,b.title,a.authorName from tbl_book b inner join tbl_book_authors as ba on b.bookId = ba.bookId inner join tbl_author a on ba.authorId = a.authorId");
		
		ResultSet rs=stmt.executeQuery();
		
		int temp1=1;
        while(rs.next()){
       	 
       	 if(bookOp == temp1){
       		bSelectedBId= rs.getInt("bookId");
       		 //System.out.println("title" + btitle);
       	 }
       	 temp1++;
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bSelectedBId;
	}
	
	public void listSelectedBookCopies(int bookId){
		
		PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("select lb.branchId,bc.bookId, b.title, bc.noOfCopies from tbl_library_branch as lb inner join tbl_book_copies as bc on lb.branchId=bc.branchId inner join tbl_book  b on bc.bookId=b.bookId where lb.branchName= ? and b.bookId=?");
		
		stmt.setString(1,index.branchCh);
		stmt.setInt(2,bookId);
		ResultSet rs=stmt.executeQuery();
		
		while (rs.next()) {
			bookSelectedBId = rs.getInt("branchId");
			bookSelectedId = rs.getInt("bookId");
			bookSelectedTitle = rs.getString("title");	
			bookSelectedNoOfCopies = rs.getInt("noOfCopies");
		 }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void addBookCopies(){
		
		System.out.println("Add copies of Book to the Branch");
		System.out.println();
		
		//GET BOOK LIST
		int bookOp = booksList();
		
		//SELECT BOOK AND GET BOOK ID
		int bookId = selectBookAdd(bookOp);
        		
		//LIST THE COPIES IN THE BRANCH FOR THE PARTICULAR BOOK
		listSelectedBookCopies(bookId);
		
		if(bookOp<temp){
         
       	 System.out.println("Existing number of copies" );
       	 System.out.println(bookSelectedNoOfCopies);
       	 System.out.println("Enter new number of copies:");
       	 int updatedCopies = index.scan.nextInt() + bookSelectedNoOfCopies;
       	 
       
       	 try {
       		
       		PreparedStatement stmt;
       		
       		stmt = index.conn.prepareStatement("select branchId from tbl_library_branch where branchName =?");
       		stmt.setString(1, index.branchCh);
       		ResultSet rs=stmt.executeQuery();
       		
       		while(rs.next()){
       			branchId = rs.getInt("branchId");
       		}
       		       		
			stmt = index.conn.prepareStatement("update tbl_book_copies bc join tbl_library_branch lb on bc.branchId = lb.branchId join tbl_book b on bc.bookId = b.bookId set bc.noOfCopies = ? where b.bookId = ? and lb.branchName=?");
		    stmt.setInt(1,updatedCopies);
       	 	stmt.setInt(2, bookSelectedId);
       	 	stmt.setString(3,index.branchCh );
       	 	stmt.executeUpdate(); 
      	 
      	 System.out.println("Book copies updated succesfully");
        }
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
		
	}
	
}
