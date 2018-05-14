package GUI;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale.Category;

import Library.CartItem;
import Library.DBMaster;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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

public class CartViewerGUI {
	private DBMaster dbm;
	private Scene MainScene;
	private Stage stage;
	private ResultSet resultSet;
    private TableView<String> table = new TableView();
	private Button Back = new Button();
    ObservableList<CartItem> data ;


       private TableView<CartItem> tableBook = new TableView() ;  
       private TableColumn<CartItem, String> columnISBN = new TableColumn("ISBN");  
      private TableColumn<CartItem, Double> columnPrice  = new TableColumn("Price"); ;  
       private TableColumn<CartItem, Integer> columnQuantity  = new TableColumn("Quantity"); ;

       Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();

	public CartViewerGUI( Stage primaryStage , Scene s ) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		MainScene = s;

		addFunctionality();
		 stage = primaryStage;
		 trial();
		// ViewerPage();
	}
	public void trial() throws SQLException
	{
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
	
		//gridPane.add(Back, 15, 23);

		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		data = FXCollections.observableArrayList();
		columnISBN.setCellValueFactory(
			    new PropertyValueFactory<CartItem,String>("isbn"));

		columnPrice.setCellValueFactory(
			    new PropertyValueFactory<CartItem,Double>("price"));
		
		
		columnQuantity.setCellValueFactory(
			    new PropertyValueFactory<CartItem,Integer>("Quantity"));
		
		tableBook.autosize();
		
		tableBook.getColumns().addAll( columnISBN,columnPrice,columnQuantity);

	    final VBox vbox = new VBox();
	    vbox.getChildren().add(tableBook);
	    vbox.getChildren().add(Back);

	    ((Group) scene.getRoot()).getChildren().addAll(vbox);
		
		show();
		
	}

	private void show() throws SQLException{
			ArrayList items = (ArrayList) dbm.getCart();
			for(int i = 0 ; i < items.size(); i++)
				data.add((CartItem)items.get(i));
			
		    tableBook.setItems(null);
		    tableBook.setItems(data);			 
		 
	    

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
				stage.setScene(MainScene);
				stage.show();
						
		}
	});
	
}

}
