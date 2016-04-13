import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Administrator {

	String newPubName,newPubAddr,newPubNum,pubName,pubAddr,pubPhone,branchN,branchA,bname,bAddr,bPhone,publisherName,publisherAddress,publisherPhone,newpubPhone2,newPubAddr2,newpubName2,pubName2,PubAddr2,authName2,authName,genreName,brCh;
	int publisherId,newPubId2,pubId2,authId2,genreId,bookId2,brId,cardNo;
	Scanner scan = new Scanner(System.in);
	Librarian lib = new Librarian();
	
	
	
	//ADD TO PUBLISHERS
	public void addToPublisher(){
		
			 
			 System.out.println("Enter the publisher Name");
			 publisherName = scan.next();
			 System.out.println("Enter the Publisher Address");
			 publisherAddress = scan.next();
			 System.out.println("Enter the publisher Phone");
			 publisherPhone = scan.next();
			 PreparedStatement stmt;
			 try {
				stmt = index.conn.prepareStatement("insert into tbl_publisher(publisherId, publisherName, publisherAddress,publisherPhone) values(?, ?, ?,?)");
			
				stmt.setString(1, "null");
				stmt.setString(2, publisherName);
				stmt.setString(3, publisherAddress);
				stmt.setString(4, publisherPhone);
				stmt.executeUpdate();
			 System.out.println("New publisher is Added");
			 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		
		}
	
	
		//DELETE PUBLISHER
		public void deletePublisher(){
		
			 System.out.println("Enter the publisher name to be deleted");
  			 String dPubName = scan.next();
  			 PreparedStatement stmt;
  			 try {
				stmt = index.conn.prepareStatement("delete from tbl_publisher where publisherName=? ");
				stmt.setString(1, dPubName);
				stmt.executeUpdate();
				System.out.println("The Publisher is deleted" );
  			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
  			 }
		}
		
		
		//UPDATE PUBLISHER
		public void updatePublisher(){
			
			System.out.println("Enter the publisher Id");
  			 int uPublishId = scan.nextInt();
  			 PreparedStatement stmt;
  			 try {
				stmt = index.conn.prepareStatement("select publisherName,publisherAddress,publisherPhone from tbl_publisher where publisherId=?");
				stmt.setInt(1, uPublishId);
				ResultSet rs = stmt.executeQuery();
  			 
  			 while(rs.next()){
  				pubName = rs.getString("publisherName");
  				System.out.println("publisher name : " + pubName);
  				pubAddr = rs.getString("publisherAddress");
  				System.out.println("publisher Address  : " + pubAddr);
  				pubPhone = rs.getString("publisherPhone");
  				System.out.println("publisher Phone : " + pubPhone);
  				
  			 }
  			 
  			 System.out.println("Please enter new publisher name or enter N/A for no change: ");
  			 newPubName = scan.next();
  			 System.out.println("Please enter new publisher address or enter N/A for no change: ");
 			 newPubAddr = scan.next();
 			 System.out.println("Please enter new publisher phone number or enter N/A for no change: ");
 			 newPubNum = scan.next();
 			 
 			 if(newPubName.equals("N/A")){
 				newPubNum = pubName;
 			 }
 			
 			 if(newPubAddr.equals("N/A")){
 				newPubAddr = pubAddr;
 			 }
 			
 			 if(newPubNum.equals("N/A")){
 				newPubNum = pubPhone;
 			 }
 			 
  			 	stmt = index.conn.prepareStatement("update tbl_publisher set publisherName =?,publisherAddress=?,publisherPhone=? where publisherId =? ");
  				stmt.setString(1, newPubName);
  				stmt.setString(2, newPubAddr);
  				stmt.setString(3, newPubNum);
  				stmt.setInt(4, uPublishId);
  				stmt.executeUpdate();
  				System.out.println("publisher details updated");
  			
  			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	//DISPLAY PUBLISHERS
		public void displayPublishers(){
			
			PreparedStatement stmt;
 			 try {
				stmt = index.conn.prepareStatement("select publisherId,publisherName,publisherAddress,publisherPhone from tbl_publisher");
				ResultSet rs = stmt.executeQuery();
				System.out.println("PUBLISHER ID   |" + "   PUBLISHER NAME  |" +   "  PUBLISHER ADDRESS  "  );
				while(rs.next()){
	  				String pubId = rs.getString("publisherId");
	  				String pubName = rs.getString("publisherName");
	  				String pubAdd = rs.getString("publisherAddress");
	  				String pubPh = rs.getString("publisherPhone");
	  				
	  				System.out.println("    " + pubId + "  |  " + pubName + "  | " + pubAdd);
	  			 }
				
 			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
 			 }
			
			
		}
	
	
	//ADD LIBRARY BRANCHES
		public void addLibraryBranches(){
			
			System.out.println("Enter the Branch Id");
			int AbranchId = index.scan.nextInt();
			System.out.println("Enter the Branch Name");
			String AbranchName = scan.next();
			System.out.println("Enter the Branch Address");
			String AbranchAddress = scan.next();
			PreparedStatement stmt;
			try {
				stmt = index.conn.prepareStatement("insert into tbl_library_branch(branchId, branchName, branchAddress) values(?, ?, ?)");
				stmt.setInt(1, AbranchId);
				stmt.setString(2, AbranchName);
				stmt.setString(3, AbranchAddress);
				stmt.executeUpdate();
  			System.out.println("New Library Branch is Added");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//DELETE LIBRARY BRANCHES
		public void deleteLibraryBranch(){
			System.out.println("Enter the branchName to deleted");
			String dBranName = scan.next();
			PreparedStatement stmt;	 
			try {
				stmt = index.conn.prepareStatement("delete from tbl_library_branch where branchName=? ");
				stmt.setString(1, dBranName);
				stmt.executeUpdate();
				System.out.println("The library branch is deleted" );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//UPDATE LIBRARY BRANCHES
		public void updateLibraryBranch(){
			
			System.out.println("Enter the branch Id");
		    int uBranchId = scan.nextInt();
		    PreparedStatement stmt;
		    try {
				stmt = index.conn.prepareStatement("select branchName,branchAddress from tbl_library_branch where branchId=?");
				stmt.setInt(1, uBranchId);
				ResultSet rs = stmt.executeQuery();
  			 
				while(index.rs.next()){
					branchN = rs.getString("branchName");
					System.out.println("branch name : " + branchN);
					branchA = rs.getString("branchAddress");
					System.out.println("branch Address  : " + branchA);
  				}
				 
				System.out.println("Please enter new branch name or enter N/A for no change: ");
				String newBranchName = scan.next();
				System.out.println("Please enter new branch name or enter N/A for no change: ");
				String newBranchAddr = scan.next();
  			 
				if(newBranchName.equals("N/A")){
					newBranchName = branchN;
				}
				
				if(newBranchAddr.equals("N/A")){
					newBranchAddr = branchN;
				}
				
				stmt = index.conn.prepareStatement("update tbl_library_branch set branchName =?,branchAddress=? where branchId =? ");
				stmt.setString(1, newBranchName);
				stmt.setString(2, newBranchAddr);
				stmt.setInt(3, uBranchId);
				stmt.executeUpdate();
				System.out.println("branch name updated");
  				
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//ADD BORROWERS
		public void addBorrowers(){
			System.out.println("Enter the Card Number");
			 int borrCard = scan.nextInt();
			 System.out.println("Enter the borrower name");
			 String borrName = scan.next();
			 System.out.println("Enter the borrower address");
			 String borrAddr = scan.next();
			 System.out.println("Enter the borrower phone number");
			 String borrPhone = scan.next();
			 PreparedStatement stmt;
			 try {
				stmt = index.conn.prepareStatement("insert into tbl_borrower(cardNo, name, address,phone) values(?, ?, ?,?)");
				stmt.setInt(1, borrCard);
				stmt.setString(2, borrName);
				stmt.setString(3, borrAddr);
				stmt.setString(4, borrPhone);
				stmt.executeUpdate();
				System.out.println(" Borrower details are Added");
			 	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		
		//DELETE BORROWERS
		public void deleteBorrowers(){
			 
			 System.out.println("Enter the borrower name to be deleted");
			 String dBorrName = scan.next();
			 PreparedStatement stmt;
			 try {
				stmt = index.conn.prepareStatement("delete from tbl_borrower where name=? ");
			    stmt.setString(1, dBorrName);
			    stmt.executeUpdate();
			    System.out.println("The borrower is deleted" );
			 	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		
		//UPDATE BORROWERS
		public void  updateBorrowers(){
			System.out.println("Enter the card No");
			 int uCardno = scan.nextInt();
			 PreparedStatement stmt;
			 try {
				stmt = index.conn.prepareStatement("select name,address,phone from tbl_borrower where cardNo=?");
				stmt.setInt(1, uCardno);
				ResultSet rs = stmt.executeQuery();
  			 
				while(rs.next()){
					bname = rs.getString("name");
					System.out.println("borrower name : " + bname);
					bAddr = rs.getString("address");
					System.out.println("borrower Address  : " + bAddr);
					bPhone = rs.getString("phone");
					System.out.println("borrower phone  : " + bPhone);
				}
			 
  			System.out.println("Please enter new borrower name or enter N/A for no change: ");
  			String newBorrName = scan.next(); 
  			System.out.println("Please enter new borrower address or enter N/A for no change: ");
  			String newBorrAddr = scan.next();
  			System.out.println("Please enter new borrower phone or enter N/A for no change: ");
  			String newBorrPhone = scan.next();
  			
  			if(newBorrName.equals("N/A")){
  				newBorrName = bname;
  			}
  			if(newBorrAddr.equals("N/A")){
  				newBorrAddr = bAddr;
  			}
  			if(newBorrPhone.equals("N/A")){
  				newBorrPhone = bPhone;
  			}
  			
  			if(newBorrName.equals("N/A")){
  				 
  				stmt = index.conn.prepareStatement("update tbl_borrower set name =?,address=?,phone=? where uCardno =? ");
  				stmt.setString(1, newBorrName);
  				stmt.setString(2, newBorrAddr);
  				stmt.setString(3, newBorrPhone);
  				stmt.setInt(4, uCardno);
  				stmt.executeUpdate();
  				System.out.println("borrower phone updated");
  				 
  			 }
  		 
  		
		} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
	}
		
		
	public void addAuthor(){
		
		System.out.println("Enter the authorName");
		String authName = scan.next();
		
		PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("insert into tbl_author(authorId, authorName) values(?, ?)");
			stmt.setString(1,null);
			stmt.setString(2,authName);
			stmt.executeUpdate();
			System.out.println("New author is added");
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
	
	public void listAuthorDetails(){
		
		System.out.println("List of Authors");
		PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("select authorId,authorName from tbl_author");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Author ID  | " + "  Author Name | "  );
			System.out.println();
			while(rs.next()){
				
				String authId = rs.getString("authorId");
				String authN = rs.getString("authorName");
				System.out.println("    " +authId+ "      |     "  +authN);
				
			}
			
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
	
	public void deleteAuthor(){
		
		listAuthorDetails();
		System.out.println("Enter the author name to be deleted");
		String authorname = scan.next();
		
		PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("delete from tbl_author where authorName =?");
			stmt.setString(1, authorname);
			ResultSet rs = stmt.executeQuery();
			System.out.println("-- Author Deleted --");
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
	
	public void updateAuthor(){
		listAuthorDetails();
		System.out.println();
		System.out.println("Enter the author id");
		String updateAuthId = scan.next();
		System.out.println("Enter the updated author name or N/A");
		String updateAuth = scan.next();
		
		if(updateAuth.equals("N/A")){
			return;
		}
		
		PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("update tbl_author set authorName =? where authorId =?");
			stmt.setString(1, updateAuth);
			stmt.setString(2, updateAuthId);
			ResultSet rs = stmt.executeQuery();
			System.out.println("-- Author Deleted --");
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		
	}
	
	public int getAuthorID(String authName2){
		
		PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("select authorId from tbl_author where authorName =?");
			stmt.setString(1, authName2);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				authId2 = rs.getInt("authorId");
			}
							
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		return authId2;
	}
	
	
	//LIST GENRES
	public void listGenre(){
		
		PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("select genre_id,genre_name from tbl_genre");
			ResultSet rs = stmt.executeQuery();
			System.out.println("  GENRE ID  |" + "  GENRE NAME  ");
			while(rs.next()){
				genreId = rs.getInt("genre_id");
				genreName =rs.getString("genre_name");
				System.out.println("      " + genreId + " |      " + genreName);
			}
							
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	}
	
	//branch selected to add the book
	public int branchOp(int branchChoice ){
		
		 String selectSQL = "select branchName,branchId from tbl_library_branch";
		 int count2=1;
		 
		try {
			 PreparedStatement stmt = index.conn.prepareStatement(selectSQL);
			 ResultSet rs = stmt.executeQuery(selectSQL );
	
			 while(rs.next()){
				 if(branchChoice == count2){
					 brCh = rs.getString("branchName");
					 brId = rs.getInt("branchId");
				 }
				 count2++;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brId;
	}
	
	
	//ADD A BOOK
	public void addBook(){
		PreparedStatement stmt;
		try {
			System.out.println("Enter the book title");
			String newBookTitle = scan.next();
			System.out.println();
			displayPublishers();
					
			System.out.println("Select the publisher name from the list or Enter N/A to insert a new publisher");
			pubName2 = index.scan.next();
			System.out.println();
			if(pubName2.equals("N/A")){
				addToPublisher();
				newpubName2=publisherName;
				newPubAddr2=publisherAddress;
				newPubId2=publisherId;
				newpubPhone2=publisherPhone;
			}else {
			System.out.println("Enter the publisher addr");
			PubAddr2 = index.scan.next();
			stmt = index.conn.prepareStatement("select publisherId from tbl_publisher where publisherName =? and publisherAddress =?");
			stmt.setString(1, pubName2);
			stmt.setString(2, PubAddr2);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
					pubId2 = rs.getInt("publisherId");
					System.out.println("-----" + pubId2 );
			}
			
			}
		
		
			//inserting in the book table
			stmt = index.conn.prepareStatement("insert into tbl_book(bookId,title,pubId) values(?, ?,?)");
			stmt.setString(1,null);
			stmt.setString(2,newBookTitle);
			stmt.setInt(3, pubId2);
			stmt.executeUpdate();
			
			System.out.println();
			listAuthorDetails();
			System.out.println("insert the author name from the list or Enter N/A to insert a new author");
			authName2 = scan.next();
		
			if(authName2.equals("N/A")){
				addAuthor();
				authName2 = authName;
				authId2 = getAuthorID(authName2);
			}else {
				authId2= getAuthorID(authName2);
			}
		
			//inserting in the book_authors table
			stmt = index.conn.prepareStatement("select bookId from tbl_book where title=? and pubId=?");
			stmt.setString(1, newBookTitle);
			stmt.setInt(2, pubId2);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				bookId2 = rs.getInt("bookId");
			}
			
			stmt = index.conn.prepareStatement("insert into tbl_book_authors(bookId,authorId) values(?,?)");
			stmt.setInt(1,bookId2);
			stmt.setInt(2,authId2);
			stmt.executeUpdate();
			System.out.println();
		
			listGenre();
		
			do{
				System.out.println("Enter the genre ID");
				genreId = scan.nextInt();
				System.out.println("Enter Y to add new genre or Enter N to exit");
				String opt = scan.next();
				if(opt.equals("N")){
					break;
				}
			
				stmt = index.conn.prepareStatement("insert into tbl_book_genres(genre_id, bookId) values(?, ?)");
				stmt.setInt(1,genreId);
				stmt.setInt(2,bookId2);
				stmt.executeUpdate();
				System.out.println("New genre is added");
					
			}while(true);
		
			//select the branch the book to be added
			System.out.println();
			System.out.println("------Library branches list-----");
			lib.listLibrarybranch();
			System.out.println("select the branch for which the book is added");
			int brChoice = scan.nextInt();
			int branchId = branchOp(brChoice);
		
			System.out.println("Enter the Number of copies of the book");
			int copiesNo = scan.nextInt();
		
			//inserting in the book copies table
			stmt = index.conn.prepareStatement("insert into tbl_book_copies(bookId,branchId,noOfCopies) values(?, ?,?)");
			stmt.setInt(1,bookId2);
			stmt.setInt(2,branchId);
			stmt.setInt(3, copiesNo);
			stmt.executeUpdate();
			System.out.println("New Book is added");
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}	
	
	//UPDATE BOOK
	public void updateBook(){
		
		try {
			 PreparedStatement stmt = index.conn.prepareStatement("select bookid,title from tbl_book");
			 ResultSet rs = stmt.executeQuery();
	
			 while (rs.next()) {
				  System.out.println();
				  int bkId = rs.getInt("bookid");
				  String btitle = rs.getString("title");	
	 		 }
			 
			 System.out.println("Enter the title of the book to be updated");
			 String titOp = scan.next();
			 System.out.println("Enter the updated title");
			 String upTitle = scan.next();
			 
			 stmt = index.conn.prepareStatement("update tbl_book set title=? where title=? ");
			 stmt.setString(1, titOp);
			 stmt.setString(2, upTitle);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//DELETE BOOK
	public void deleteBook(){
		
		 PreparedStatement stmt;
		try {
			stmt = index.conn.prepareStatement("select bookid,title from tbl_book");
		    ResultSet rs = stmt.executeQuery();
		    int bkIdD=0;
		    String btitleD;
		    System.out.println("  BOOK ID  |" + "  BOOK TITLE  ");
		    while (rs.next()) {
		    	System.out.println();
		    	bkIdD = rs.getInt("bookid");
		    	btitleD= rs.getString("title");	
		    	
		    	System.out.println("      " + bkIdD + " |      " + btitleD);
		    }
		    
		    System.out.println("Enter the title of the book to be deleted");
			String titOp = scan.next();
			
			stmt = index.conn.prepareStatement("delete from tbl_book where bookId =?");
			stmt.setInt(1, bkIdD);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Display particular borrower books checked out
	public void booksCheckedOut(){
		PreparedStatement stmt;
		try {
			 
			 stmt = index.conn.prepareStatement("select bookId,branchId,dueDate from tbl_book_loans where cardNo=? and datein is null");
			 stmt.setInt(1, cardNo);
			 ResultSet rs = stmt.executeQuery();
			 
			
			 while(rs.next()){
				 System.out.println("Book ID" + " : " + rs.getInt("bookId") + "  Branch Id : " + rs.getInt("branchId") + "  DueDate :" + rs.getDate("dueDate"));
			 }
			 
			 
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	
	//UPDATE DUE DATE
	public void updateDueDate(){
		try {
			PreparedStatement stmt;
			do {
				System.out.println("----------------------------------------------");
				System.out.println("Enter the your Card Number:");
				cardNo = scan.nextInt();
				stmt = index.conn.prepareStatement("select cardNo from tbl_borrower where cardNo =?");
				stmt.setInt(1,cardNo);
				ResultSet rs = stmt.executeQuery();
				if(!rs.next()){
					System.out.println("Invalid PIN");
   				
				}else{
					break;
				}
   			
			}while(true);
			
			booksCheckedOut();
			
			System.out.println("Enter the book id whose due date needs to be extended :");
			int dBid = scan.nextInt();
			
			stmt = index.conn.prepareStatement("update tbl_book_loans set dueDate = DATE_ADD(CURDATE(),INTERVAL 7 DAY) where cardNo =? and bookId=? ");
			stmt.setInt(1, cardNo);
			stmt.setInt(2, dBid);
			stmt.executeUpdate();
			System.out.println("Due Date is extented..");
		    
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
}

