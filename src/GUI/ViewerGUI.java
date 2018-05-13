package GUI;

import java.awt.Font;
import java.awt.Insets;
import Library.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale.Category;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Library.DBMaster;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewerGUI {
	private DBMaster dbm;
	private Scene MainScene;
	private Stage stage;
	private ResultSet resultSet;
    private TableView<String> table = new TableView();
    ObservableList<Book> data ;

        //private Label label;
       private TableView<Book> tableBook = new TableView() ;  
       private TableColumn<Book, String> columnTitle = new TableColumn("Title");  
      private TableColumn<Book, Date> columnYear  = new TableColumn("Year"); ;  
       private TableColumn<Book, String> columnISBN  = new TableColumn("ISBN"); ;
       private TableColumn<Book, Integer> columnPublisherID = new TableColumn("Publisher ID"); ;
       private TableColumn<Book, Integer> columnPrice = new TableColumn("Price"); ;
       private TableColumn<Book, Integer> columnQuantity = new TableColumn("Quantity"); ;
       private TableColumn<Book, Integer> columnThreshold = new TableColumn("Threshold"); ;
       private TableColumn<Book, Category> columnCategory = new TableColumn("Category"); ;

	public ViewerGUI( Stage primaryStage , Scene s , ResultSet x ) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		MainScene = s;
		resultSet = x;


		 stage = primaryStage;
		 trial();
		// ViewerPage();
	}
	public void trial() throws SQLException
	{
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();
		data = FXCollections.observableArrayList();
		columnISBN.setCellValueFactory(
			    new PropertyValueFactory<Book,String>("isbn"));
		columnTitle.setCellValueFactory(
			    new PropertyValueFactory<Book,String>("Title"));
		
		columnPublisherID.setCellValueFactory(
			    new PropertyValueFactory<Book,Integer>("publisherId"));
		columnPrice.setCellValueFactory(
			    new PropertyValueFactory<Book,Integer>("SellingPrice"));
		
		columnQuantity.setCellValueFactory(
			    new PropertyValueFactory<Book,Integer>("StockQuantity"));
		columnThreshold.setCellValueFactory(
			    new PropertyValueFactory<Book,Integer>("Threshold"));
		columnYear.setCellValueFactory(
			    new PropertyValueFactory<Book,Date>("Year"));
		columnCategory.setCellValueFactory(
			    new PropertyValueFactory<Book,Category>("category"));
		
		tableBook.autosize();
		
		tableBook.getColumns().addAll( columnISBN,columnTitle,columnCategory, columnPublisherID,columnYear,columnPrice,
        		columnQuantity,columnThreshold);
		
		

	        while(resultSet.next()){
	        	Book b = new Book();
	        	data.add(
	        			new Book(
	        			resultSet.getString("Title"),
	        			resultSet.getString("ISBN"),
	        			resultSet.getInt("PID"),
	        			resultSet.getInt("Stock"),
	        			resultSet.getDouble("Price"),
	        			resultSet.getInt("Threshold"),
	        			resultSet.getString("Category"),
	        			resultSet.getDate("Year")
	        			
	        			));
	        			
	        	
	
	        	
	        }

        
        tableBook.setItems(null);

        tableBook.setItems(data);
        

        final VBox vbox = new VBox();
        vbox.getChildren().add(tableBook);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
 
       
        Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}
	});
	}
	/*public void ViewerPage(ResultSet rs){
		TableColumn firstNameCol = new TableColumn("ISBN");
        TableColumn lastNameCol = new TableColumn("PID");
       // TableColumn emailCol = new TableColumn("Email");
        
        table.getColumns().addAll(firstNameCol, lastNameCol);
        
		resultSet = rs;
	}*/

}
