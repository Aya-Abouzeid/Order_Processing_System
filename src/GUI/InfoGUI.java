package GUI;

import java.io.File;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InfoGUI {
	protected Button Back = new Button();
	protected Scene MainScene;
	protected Scene CustomerScene;
	protected Stage stage;
	protected RadioButton UName = new RadioButton("User Name");
	protected TextField UNameTf = new TextField();
	protected TextField UPassTf = new TextField();
	protected TextField UFNameTf = new TextField();
	protected TextField ULNameTf = new TextField();
	protected TextField EmailTf = new TextField();
	protected TextField AddressTf = new TextField();


	public InfoGUI( Stage primaryStage, Scene s) {
		stage = primaryStage;
		MainScene = s;
		InfoPage();
	}
	
	public void InfoPage(){
		
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		CustomerScene = scene;
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		FillGUI(gridPane);
		
		//Decide what to do with each btn click
		AddFunctionality();
		RadioBtnFunctionality();
		AddImage(group);
		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(49, 49, 56, 280));
		gridPane.setAlignment(Pos.CENTER);

		gridPane.setVgap(10);
		gridPane.setHgap(2);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}

		});
 }
	public void AddImage(Group group){
		File file = new File("Books2.jpg");
		Image background = new Image(file.toURI().toString());
        ImageView img = new ImageView(background);
        img.setPreserveRatio(true);
        group.getChildren().add(img);
	}
	
	public void FillGUI(GridPane gp){
		

		UName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(UName, 1, 2);

		RadioButton UPass = new RadioButton("Password");
		UPass.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(UPass, 1, 5);
		
		RadioButton FName = new RadioButton("First Name");
		FName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(FName, 1, 8);
		
		Label LName = new Label("Last Name: ");
		LName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(LName, 1, 11);
		
		Label Email = new Label("Email: ");
		Email.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Email, 1, 14);
		
		Label Address = new Label("Shipping Address: ");
		Address.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Address, 1, 17);
		
		gp.add(UNameTf, 3, 2);
		
		gp.add(UPassTf, 3, 5);
		

		gp.add(UFNameTf, 3, 8);
		
		gp.add(ULNameTf, 3, 11);
		
		gp.add(EmailTf, 3, 14);
		
		gp.add(AddressTf, 3, 17);
		
		Button Register = new Button();
		Register.setText("Update Info");
		Register.setPrefSize(140, 35);
		gp.add(Register, 3, 20);
		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 23);
		
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Register.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		gp.setAlignment(Pos.BOTTOM_CENTER);
		gp.setPadding(new Insets(49, 49, 56, 280));

	}
	public void RadioBtnFunctionality(){
		
		UName.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(!UName.isSelected()){
	            	
	            }
	            else{
	            	
	            }
	        }
	    });
	}
	public void AddFunctionality(){
		
		Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(MainScene);
				stage.show();
			}
		});
	}

}
