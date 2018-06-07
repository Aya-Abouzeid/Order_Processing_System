package GUI;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import Library.DBMaster;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
	private String[] data = new String[8];
	private DBMaster dbm;

	public SearchGUI( Stage primaryStage, Scene s) throws ClassNotFoundException, SQLException {
		//System.out.print("hi");
		dbm = DBMaster.getDBMaster();
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
		gridPane.setPadding(new Insets(49, 49, 56, 100));
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
		gp.add(Price, 4, 2);

		Category.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Category, 4, 5);
		
		Threshold.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Threshold, 4, 8);
		
		Stock.setStyle("-fx-font: normal bold 32px 'serif' ");
		gp.add(Stock, 4, 11);
		
		ISBNTf.setVisible(false);
		gp.add(ISBNTf, 2, 2);

		PIDTf.setVisible(false);
		gp.add(PIDTf, 2, 5);
		
		TitleTf.setVisible(false);
		gp.add(TitleTf, 2, 8);
		
		YearTf.setVisible(false);
		gp.add(YearTf, 2, 11);
		
		PriceTf.setVisible(false);
		gp.add(PriceTf, 5, 2);
		
		CategoryTf.setVisible(false);
		gp.add(CategoryTf, 5, 5);
		
		ThresholdTf.setVisible(false);
		gp.add(ThresholdTf, 5, 8);
		
		StockTf.setVisible(false);
		gp.add(StockTf, 5, 11);
		
		
		Search.setText("Search Book");
		Search.setPrefSize(200, 35);
		gp.add(Search, 5, 15);
		
		Back.setText("< Back");
		Back.setPrefSize(119, 35);
		gp.add(Back, 1, 15);
		
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
		
		Search.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(emptyTextFields())
					showAlert("Update Failed","Please Fill All Selected Fields");
				else{
					addData();
					try {
						ResultSet x = (ResultSet) dbm.searchBook(data);
						ViewerGUI x2 = new ViewerGUI(stage,CustomerScene,x);
						
						

					} catch (SQLException e) {
						// TODO Auto-generated catch block
					//	showAlert("Update Failed","Username or Email already Used");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
	}
	//"ISBN","PID","TITLE","YEAR","PRICE","CATEGORY","THRESHOLD","STOCK"
	private void addData(){
		if(!ISBN.isSelected())
			data[0]="";
		else
			data[0]=ISBNTf.getText().trim();
		if(!PID.isSelected())
			data[1]="";
		else
			data[1]=PIDTf.getText().trim();
		if(!Title.isSelected())
			data[2]="";
		else
			data[2]=TitleTf.getText().trim();
		if(!Year.isSelected())
			data[3]="";
		else
			data[3]=YearTf.getText().trim();
		if(!Price.isSelected())
			data[4]="";
		else
			data[4]=PriceTf.getText().trim();
		if(!Category.isSelected())
			data[5]="";
		else
			data[5]=CategoryTf.getText().trim();
		if(!Threshold.isSelected())
			data[6]="";
		else
			data[6]=ThresholdTf.getText().trim();
		if(!Stock.isSelected())
			data[7]="";
		else
			data[7]=StockTf.getText().trim();
		
	}
	private boolean emptyTextFields(){
		if(( ISBN.isSelected()&& ISBNTf.getText().trim().isEmpty()
				|| (PID.isSelected()&& PIDTf.getText().trim().isEmpty())
				|| (Title.isSelected()&& TitleTf.getText().trim().isEmpty())
				|| (Year.isSelected()&& YearTf.getText().trim().isEmpty())
				|| (Price.isSelected()&& PriceTf.getText().trim().isEmpty())
				|| (Category.isSelected()&& CategoryTf.getText().trim().isEmpty())
				|| (Threshold.isSelected()&& ThresholdTf.getText().trim().isEmpty())
				|| (Stock.isSelected()&& StockTf.getText().trim().isEmpty()))
				)
			return true;
		return false;
	}
	private void showAlert(String title , String msg){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		alert.show();
	}

}
