package GUI;

import java.io.File;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SignUpGUI {
	Stage stage;
	protected Button Back = new Button();
	protected Scene MainScene;
	public SignUpGUI( Stage primaryStage , Scene s) {
		MainScene = s;
		stage = primaryStage;
		SignUpPage();
		//graph = new double[number + 1][number + 1];
	}
 public void SignUpPage(){
	// String path;
		
	    Group group = new Group();

		Scene scene = new Scene(group, 980, 630);

		GridPane gridPane = new GridPane();
		FillGUI(gridPane);
		AddFuncionality();
		AddImage( group);
		group.getChildren().add(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(2);
		gridPane.setPadding(new Insets(49, 49, 56, 190));

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setScene(scene);
				stage.show();
			}

		});
 }
 public void AddImage(Group group){
		File file = new File("books2.jpg");
		Image background = new Image(file.toURI().toString());
     ImageView img = new ImageView(background);
     img.setPreserveRatio(true);
     group.getChildren().add(img);
	}
 public void FillGUI(GridPane gp){
		Label UName = new Label("User Name: ");
		UName.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(UName, 1, 2);

		Label UPass = new Label("Password: ");
		UPass.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(UPass, 1, 5);
		
		Label FName = new Label("First Name: ");
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
		
		TextField UNameTf = new TextField();
		gp.add(UNameTf, 3, 2);
		
		TextField UPassTf = new TextField();
		gp.add(UPassTf, 3, 5);
		
		TextField UFNameTf = new TextField();
		gp.add(UFNameTf, 3, 8);
		
		TextField ULNameTf = new TextField();
		gp.add(ULNameTf, 3, 11);
		
		TextField EmailTf = new TextField();
		gp.add(EmailTf, 3, 14);
		
		TextField AddressTf = new TextField();
		gp.add(AddressTf, 3, 17);
		
		Button Register = new Button();
		Register.setText("Register");
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
 
	public void AddFuncionality(){
		
		Back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(MainScene);
				stage.show();
			}
		});
		
	}
}