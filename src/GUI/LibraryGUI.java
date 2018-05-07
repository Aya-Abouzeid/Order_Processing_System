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


public class LibraryGUI {
	private Button Back = new Button();
	private Button Search = new Button();
	
	private RadioButton ISBN = new RadioButton("ISBN");
	private RadioButton PID = new RadioButton("PID");
	private RadioButton Title = new RadioButton("Title");
	private RadioButton Year = new RadioButton("Year");
	private RadioButton Price = new RadioButton("Price");
	private RadioButton Category = new RadioButton("Category");
	private RadioButton Stock = new RadioButton("Stock");
	private RadioButton Threshold = new RadioButton("Threshold");
	private TextField ISBNTf = new TextField();
	private TextField PIDTf = new TextField();
	private TextField TitleTf = new TextField();
	private TextField YearTf = new TextField();
	private TextField PriceTf = new TextField();
	private TextField CategoryTf = new TextField();
	private TextField StockTf = new TextField();
	private TextField ThresholdTf = new TextField();
	
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
		RadioBtnFunctionality();
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


		ISBN.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(ISBN, 1, 2);

		PID.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(PID, 1, 5);
		
		Title.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Title, 1, 8);
		
		Year.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Year, 1, 11);
		
		Price.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Price, 1, 14);

		Category.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Category, 1, 17);
		
		
		ISBNTf.setVisible(false);
		gp.add(ISBNTf, 3, 2);

		PIDTf.setVisible(false);
		gp.add(PIDTf, 3, 5);
		
		TitleTf.setVisible(false);
		gp.add(TitleTf, 3, 8);
		
		YearTf.setVisible(false);
		gp.add(YearTf, 3, 11);
		
		PriceTf.setVisible(false);
		gp.add(PriceTf, 3, 14);
		
		CategoryTf.setVisible(false);
		gp.add(CategoryTf, 3, 17);	
		
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
		gp.add(UID,15, 2);
		TextField UIDTf = new TextField();
		gp.add(UIDTf,18 , 2);
		
		promote.setText("Promote customer");
		promote.setPrefSize(200, 35);
		gp.add(promote , 18, 5);
		promote.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 20px 'serif' ;");

		
		
		
		
		gp.setAlignment(Pos.TOP_LEFT);
		gp.setPadding(new Insets(0, 0, 5, 5));
		

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
		PID.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(PID.isSelected()){
	            	PIDTf.setVisible(true);
	            }
	            else{
	            	PIDTf.clear();
	            	PIDTf.setVisible(false);
	            }
	        }
	    });
		Title.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Title.isSelected()){
	            	TitleTf.setVisible(true);
	            }
	            else{
	            	TitleTf.clear();
	            	TitleTf.setVisible(false);
	            }
	        }
	    });
		Year.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Year.isSelected()){
	            	YearTf.setVisible(true);
	            }
	            else{
	            	YearTf.clear();
	            	YearTf.setVisible(false);
	            }
	        }
	    });
		Price.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Price.isSelected()){
	            	PriceTf.setVisible(true);
	            }
	            else{
	            	PriceTf.clear();
	            	PriceTf.setVisible(false);
	            }
	        }
	    });
		Category.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Category.isSelected()){
	            	CategoryTf.setVisible(true);
	            }
	            else{
	            	CategoryTf.clear();
	            	CategoryTf.setVisible(false);
	            }
	        }
	    });
		Threshold.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Threshold.isSelected()){
	            	ThresholdTf.setVisible(true);
	            }
	            else{
	            	ThresholdTf.clear();
	            	ThresholdTf.setVisible(false);
	            }
	        }
	    });
		Stock.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent arg0) {
	            if(Stock.isSelected()){
	            	StockTf.setVisible(true);
	            }
	            else{
	            	StockTf.clear();
	            	StockTf.setVisible(false);
	            }
	        }
	    });
	}
/**
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