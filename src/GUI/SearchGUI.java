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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SearchGUI {
	private Button Back = new Button();
	private Button Search = new Button();
	private Scene CustomerScene;
	private Stage stage;
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


	public SearchGUI( Stage primaryStage, Scene s) {
		//System.out.print("hi");

		CustomerScene = s;
		stage = primaryStage;
		SearchPage();
	}
	
	public void SearchPage(){
		Group group = new Group();
		Scene scene = new Scene(group, 980, 630);
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
		
		Search.setText("Search Book");
		Search.setPrefSize(200, 35);
		gp.add(Search, 3, 20);
		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 21);
		
		Back.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");
		Search.setStyle("-fx-background-color: #006064; -fx-text-fill: white; -fx-font: normal bold 25px 'serif' ;");

		gp.setAlignment(Pos.BOTTOM_CENTER);
		gp.setPadding(new Insets(49, 49, 56, 280));

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
