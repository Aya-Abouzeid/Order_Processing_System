package GUI;

import java.io.File;
import java.time.LocalDate;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckoutGUI {
	
	private Button Back = new Button();
	private Scene cartScene;
	private Scene checkOutScene;
	private Stage stage;
	private TextField creditCardTF = new TextField();
	private TextField dateTF = new TextField();
	private Button confirmPurchase = new Button();
	private DatePicker datePicker = new DatePicker();

	
	public CheckoutGUI( Stage primaryStage, Scene s) {
		stage = primaryStage;
		cartScene = s;
		CartPage();
	}
	
	private void CartPage(){
		
		Group group = new Group();
		checkOutScene = new Scene(group, 980, 630);
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		fillGUI(gridPane);
		
		//Decide what to do with each btn click
		addFunctionality();
		addImage(group);
		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(20, 20, 56, 100));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(35);
		gridPane.setHgap(7);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(checkOutScene);
				stage.show();
			}

		});
 }
	private void addImage(Group group){
		File file = new File("books2.jpg");
		Image background = new Image(file.toURI().toString());
        ImageView img = new ImageView(background);
        img.setPreserveRatio(true);
        group.getChildren().add(img);
	}
	private void fillGUI(GridPane gp){
		
		gp.add(creditCardTF, 4, 4);
		
		
		Label credit = new Label("Credit Card Number: ");
		credit.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(credit, 2, 4);
		
		Label date  = new Label("Expiration Date: ");
		date.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(date, 2, 7);
		gp.add(datePicker, 4, 7);

		confirmPurchase.setText("Confirm Purchase");
		confirmPurchase.setPrefSize(300, 35);
		gp.add(confirmPurchase, 4, 10);
		confirmPurchase.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 10);
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
	
	}
	private void addFunctionality(){
		
		datePicker.setOnAction(event -> {
		    LocalDate date = datePicker.getValue();
		    System.out.println("Selected date: " + date);
		});
		Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(cartScene);
				stage.show();
			}
		});
		confirmPurchase.setOnMouseClicked(new EventHandler<MouseEvent>() {

		    LocalDate date = datePicker.getValue();

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
	}
}
