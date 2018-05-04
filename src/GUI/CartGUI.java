package GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CartGUI {
	private RadioButton ISBN = new RadioButton("ISBN");
	private TextField ISBNTf = new TextField();
	private RadioButton Quantity = new RadioButton("Quantity");
	private TextField QuantityTf = new TextField();
	private Button AddToCart = new Button();

	private Button Back = new Button();
	private Scene CustomerScene;
	private Stage stage;

	public CartGUI( Stage primaryStage, Scene s) {
		stage = primaryStage;
		CustomerScene = s;
		CartPage();
	}
	
	public void CartPage(){
		
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		FillGUI(gridPane);
		
		//Decide what to do with each btn click
		AddFunctionality();

		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(49, 49, 56, 280));
		gridPane.setAlignment(Pos.CENTER);
		RadioBtnFunctionality();
		gridPane.setVgap(35);
		gridPane.setHgap(7);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}

		});
 }
	public void FillGUI(GridPane gp){
		
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

		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 5);
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

	}
	
	
public void RadioBtnFunctionality(){
		
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
	public void AddFunctionality(){
		
		Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(CustomerScene);
				stage.show();
			}
		});
	}
}
