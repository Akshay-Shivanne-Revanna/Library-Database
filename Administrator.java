import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Borrower {

	index obj = new index();
	String bookTitle,branchN;
	int bId,brId,branchIdChoosed,bid,cardNum;
	
	//List of branches
	public void branchList(){
			
		PreparedStatement stmt;
		try {
				stmt = index.conn.prepareStatement("select branchName from tbl_library_branch");
				ResultSet rs = stmt.executeQuery();
	
				int count1=1;
				while(rs.next()){
					System.out.println(count1 + ")" + rs.getString("branchName"));
					String brName = rs.getString("branchName");
					count1++;
			 }
			 System.out.println(count1 + ")" + "Quit to previous");
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	
	}
	
	
	
	//Books in the branch
	public void branchBooks(String branchChoosed ){
		branchN =branchChoosed;
		PreparedStatement stmt;
			try {
				stmt=index.conn.prepareStatement("select b.title,a.authorName from tbl_book as b inner join tbl_book_authors as ba on b.bookId = ba.bookId inner join tbl_author as a on ba.authorId = a.authorId inner join tbl_book_copies bc on b.bookId = bc.bookId inner join tbl_library_branch lb on  bc.branchId = lb.branchId where lb.branchName=? and bc.noOfCopies is not null");
				stmt.setString(1,branchChoosed);
				ResultSet rs = stmt.executeQuery();
				
				int temp2=1;
				while(rs.next()){
			       	 System.out.println(temp2 + ") " + rs.getString("title") +" By " + rs.getString("authorName"));
			       	 temp2++;
				}
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	//Book choosed
	public void bookChoosed(int bookChoice){
		
		PreparedStatement stmt;
			try {
				stmt=index.conn.prepareStatement("select b.title,a.authorName from tbl_book as b inner join tbl_book_authors as ba on b.bookId = ba.bookId inner join tbl_author as a on ba.authorId = a.authorId inner join tbl_book_copies bc on b.bookId = bc.bookId inner join tbl_library_branch lb on  bc.branchId = lb.branchId where lb.branchName=? and bc.noOfCopies is not null");
				stmt.setString(1,branchN);
				ResultSet rs = stmt.executeQuery();
			
				int temp3=1;
				while(rs.next()){
					if(bookChoice == temp3){
					bookTitle = rs.getString("title");
					System.out.println(bookTitle);
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
		PreparedStatement stmt;
		Statement stmt2;
			try {
				stmt=index.conn.prepareStatement("select b.bookId,bc.branchId from tbl_book b join tbl_book_copies bc on  b.bookId = bc.bookId join tbl_library_branch lb on lb.branchId = bc.branchId where lb.branchName =? and b.title=?");
				stmt.setString(1,branchN);
				stmt.setString(2,bookTitle);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					bId = rs.getInt("bookId");
					brId = rs.getInt("branchId");
				}
			
			String sql = "insert into tbl_book_loans " +
                  "VALUES (bookId, branchId , cardNo , CURDATE,)";
			
			stmt2=index.conn.createStatement();
			stmt2.executeUpdate("insert into tbl_book_loans values ('"+bId+"','"+brId+"','"+index.cardNum+"',CURDATE(),DATE_ADD(CURDATE(),INTERVAL 7 DAY),NULL)");
			stmt2=index.conn.createStatement();
			stmt2.executeUpdate("update tbl_book_copies set noOfCopies=noOfCopies-1 where bookId='"+bId+"'");
			
			} catch (MySQLIntegrityConstraintViolationException e) {
		// TODO Auto-generated catch block
		System.out.println("Cannot check two books same time");
		}
	}
	
	
	//get branchId
	public void branchId(){
		PreparedStatement stmt;
			try {
				stmt = index.conn.prepareStatement("select branchId from tbl_library_branch");
				ResultSet rs = stmt.executeQuery();
				int newCount1=1;
				while(index.rs.next()){
					if(index.branchChoice2 == newCount1 ){
					branchIdChoosed = index.rs.getInt("branchId");
					}
				newCount1++;
				}
				System.out.println("branch choosed :" + branchIdChoosed );
			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	}
	
	//book return
		public void bookReturn(){
			PreparedStatement stmt;
			try {
				stmt = index.conn.prepareStatement("delete from tbl_book_loans where bookid=? and cardNo=? and branchId=?");
			
				stmt.setInt(1, index.bid);
				stmt.setInt(2, index.cardNum);
				stmt.setInt(3, branchIdChoosed);
				stmt.executeUpdate();
			
				stmt = index.conn.prepareStatement("update tbl_book_copies bc set noOfCopies =? where bookId =? and branchId =?");
				stmt.setInt(1, index.noOfCopies+1);
				stmt.setInt(2, index.bid);
				stmt.setInt(3, branchIdChoosed);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
}
