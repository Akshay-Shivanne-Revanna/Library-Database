import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Borrower {

	index obj = new index();
	
	//List of branches
	public void branchList(){
		try {
			index.stmt = index.conn.prepareStatement("select branchName from tbl_library_branch");
		
			 index.rs = index.stmt.executeQuery();
	
			 int count1=1;
			 while(index.rs.next()){
				 System.out.println(count1 + ")" + index.rs.getString("branchName"));
				 String brName = index.rs.getString("branchName");
				
				 count1++;
			 }
			 System.out.println(count1 + 1 + ")" + "Quit to previous");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}
	
	//Books in the branch
	public void branchBooks(){
			try {
				index.stmt=index.conn.prepareStatement("select b.title,a.authorName from tbl_book as b inner join tbl_book_authors as ba on b.bookId = ba.bookId inner join tbl_author as a on ba.authorId = a.authorId inner join tbl_book_copies bc on b.bookId = bc.bookId inner join tbl_library_branch lb on  bc.branchId = lb.branchId where lb.branchName=? and bc.noOfCopies is not null");
			
			index.stmt.setString(1,index.branchChoosed);
			index.rs = index.stmt.executeQuery();
			
			int temp2=1;
			
			while(index.rs.next()){
			
       	 System.out.println(temp2 + ") " + index.rs.getString("title") +" By " + index.rs.getString("authorName"));
       	 temp2++;
			}} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//Book choosed
	public void bookChoosed(){
			
			try {
				index.stmt=index.conn.prepareStatement("select b.title,a.authorName from tbl_book as b inner join tbl_book_authors as ba on b.bookId = ba.bookId inner join tbl_author as a on ba.authorId = a.authorId inner join tbl_book_copies bc on b.bookId = bc.bookId inner join tbl_library_branch lb on  bc.branchId = lb.branchId where lb.branchName=? and bc.noOfCopies is not null");
			
			index.stmt.setString(1,index.branchChoosed);
			index.rs = index.stmt.executeQuery();
			
			int temp3=1;
			
			while(index.rs.next()){
				
				
				if(index.bookChoice == temp3){
					index.bookTitle = index.rs.getString("title");
					System.out.println(index.bookTitle);
				}
				
	        	 
	        	 temp3++;
			} 
	
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//inserting the book in book loans table
	public void bookLoans() throws SQLException{
			try {
				index.stmt=index.conn.prepareStatement("select b.bookId,bc.branchId from tbl_book b join tbl_book_copies bc on  b.bookId = bc.bookId join tbl_library_branch lb on lb.branchId = bc.branchId where lb.branchName =? and b.title=?");
			
			index.stmt.setString(1, index.branchChoosed);
			index.stmt.setString(2,index.bookTitle);
			index.rs = index.stmt.executeQuery();
			
			while(index.rs.next()){
				
				index.bId = index.rs.getInt("bookId");
				index.brId = index.rs.getInt("branchId");
				
			}
			
			String sql = "insert into tbl_book_loans " +
                  "VALUES (bookId, branchId , cardNo , CURDATE,)";
			
			index.stmt2=index.conn.createStatement();
			index.stmt2.executeUpdate("insert into tbl_book_loans values ('"+index.bId+"','"+index.brId+"','"+index.cardNum+"',CURDATE(),DATE_ADD(CURDATE(),INTERVAL 7 DAY),NULL)");
			
			index.stmt2=index.conn.createStatement();
			index.stmt2.executeUpdate("update tbl_book_copies set noOfCopies=noOfCopies-1 where bookId='"+index.bId+"'");
			
	} catch (MySQLIntegrityConstraintViolationException e) {
		// TODO Auto-generated catch block
		System.out.println("Cannot check two books same time");
	}
	}
	
	
	//get branchId
	public void branchId(){
			try {
				index.stmt = index.conn.prepareStatement("select branchId from tbl_library_branch");
			
			index.rs = index.stmt.executeQuery();
			
			
			int newCount1=1;
			while(index.rs.next()){
				
				
				if(index.branchChoice2 == newCount1 ){
					index.branchIdChoosed = index.rs.getInt("branchId");
					
				}

				newCount1++;
			}
			
			System.out.println("branch choosed :" + index.branchIdChoosed );
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	//book return
	
	public void bookReturn(){
		
			try {
				index.stmt = index.conn.prepareStatement("delete from tbl_book_loans where bookid=? and cardNo=? and branchId=?");
			
			index.stmt.setInt(1, index.bid);
			index.stmt.setInt(2, index.cardNum);
			index.stmt.setInt(3, index.branchIdChoosed);
			index.stmt.executeUpdate();
			
			index.stmt = index.conn.prepareStatement("update tbl_book_copies bc set noOfCopies =? where bookId =? and branchId =?");
			index.stmt.setInt(1, index.noOfCopies+1);
			index.stmt.setInt(2, index.bid);
			index.stmt.setInt(3, index.branchIdChoosed);
			index.stmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
}
