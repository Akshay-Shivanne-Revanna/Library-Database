
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class index{
	
	
	public static Connection conn = null;
	public static PreparedStatement stmt = null;
	public static ResultSet rs =null;
	public static ResultSet rs2 =null;
	public static int newCount,option,branchChoice,cardNum,borrOption,bookId,noOfCopie,libOption,noOfCopies,branchChoice2,bookChoice,bid,adminOption,pubOption,libBranchOption,adminBorrOption;
	public static String branchName,branchAddress,title,libBranch,branchChoosed,bookTitle,branchCh,btitle;
	public static Scanner scan;
	
	public static void main(String[] args){
		
		
		Librarian lib = new Librarian();
		Borrower br = new Borrower();
		Administrator admin = new Administrator();
		scan = new Scanner(System.in);
		int choice;
		
		try{
			
			//connection to database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","password");
						
		
			do{
				System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you");
				System.out.println();
				System.out.println("1)Librarian");
				System.out.println("2)Administrator");
				System.out.println("3)Borrower");
				
				
				choice = scan.nextInt();
				
				switch(choice){
				
				case 1: {
						
							do{  
								 System.out.println(" --------------------------------------------- ");
								 System.out.println("LIB1:");
								 System.out.println();
								 System.out.println("1) Enter Branch you manage");
								 System.out.println("2) Quit to previous ");
								 option = scan.nextInt();
								 int count;
								 switch(option){
								 
								 	case 1 :
								 			int count2;
								 			int branchOption;
								 		    do{
								 		     System.out.println(" ----------------------LIBRARY BRANCHES----------------------- ");
								 			 System.out.println("LIB2:");
								 			 System.out.println();
								 			 
								 			 //LIST THE BRANCH
								 			 lib.listLibrarybranch();
								 			 								 			 
								 			 branchChoice = scan.nextInt();
											 
											 //CHOOSE THE BRANCH
								 			 branchOption = lib.selectBranch(branchChoice);
								 			 
								 			 
								 			 	if(branchChoice < (branchOption )){
								 			 		do{
								 			 			System.out.println(" ----------------------LIBRARIAN OPTIONS---------------------- ");
								 			 			System.out.println("LIB3:");
								 			 			System.out.println();
								 			 			System.out.println("1)	Update the details of the Library ");
								 			 			System.out.println("2)	Add copies of Book to the Branch");
								 			 			System.out.println("3)	Quit to previous ");
								 			 			libOption = scan.nextInt();
									 				 
								 			 			switch(libOption){
									 				 
										 				 	case 1 : System.out.println(" ------------------------UPDATE LIBRARY-------------------- ");
										 				 			 System.out.println("Update the details of the Library");
										 				 			 String libBranch = branchCh;
										 				 			 								 				 			 
										 				 			 //Data from the specified branch
										 				 			 lib.branchDetails();
										 				 			 //update branch name
										 				 			 lib.updateLibraryDetails();
													    	   		 
													    	   		 break;
									 				 			 
										 				 	case 2 : 
										 				 			do {
										 				 				System.out.println(" ------------------------ADD COPIES--------------------- ");
										 				 				lib.addBookCopies();
								 				 			         	break;
										 				 			}while(true);
										 				 			break;
								 				 			        								 				 			 
										 				 	case 3 : break;
									 				 
								 			 			}
									 				 
								 			 		}while(libOption!=3);
								 			 	}else if (branchChoice == branchOption){
								 			 		break;
								 			 	}else {
								 				System.out.println("invalid option");
								 			 	}
								 			 
								 		    }while(branchChoice!= branchOption+1);
								 			break;
								 			 
								 			 
								 	case 2: System.out.println("----------------------------------------------");
								 			break;	
								 			 
								 }
															
							  }while(option!=2);
							break;
					
						}
				
				
				
				case 2 : {
							do{
								System.out.println("----------------ADMIN OPTIONS----------");
								System.out.println("1)Publishers");
								System.out.println("2)Library Branches");
								System.out.println("3)Borrowers");
								System.out.println("4)Author");
								System.out.println("5)Book ");
								System.out.println("6)Update Due date");
								System.out.println("7) Exit" );
								
								adminOption = scan.nextInt();
								
								switch(adminOption){
								
								
								 case 1 : 
									 	do{
									      System.out.println("-------ADMIN ON PUBLISHER -----");
								 		  System.out.println();
								 		  System.out.println("1) Add to Publishers");
								 		  System.out.println("2) Delete on Publishers");
								 		  System.out.println("3) Update to Publishers");
								 		  System.out.println("4) Exit");
								 		  
								 		  pubOption = scan.nextInt();
								 		  
								 		 
								 		  switch(pubOption){
								 		  	
								 		  	case 1 : System.out.println("----------Add to Publishers --------");
								 		  			 System.out.println();
								 		  			 admin.addToPublisher();
								 		  			 break;
								 		  			 
								 		  	case 2 : System.out.println("----------Delete Operation-----------");
								 		  			 System.out.println();
								 		  			 admin.deletePublisher();
								 		  			 break;
								 		  			
								 		  	case 3 : System.out.println("----------Update Operation-----------");
								 		  			 System.out.println();
								 		  			 admin.updatePublisher();
								 		  			 break;
								 		  			 
								 		  	case 4 : break;
								 		  	
								 		  	default :System.out.println("Invalid Option");
								 		  			 break;
															 		  
								 		  }
								 		 }while(pubOption!=4);  
								 		  
								 		  break;
								 
								 case 2 : do{
									 	  	System.out.println("-------ADMIN ON LIBRARY BRANCHES-------");
									 	  	System.out.println();
									 	  	System.out.println("1) Add to LIBRARY BRANCHES");
									 	  	System.out.println("2) Delete on LIBRARY BRANCHES");
									 	  	System.out.println("3) Update to LIBRARY BRANCHES");
									 	  	System.out.println("4) Exit");
								 		  
									 	  	libBranchOption = scan.nextInt();
								 		  
								 		 	switch(libBranchOption){
								 		 		
								 		 		case 1 : System.out.println("----------Add to LIBRARY BRANCHES --------");
								 		 				 System.out.println();
								 		 				 admin.addLibraryBranches();
								 		 				 break;
						 		  			 
								 		 		case 2 : System.out.println("----------Delete LIBRARY BRANCHES-----------");
								 		 				 System.out.println();
								 		 				 admin.deleteLibraryBranch();
								 		 				
									 		  			 break;
								 		 				
								 		 		case 3 : System.out.println("----------Update LIBRARY BRANCHES-----------");
								 		 				 System.out.println();
								 		 				 admin.updateLibraryBranch();
								 		 				 break;
								 		 				
								 		 		case 4 : break;
						 		  	
								 		 		default :System.out.println("Invalid Option");
								 		 				 break;
								 		 
								 		 	
								 		 	}
								 		 }while(libBranchOption!=4);
								 		  break;
								 		  
								 case 3 : do{	
									 		System.out.println("-------ADMIN ON BORR0WERS ------------");
									 		System.out.println();
									 		System.out.println("1) Add to BORR0WERS");
									 		System.out.println("2) Delete on BORR0WERS");
									 		System.out.println("3) Update to BORR0WERS");
									 		System.out.println("4) Exit");
						 		  
									 		adminBorrOption = scan.nextInt();
						 		  
									 		switch(adminBorrOption){
									 			
									 			case 1 : System.out.println("----------Add to BORR0WERS  --------");
									 					 System.out.println();
									 					 admin.addBorrowers();
									 					 break;
			 		  			 
									 			case 2 : System.out.println("----------Delete BORR0WER -----------");
									 					 System.out.println();
									 					 admin.deleteBorrowers();
									 					 break;
									 					
			 		  			 
									 			case 3 : System.out.println("----------Update BORR0WER -----------");
									 					 System.out.println();
									 					 admin.updateBorrowers();
									 					 break;
			 		  			 
									 			case 4 : break;
			 		  	
									 			default :System.out.println("Invalid Option");
									 					 break;
									 		
									 		
									 		}
								 		 }while(adminBorrOption!=4);
								 
								 		  break;
								 		  
								 case 4 : do{	
								 			System.out.println("-------ADMIN ON AUTHORS ------------");
								 			System.out.println();
								 			System.out.println("1) Add to AUTHORS");
								 			System.out.println("2) Delete to AUTHORS");
								 			System.out.println("3) Update to AUTHORS");
								 			System.out.println("4) Exit");
					 		  
								 			adminBorrOption = scan.nextInt();
					 		  
								 			switch(adminBorrOption){
								 			
								 				case 1 : System.out.println("----------Add to AUTHORS  --------");
								 						 System.out.println();
								 						 admin.addAuthor();
								 						 break;
		 		  			 
								 				case 2 : System.out.println("----------Delete AUTHORS -----------");
								 						 System.out.println();
								 						 admin.deleteAuthor();
								 					 break;
								 					
		 		  			 
								 				case 3 : System.out.println("----------Update AUTHORS -----------");
								 						 System.out.println();
								 						 admin.updateAuthor();
								 					 break;
		 		  			 
								 				case 4 : break;
		 		  	
								 			default :System.out.println("Invalid Option");
								 					 break;
								 										 		
								 		}
							 		 }while(adminBorrOption!=4);
								  		  break;
								 		  
								case 5 : do{	
							 				System.out.println("-------ADMIN ON BOOKS ------------");
							 				System.out.println();
							 				System.out.println("1) Add to BOOKS");
							 				System.out.println("2) Delete to BOOKS");
							 				System.out.println("3) Update to BOOKS");
							 				System.out.println("4) Exit");
				 		  
							 				adminBorrOption = scan.nextInt();
				 		  
							 				switch(adminBorrOption){
							 			
							 					case 1 : System.out.println("----------Add to BOOKS  --------");
							 							 System.out.println();
							 							 admin.addBook();
							 							 break;
	 		  			 
							 					case 2 : System.out.println("----------Delete BOOKS -----------");
							 						 	 System.out.println();
							 						 	 admin.updateBook();
							 						 	 break;
							 					
	 		  			 
							 					case 3 : System.out.println("----------Update BOOKS -----------");
							 							 System.out.println();
							 							 admin.deleteBook();
							 							 break;
	 		  			 
							 				case 4 : break;
	 		  	
							 				default :System.out.println("Invalid Option");
							 					 break;
							 										 		
							 					}
								 			}while(adminBorrOption!=4);
								 			break;
								  		  
								 case 6 : System.out.println("---------UPDATE DUE DATE-----------");
								 		  System.out.println();
								 		  admin.updateDueDate();
								 		  break;
								 		  
								 case 7 : break;
							 											
								}
								
							}while(adminOption!=7);
							break;
						
				
				
						 }
				
						
				case 3 : { 
					       
					    	 do {
					    	   			System.out.println("----------------------------------------------");
					    	   			System.out.println("Enter the your Card Number:");
					    	   			cardNum = scan.nextInt();
					    	   			stmt = conn.prepareStatement("select cardNo from tbl_borrower where cardNo =?");
					    	   			stmt.setInt(1,cardNum);
					    	   			
					    	   			rs = stmt.executeQuery();
					    	   			if(!rs.next()){
					    	   				System.out.println("Invalid PIN");
					    	   				
					    	   			}else{
					    	   				break;
					    	   			}
					    	   			
					    	   		}while(true);
					    	   		
					    	   		do {
					    	   		System.out.println("----------------------BORROWER OPTIONS-----------------------");
					    	   		
					    	   		System.out.println("BORR1:");
					    	   		System.out.println("1) Check out a book");
					    	   		System.out.println("2) Return a Book");
					    	   		System.out.println("3) Quit to Previous ");
					    	   		
					    	   		borrOption = scan.nextInt();
					    	   		
					    	   		switch(borrOption){
					    	   			
					    	   			case 1 : //CHECK OUT THE BOOK
					    	   					   	System.out.println("------------------------BRANCHES----------------------");
					    	   						br.branchList();
					    	   						branchChoice2 = scan.nextInt();
					    	   						
					    	   						stmt = conn.prepareStatement("select branchName from tbl_library_branch");
					    	   						rs = stmt.executeQuery();
					    	   								   						
					    	   						newCount=1;
					    	   						while(rs.next()){
					    	   									    	   							
					    	   							if(branchChoice2 == newCount ){
					    	   								branchChoosed = rs.getString("branchName");
					    	   							}
					    	   		 	   				newCount++;
					    	   						}
					    	   						
					    	   						br.branchBooks(branchChoosed);
					    	   						bookChoice = scan.nextInt();
					    	   						//choosing right book
					    	   						br.bookChoosed(bookChoice);
					    	   						//inserting in the book loans
					    	   						br.bookLoans();
					    	   					 
					    	   					 break;
					    	   			
					    	   			case 2 : 
					    	   					 System.out.println("Return a Book");
					    	   					 br.branchList();
					    	   					 branchChoice2 = scan.nextInt();
					    	   				   	 br.branchId();
				    	   						 System.out.println("Enter the book Id :" );
					    	   					 bid = scan.nextInt();
				    	   						 br.bookReturn();
					    	   					 break;
					    	   					 
					    	   			case 3 : System.out.println("Quit to previous");
					    	   					 break;

					    	   		
					    	   		}
					    	   		
					    	   		
					       }while(borrOption!=3);
							break;
							
						 }
				
				
				}
				
				
				
			}while(true);
			
	
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
		
	}
	
}
