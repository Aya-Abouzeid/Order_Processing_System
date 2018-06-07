package GUI;

import java.io.File;
import java.sql.SQLException;

import Library.DBMaster;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CartGUI {
	private Label ISBN = new Label("       ISBN :");
	private TextField ISBNTf = new TextField();
	private Button View = new Button();
	private Button Remove = new Button();
	private Button Price = new Button();
	private Button checkOut = new Button();
	private Button TotalPrice = new Button();
	private DBMaster dbm;
	private Button Back = new Button();
	private Scene CustomerScene;
	private Stage stage;
	private Scene cartScene;
	private Label bookPrice = new Label();
	private Label totalPriceLabel = new Label();

	public CartGUI( Stage primaryStage, Scene s) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		stage = primaryStage;
		CustomerScene = s;
		CartPage();
	}
	
	private void CartPage(){
		
		Group group = new Group();
		cartScene = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		FillGUI(gridPane);
		
		//Decide what to do with each btn click
		AddFunctionality();
		AddImage(group);
		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(49, 49, 56, 185));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(35);
		gridPane.setHgap(7);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(cartScene);
				stage.show();
			}

		});
 }
	private void AddImage(Group group){
		File file = new File("books2.jpg");
		Image background = new Image(file.toURI().toString());
        ImageView img = new ImageView(background);
        img.setPreserveRatio(true);
        group.getChildren().add(img);
	}
	private void FillGUI(GridPane gp){
		
		View.setText("View Cart");
		View.setPrefSize(300, 35);
		gp.add(View, 2, 1);
		View.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		
		TotalPrice.setText("View Total Price");
		TotalPrice.setPrefSize(300, 35);
		gp.add(TotalPrice, 2, 2);
		TotalPrice.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		
		totalPriceLabel.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(totalPriceLabel, 3, 2);
		
		ISBN.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(ISBN, 1, 4);
		
		gp.add(ISBNTf, 2, 4);
		Price.setText("View Book Price");
		Price.setPrefSize(300, 35);
		gp.add(Price, 2, 5);
		Price.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		
		bookPrice.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(bookPrice, 3, 5);
		
		Remove.setText("Remove Book");
		Remove.setPrefSize(300, 35);
		gp.add(Remove, 2, 6);
		Remove.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		
	
		Back.setText("< Back");
		Back.setPrefSize(160, 35);
		gp.add(Back, 1, 7);
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		checkOut.setText("Check Out");
		checkOut.setPrefSize(160, 35);
		gp.add(checkOut, 3, 7);
		checkOut.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

	}
	


	private void AddFunctionality(){
	
	Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			stage.setScene(CustomerScene);
			stage.show();
		}
	});
	checkOut.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			try {
				CheckoutGUI checkout = new CheckoutGUI(stage , cartScene);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	// validate existance of book
	Price.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if(!ISBNTf.getText().trim().isEmpty()){
			try {
				double price = dbm.getItemPrice(ISBNTf.getText());
				if(price == -1){
				showAlert("Book Missing" , "Book Doesn't Exist In Cart");

				}
				else
				bookPrice.setText("         "+ String.valueOf(price));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}else{
				showAlert("ISBN Missing" , "Please Enter The Required ISBN");
			}
		}
	});
	View.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent arg0) {
			totalPriceLabel.setText("");
			try {
				AddToCartGUI add = new AddToCartGUI(stage , cartScene);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	Remove.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			if(!ISBNTf.getText().trim().isEmpty()){
				try {
					int x = dbm.removeFromCart(ISBNTf.getText());
					if(x!=-1)
					showAlert("Success" , "BOOK Removed !");
					else
					showAlert("Doesn't exist" , "ISBN Doesn't exist in Cart!");
						
					ISBNTf.setText("");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				showAlert("ISBN Missing" , "Please Enter The Required ISBN");
			}
		}
	});
	TotalPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
				try {
					double price = dbm.getTotalPrice();
					totalPriceLabel.setText("         "+String.valueOf(price));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	});
	
}
	private void showAlert(String title , String msg){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		alert.show();
	}
}
