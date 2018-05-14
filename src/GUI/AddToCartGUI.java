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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddToCartGUI {
	private RadioButton ISBN = new RadioButton("ISBN");
	private TextField ISBNTf = new TextField();
	private RadioButton Quantity = new RadioButton("Quantity");
	private TextField QuantityTf = new TextField();
	private Button AddToCart = new Button();
	private Button ViewCart = new Button();
	private Button Back = new Button();
	private Scene cartScene;
	private Stage stage;
	private Scene addToCart;
	private DBMaster dbm;

	public AddToCartGUI( Stage primaryStage, Scene s) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		stage = primaryStage;
		cartScene = s;
		CartPage();
	}
	
	private void CartPage(){
		Group group = new Group();
		addToCart = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		FillGUI(gridPane);
		
		//Decide what to do with each btn click
		AddFunctionality();
		AddImage(group);
		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(49, 49, 56, 280));
		gridPane.setAlignment(Pos.CENTER);
		RadioBtnFunctionality();
		gridPane.setVgap(35);
		gridPane.setHgap(7);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("here");

				stage.setScene(addToCart);
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
		
	
		
		ISBN.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(ISBN, 1, 2);
		
		Quantity.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Quantity, 1, 3);
		
		ISBNTf.setVisible(false);
		gp.add(ISBNTf, 3, 2);
		
		QuantityTf.setVisible(false);
		gp.add(QuantityTf, 3, 3);
		
		AddToCart.setText("Add To Cart");
		AddToCart.setPrefSize(200, 35);
		gp.add(AddToCart, 1, 4);
		AddToCart.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		
		ViewCart.setText("View Cart");
		AddToCart.setPrefSize(200, 35);
		gp.add(ViewCart, 1, 5);
		ViewCart.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 6);
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

	}
	
	
	private void RadioBtnFunctionality(){
		
		ISBN.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(ISBN.isSelected()){
	        		ISBNTf.setVisible(true);
	            }
	            else{
	            	ISBNTf.clear();
	        		ISBNTf.setVisible(false);
	            }
	        }
	    });
		Quantity.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Quantity.isSelected()){
	            	QuantityTf.setVisible(true);
	            }
	            else{
	            	QuantityTf.clear();
	            	QuantityTf.setVisible(false);
	            }
	        }
	    });

	}
	 int x =0;
	private void AddFunctionality(){
		
		Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(cartScene);
				stage.show();
			}
		});
		AddToCart.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(!ISBNTf.getText().trim().isEmpty() && !QuantityTf.getText().trim().isEmpty()){
					
						try {
							x = dbm.addInCart(ISBNTf.getText().trim(), Integer.valueOf(QuantityTf.getText().trim()));
						} catch (NumberFormatException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(x==0){
							showAlert("Add Success","Book Added To Cart");
						}
						else if(x==-1){
							showAlert("Add Failed","not found quantity");
						}
						else if(x==-2){
							showAlert("Add Failed","not found ISBN");
						}
						
					 }
				else {
					showAlert("Add Failed","Please Fill All Attributes");

				}
				//AddToCartGUI add = new AddToCartGUI(stage , cartScene);
			}
		});
		ViewCart.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					try {
						CartViewerGUI StartCart = new CartViewerGUI(stage , cartScene);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
