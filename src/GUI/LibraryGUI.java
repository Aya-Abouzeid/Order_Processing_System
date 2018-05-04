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


public class LibraryGUI {
	Button addBook = new Button();
	Button modify = new Button();
	Button order = new Button();
	Button confirm = new Button();
	Button promote = new Button();


	Stage stage;
	Scene CustomerScene;
	public LibraryGUI( Stage primaryStage, Scene s) {
		
		stage = primaryStage;
		CustomerPage();

	}
	public void CustomerPage(){
		// String path;
			
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
		CustomerScene = scene;
		GridPane gridPane = new GridPane();
		
		//Add btns to GUI
		FillGUI(gridPane);
		
		//Decide what to do with each btn click
		//AddFunctionality();
		AddImage(group);
		group.getChildren().add(gridPane);
		gridPane.setPadding(new Insets(49, 49, 56, 150));
		gridPane.setAlignment(Pos.TOP_LEFT);

		gridPane.setVgap(3);
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
		File file = new File("Books2.jpg");
		Image background = new Image(file.toURI().toString());
        ImageView img = new ImageView(background);
        img.setPreserveRatio(true);
        group.getChildren().add(img);
	}
	
	public void FillGUI(GridPane gp){
		Label ISBN = new Label("ISBN: ");
		ISBN.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(ISBN, 1, 2);

		Label title = new Label("Title: ");
		title.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(title, 1, 5);
		
		Label PID = new Label("PID: ");
		PID.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(PID, 1, 8);
		
		Label year = new Label("Year: ");
		year.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(year, 1, 11);
		
		Label price = new Label("Price: ");
		price.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(price, 1, 14);
		
		Label category = new Label("Category: ");
		category.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(category, 1, 17);
		
		Label stock = new Label("Stock: ");
		stock.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(stock, 1, 20);
		
		Label threshold = new Label("Threshold: ");
		threshold.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(threshold, 1, 23);
		
		TextField ISBNTf = new TextField();
		gp.add(ISBNTf, 3, 2);
		
		TextField titleTf = new TextField();
		gp.add(titleTf, 3, 5);
		
		TextField PIDTf = new TextField();
		gp.add(PIDTf, 3, 8);
		
		TextField yearTf = new TextField();
		gp.add(yearTf, 3, 11);
		
		TextField priceTf = new TextField();
		gp.add(priceTf, 3, 14);
		
		TextField categoryTf = new TextField();
		gp.add(categoryTf, 3, 17);
		
		TextField stockTf = new TextField();
		gp.add(stockTf, 3, 20);
		TextField thresholdTf = new TextField();
		gp.add(thresholdTf, 3, 23);
		
		
		addBook.setText("Add Book");
		addBook.setPrefSize(200, 35);
		gp.add(addBook, 3, 26);
		addBook.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		
		modify.setText("Modify Book");
		modify.setPrefSize(200, 35);
		gp.add(modify, 3, 29);
		modify.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		order.setText("Order Book");
		order.setPrefSize(200, 35);
		gp.add(order, 3, 32);
		order.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		confirm.setText("Confirm Order");
		confirm.setPrefSize(200, 35);
		gp.add(confirm , 3, 35);
		confirm.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		
		Label UID = new Label("UID: ");
		UID.setStyle("-fx-font: normal bold 32px 'serif' ");
		UID.setPadding(new Insets(0, 0, 5, 10));
		gp.add(UID,20, 2);
		TextField UIDTf = new TextField();
		gp.add(UIDTf,23 , 2);
		
		promote.setText("Promote customer");
		promote.setPrefSize(200, 35);
		gp.add(promote , 23, 5);
		promote.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 20px 'serif' ;");

		
		
		
		
		gp.setAlignment(Pos.TOP_LEFT);
		gp.setPadding(new Insets(0, 0, 5, 5));
		

} /**
	1. Add new books
	2. Modify existing books
	3. Place orders for books
	4. Confirm orders
	5. Promote registered customers to have managers credentials
	6. View the following reports on sales
	a. The total sales for books in the previous month
	b. The top 5 customers who purchase the most purchase amount in descending order for the last
	three months
	c. The top 10 selling books for the last three months
	Assume that the system stores book sales and other related data for the last 3 months.*/
}