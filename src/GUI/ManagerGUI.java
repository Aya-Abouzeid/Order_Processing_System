package GUI;

import java.io.File;
import java.sql.SQLException;

import Library.DBMaster;
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

public class ManagerGUI {

	protected Button Edit = new Button();
	protected Button Search = new Button();
	protected Button Cart = new Button();
	protected Button Logout = new Button();
	protected Button viewTotalSalesThisMonth = new Button();
	protected Button topFiveCustomers = new Button();
	protected Button topTenSellingBooks = new Button();
	protected Button manageLibrary = new Button();
	protected Stage stage;
	protected Scene MainScene;
	protected Scene ManagerScene;
	private DBMaster dbm;

	public ManagerGUI( Stage primaryStage, Scene s) throws ClassNotFoundException, SQLException {
		dbm = DBMaster.getDBMaster();
		stage = primaryStage;
		MainScene = s;
		ManagerPage();
	}
	public void ManagerPage(){
			
			Group group = new Group();
			Scene scene = new Scene(group, 980, 630);
			ManagerScene = scene;
			GridPane gridPane = new GridPane();
			
			//Add btns to GUI
			FillGUI(gridPane);
			
			//Decide what to do with each btn click
			AddFunctionality();
			AddImage(group);
			group.getChildren().add(gridPane);
			gridPane.setPadding(new Insets(49, 49, 56, 150));
			gridPane.setAlignment(Pos.TOP_LEFT);

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
	private void AddImage(Group group){
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
		Edit.setPrefSize(300, 50);

		Search.setText("Search For Book");
		Search.setPrefSize(300, 35);
		
		Cart.setText("Shopping Cart");
		Cart.setPrefSize(300, 35);
		
		Logout.setText("Logout");
		Logout.setPrefSize(300, 35);
		
		viewTotalSalesThisMonth.setText("Total Sales");
		viewTotalSalesThisMonth.setPrefSize(300, 35);
		
		topFiveCustomers.setText("Top Five Customers");
		topFiveCustomers.setPrefSize(300, 35);
		
		topTenSellingBooks.setText("Top Ten Selling Books");
		topTenSellingBooks.setPrefSize(300, 35);
		
		manageLibrary.setText("Manage Library");
		manageLibrary.setPrefSize(300, 35);
		
		gp.add(Edit, 5, 1);
		gp.add(Search, 5, 3);
		gp.add(Cart, 5, 5);
		gp.add(Logout, 5, 7);
		gp.add(viewTotalSalesThisMonth, 10, 1);
		gp.add(topFiveCustomers, 10, 3);
		gp.add(topTenSellingBooks, 10, 5);
		gp.add(manageLibrary, 10, 7);
		

		Edit.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Search.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Cart.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Logout.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		viewTotalSalesThisMonth.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		topFiveCustomers.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		topTenSellingBooks.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		manageLibrary.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

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
		
		
		Edit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					try {
						InfoGUI StartLogin = new InfoGUI(stage , ManagerScene);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		manageLibrary.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					LibraryGUI manage = new LibraryGUI(stage , ManagerScene);
			}
		});
		//User Clicked Cart Btn
		Cart.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					try {
						CartGUI StartCart = new CartGUI(stage , ManagerScene);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
		Search.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
					try {
						SearchGUI StartSearch = new SearchGUI(stage , ManagerScene);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
	}
}
