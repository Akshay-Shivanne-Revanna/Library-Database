import java.sql.SQLException;

public class Administrator {

	//ADD TO PUBLISHERS
	public void addToPublisher(){
		
			 System.out.println("Enter the Publisher Id");
			 int publisherId = index.scan.nextInt();
			 System.out.println("Enter the publisher Name");
			 String publisherName = index.scan.next();
			 System.out.println("Enter the Publisher Address");
			 String publisherAddress = index.scan.next();
			 System.out.println("Enter the publisher Phone");
			 String publisherPhone = index.scan.next();
			 
			 try {
				index.stmt = index.conn.prepareStatement("insert into tbl_publisher(publisherId, publisherName, publisherAddress,publisherPhone) values(?, ?, ?,?)");
			
			 index.stmt.setInt(1, publisherId);
			 index.stmt.setString(2, publisherName);
			 index.stmt.setString(3, publisherAddress);
			 index.stmt.setString(4, publisherPhone);
			 index.stmt.executeUpdate();
			 System.out.println("New publisher is Added");
			 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		
		}
	
	
		//DELETE PUBLISHER
		public void deletePublisher(){
		
			 System.out.println("Enter the publisher name to be deleted");
  			 String dPubName = index.scan.next();
  			 
  			try {
				index.stmt = index.conn.prepareStatement("delete from tbl_publisher where publisherName=? ");
				index.stmt.setString(1, dPubName);
				index.stmt.executeUpdate();
				System.out.println("The Publisher is deleted" );
  			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//UPDATE PUBLISHER
		public void updatePublisher(){
			
			System.out.println("Enter the publisher Id");
  			 int uPublishId = index.scan.nextInt();
  			 
  			try {
				index.stmt = index.conn.prepareStatement("select publisherName,publisherAddress,publisherPhone from tbl_publisher where publisherId=?");
			
  			index.stmt.setInt(1, uPublishId);
  			index.rs = index.stmt.executeQuery();
  			 
  			 while(index.rs.next()){
  				System.out.println("publisher name : " + index.rs.getString("publisherName"));
  				System.out.println("publisher Address  : " + index.rs.getString("publisherAddress"));
  				System.out.println("publisher Phone : " + index.rs.getString("publisherPhone"));
  				
  			 }
  			 
  			 System.out.println("Please enter new publisher name or enter N/A for no change: ");
  			 String newPubName = index.scan.next();
  			 
  			 if(!newPubName.equals("N/A")){
  				 
  				index.stmt = index.conn.prepareStatement("update tbl_publisher set publisherName =? where publisherId =? ");
  				index.stmt.setString(1, newPubName);
  				index.stmt.setInt(2, uPublishId);
  				index.stmt.executeUpdate();
  				 System.out.println("publisher name updated");
  				 
  			 }
  			
  			 System.out.println("Please enter new publisher address or enter N/A for no change: ");
  			 String newPubAddr = index.scan.next();
  			 
  			 if(!newPubAddr.equals("N/A")){
  				 
  				index.stmt = index.conn.prepareStatement("update tbl_publisher set publisherAddress =? where publisherId =? ");
  				index.stmt.setString(1, newPubAddr);
  				index.stmt.setInt(2, uPublishId);
  				index.stmt.executeUpdate();
  				 System.out.println("publisher name updated");
  				 
  			 }
  			 
  			 System.out.println("Please enter new publisher phone number or enter N/A for no change: ");
  			 String newPubNum = index.scan.next();
  			 
  			 if(!newPubNum.equals("N/A")){
  				 
  				index.stmt = index.conn.prepareStatement("update tbl_publisher set publisherPhone =? where publisherId =? ");
  				index.stmt.setString(1, newPubNum);
  				index.stmt.setInt(2, uPublishId);
  				index.stmt.executeUpdate();
  				 System.out.println("publisher name updated");
  				 
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
			String AbranchName = index.scan.next();
			System.out.println("Enter the Branch Address");
			String AbranchAddress = index.scan.next();
			
			try {
				index.stmt = index.conn.prepareStatement("insert into tbl_library_branch(branchId, branchName, branchAddress) values(?, ?, ?)");
			
			index.stmt.setInt(1, AbranchId);
			index.stmt.setString(2, AbranchName);
			index.stmt.setString(3, AbranchAddress);
			index.stmt.executeUpdate();
  			System.out.println("New Library Branch is Added");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//DELETE LIBRARY BRANCHES
		public void deleteLibraryBranch(){
			System.out.println("Enter the branchName to deleted");
			String dBranName = index.scan.next();
				 
			try {
				index.stmt = index.conn.prepareStatement("delete from tbl_library_branch where branchName=? ");
				index.stmt.setString(1, dBranName);
				index.stmt.executeUpdate();
				System.out.println("The library branch is deleted" );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//UPDATE LIBRARY BRANCHES
		public void updateLibraryBranch(){
			
			System.out.println("Enter the branch Id");
		    int uBranchId = index.scan.nextInt();
				 
		    try {
				index.stmt = index.conn.prepareStatement("select branchName,branchAddress from tbl_library_branch where branchId=?");
			
				index.stmt.setInt(1, uBranchId);
				index.rs = index.stmt.executeQuery();
  			 
				while(index.rs.next()){
					System.out.println("branch name : " + index.rs.getString("branchName"));
					System.out.println("branch Address  : " + index.rs.getString("branchAddress"));
  			
				}
				 
				System.out.println("Please enter new branch name or enter N/A for no change: ");
				String newBranchName = index.scan.next();
  			 
				if(!newBranchName.equals("N/A")){
  				 
					index.stmt = index.conn.prepareStatement("update tbl_library_branch set branchName =? where branchId =? ");
					index.stmt.setString(1, newBranchName);
					index.stmt.setInt(2, uBranchId);
					index.stmt.executeUpdate();
					System.out.println("branch name updated");
  				 
				}
  			 
				System.out.println("Please enter new branch name or enter N/A for no change: ");
				String newBranchAddr = index.scan.next(); 
  			
				if(!newBranchName.equals("N/A")){
  				 
					index.stmt = index.conn.prepareStatement("update tbl_library_branch set branchAddress =? where branchId =? ");
					index.stmt.setString(1, newBranchAddr);
					index.stmt.setInt(2, uBranchId);
					index.stmt.executeUpdate();
					System.out.println("branch name updated");
  				 
				}
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//ADD BORROWERS
		public void addBorrowers(){
			System.out.println("Enter the Card Number");
			 int borrCard = index.scan.nextInt();
			 System.out.println("Enter the borrower name");
			 String borrName = index.scan.next();
			 System.out.println("Enter the borrower address");
			 String borrAddr = index.scan.next();
			 System.out.println("Enter the borrower phone number");
			 String borrPhone = index.scan.next();
			 
			 try {
				index.stmt = index.conn.prepareStatement("insert into tbl_borrower(cardNo, name, address,phone) values(?, ?, ?,?)");
			
			 index.stmt.setInt(1, borrCard);
			 index.stmt.setString(2, borrName);
			 index.stmt.setString(3, borrAddr);
			 index.stmt.setString(4, borrPhone);
			 index.stmt.executeUpdate();
  			 System.out.println(" Borrower details are Added");
			 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		//DELETE BORROWERS
		public void deleteBorrowers(){
			 
			 System.out.println("Enter the borrower name to be deleted");
			 String dBorrName = index.scan.next();
			 
			 try {
				index.stmt = index.conn.prepareStatement("delete from tbl_borrower where name=? ");
			
			 index.stmt.setString(1, dBorrName);
			 index.stmt.executeUpdate();
  			 System.out.println("The borrower is deleted" );
			 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		//UPDATE BORROWERS
		public void  updateBorrowers(){
			System.out.println("Enter the card No");
			 int uCardno = index.scan.nextInt();
			 
			 try {
				index.stmt = index.conn.prepareStatement("select name,address,phone from tbl_borrower where cardNo=?");
			
			 index.stmt.setInt(1, uCardno);
			 index.rs = index.stmt.executeQuery();
  			 
  			 while(index.rs.next()){
  				System.out.println("borrower name : " + index.rs.getString("name"));
  				System.out.println("borrower Address  : " + index.rs.getString("address"));
  				System.out.println("borrower phone  : " + index.rs.getString("phone"));
  			 }
			 
  			System.out.println("Please enter new borrower name or enter N/A for no change: ");
  			String newBorrName = index.scan.next(); 
  			
  			if(!newBorrName.equals("N/A")){
  				 
  				index.stmt = index.conn.prepareStatement("update tbl_borrower set name =? where uCardno =? ");
  				index.stmt.setString(1, newBorrName);
  				index.stmt.setInt(2, uCardno);
  				index.stmt.executeUpdate();
  				 System.out.println("borrower phone updated");
  				 
  			 }
  			 
  			 
  			System.out.println("Please enter new borrower address or enter N/A for no change: ");
  			String newBorrAddr = index.scan.next(); 
  			
  			if(!newBorrAddr.equals("N/A")){
  				 
  				index.stmt = index.conn.prepareStatement("update tbl_borrower set address =? where uCardno =? ");
  				index.stmt.setString(1, newBorrAddr);
  				index.stmt.setInt(2, uCardno);
  				index.stmt.executeUpdate();
  				 System.out.println("borrower address updated");
  				 
  			 }
  			 
  			System.out.println("Please enter new borrower phone or enter N/A for no change: ");
  			String newBorrPhone = index.scan.next(); 
  			
  			if(!newBorrAddr.equals("N/A")){
  				 
  				index.stmt = index.conn.prepareStatement("update tbl_borrower set phone =? where uCardno =? ");
  				index.stmt.setString(1, newBorrPhone);
  				index.stmt.setInt(2, uCardno);
  				index.stmt.executeUpdate();
  				 System.out.println("borrower phone updated");
  				 
  			 }
			 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		
		
	
}
