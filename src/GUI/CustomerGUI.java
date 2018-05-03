package GUI;

import java.io.File;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerGUI {
	protected Button Edit = new Button();
	protected Button Search = new Button();
	protected Button Cart = new Button();
	protected Button Logout = new Button();
	protected Stage stage;
	protected Scene MainScene;
	protected Scene CustomerScene;

	public CustomerGUI( Stage primaryStage, Scene s) {
		stage = primaryStage;
		MainScene = s;
		CustomerPage();
	}
	public void CustomerPage(){
			
			Group group = new Group();
			Scene scene = new Scene(group, 980, 630);
			CustomerScene = scene;
			GridPane gridPane = new GridPane();
			
			//Add btns to GUI
			FillGUI(gridPane);
			
			//Decide what to do with each btn click
			AddFunctionality();
			AddImage(group);
			group.getChildren().add(gridPane);
			gridPane.setPadding(new Insets(49, 49, 56, 280));
			gridPane.setAlignment(Pos.CENTER);

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
	public void AddImage(Group group){
		File file = new File("Library2.jpg");
		Image background = new Image(file.toURI().toString());
        ImageView img = new ImageView(background);
        img.setPreserveRatio(true);
        img.setFitWidth(1000);
        img.setFitHeight(1000);
        group.getChildren().add(img);
	}
	public void FillGUI(GridPane gp){

		Edit.setText("Edit Personal Info");
		Edit.setPrefSize(230, 50);

		Search.setText("Search For Book");
		Search.setPrefSize(230, 35);
		
		Cart.setText("Shopping Cart");
		Cart.setPrefSize(230, 35);
		
		Logout.setText("Logout");
		Logout.setPrefSize(230, 35);
		
		gp.add(Edit, 15, 1);
		gp.add(Search, 15, 3);
		gp.add(Cart, 15, 5);
		gp.add(Logout, 15, 7);

		Edit.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Search.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Cart.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Logout.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
}
	
	public void AddFunctionality(){
		
		//User Clicked Logout Btn
		Logout.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				stage.setScene(MainScene);
				stage.show();
			}
		});
		
		//User Clicked Logout Btn
		Edit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					InfoGUI StartLogin = new InfoGUI(stage , CustomerScene);
			}
		});
		
		//User Clicked Cart Btn
				Cart.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						
							CartGUI StartLogin = new CartGUI(stage , CustomerScene);
					}
				});
	}
}