package GUI;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerGUI {
	Stage stage;
	public CustomerGUI( Stage primaryStage) {
		
		stage = primaryStage;
		CustomerPage();

	}
	public void CustomerPage(){
		// String path;
			
		 Group group = new Group();

			Scene scene = new Scene(group, 980, 630);

			GridPane gridPane = new GridPane();
			FillGUI(gridPane);

			group.getChildren().add(gridPane);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					stage.setScene(scene);
					stage.show();
				}

			});
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
		
		gp.setAlignment(Pos.BOTTOM_CENTER);
		gp.setPadding(new Insets(49, 49, 56, 280));

}
}