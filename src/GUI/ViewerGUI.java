package GUI;

import java.awt.Font;
import java.awt.Insets;
import Library.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale.Category;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import Library.DBMaster;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewerGUI {
	private DBMaster dbm;
	private Scene MainScene;
	private Stage stage;
	private ResultSet resultSet;
    private TableView<String> table = new TableView();
	private Button Back = new Button();
	private Button Forward = new Button();
	private ArrayList<ObservableList<Book>> allData = new ArrayList<ObservableList<Book>>();
    ObservableList<Book> data ;
    private int pagesCounter=0;
    private int pagesOffset=0;

        //private Label label;
       private TableView<Book> tableBook = new TableView() ;  
       private TableColumn<Book, String> columnTitle = new TableColumn("Title");  
      private TableColumn<Book, Date> columnYear  = new TableColumn("Year"); ;  
       private TableColumn<Book, String> columnISBN  = new TableColumn("ISBN"); ;
       private TableColumn<Book, Integer> columnPublisherID = new TableColumn("Publisher ID"); ;
       private TableColumn<Book, Double> columnPrice = new TableColumn("Price"); ;
       private TableColumn<Book, Integer> columnQuantity = new TableColumn("Quantity"); ;
       private TableColumn<Book, Integer> columnThreshold = new TableColumn("Threshold"); ;
       private TableColumn<Book, String> columnCategory = new TableColumn("Category"); ;
       Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();

	public ViewerGUI( Stage primaryStage , Scene s , ResultSet x ) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		MainScene = s;
		resultSet = x;

		addFunctionality();
		 stage = primaryStage;
		 trial();
		// ViewerPage();
	}
	public void trial() throws SQLException
	{
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		Forward.setText("Forward >");
		Forward.setPrefSize(119, 35);
		//gridPane.add(Back, 15, 23);

		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Forward.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		data = FXCollections.observableArrayList();
		columnISBN.setCellValueFactory(
			    new PropertyValueFactory<Book,String>("isbn"));
		columnTitle.setCellValueFactory(
			    new PropertyValueFactory<Book,String>("Title"));
		
		columnPublisherID.setCellValueFactory(
			    new PropertyValueFactory<Book,Integer>("publisherId"));
		columnPrice.setCellValueFactory(
			    new PropertyValueFactory<Book,Double>("SellingPrice"));
		
		columnQuantity.setCellValueFactory(
			    new PropertyValueFactory<Book,Integer>("StockQuantity"));
		columnThreshold.setCellValueFactory(
			    new PropertyValueFactory<Book,Integer>("Threshold"));
		columnYear.setCellValueFactory(
			    new PropertyValueFactory<Book,Date>("Year"));
		columnCategory.setCellValueFactory(
			    new PropertyValueFactory<Book,String>("category"));
		
		tableBook.autosize();
		
		tableBook.getColumns().addAll( columnISBN,columnTitle,columnCategory, columnPublisherID,columnYear,columnPrice,
        		columnQuantity,columnThreshold);

	    final VBox vbox = new VBox();
	    vbox.getChildren().add(tableBook);
	    vbox.getChildren().add(Back);
	    vbox.getChildren().add(Forward);

	    ((Group) scene.getRoot()).getChildren().addAll(vbox);
		
		show();
		
 
       
        Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}
	});
	}

	private void show() throws SQLException{
		pagesOffset=0;
		 if(pagesCounter < allData.size()){
			   tableBook.setItems(allData.get(pagesCounter));
			   pagesCounter++;
		 }
		 else{
			 data = FXCollections.observableArrayList();
				boolean hasNext = resultSet.next();
				while(hasNext){
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
	        			resultSet.getDate("Year").toString()
	        			));	
	        	pagesOffset++;
	        	if(pagesOffset==10)
	        		break;
	        	hasNext = resultSet.next();
	        }
				if(data.size()!=0){
					allData.add(data);
					pagesCounter++;
				}
				
			 
		    tableBook.setItems(null);

		    tableBook.setItems(data);			 
		 }
			
	    

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}
	});
		
	}
		
		
  private void showPrevious(){
	  pagesCounter--;
	   tableBook.setItems(null);
	   tableBook.setItems(allData.get(pagesCounter));
	    
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}
	});	  
  }
void addFunctionality(){
	Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if(pagesCounter==0){
				stage.setScene(MainScene);
				stage.show();
			}
			else{
				showPrevious();
			}
			
		}
	});
	Forward.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			try {
				show();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
}

}
