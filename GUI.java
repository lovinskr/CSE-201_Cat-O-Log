
/*
 * Help from:
 * http://www.java2s.com/Tutorials/Java/JavaFX/0590__JavaFX_ComboBox.htm 
 * 
 * 
 * background color options:
 * https://www.w3schools.com/colors/colors_names.asp 
 * 
 * 
 */


import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage; 

public class GUI extends Application
{
	int width = 500; 
	int height = 500; 
	
	public static void main(String[] args)
	{ 
		launch(args);  
	}
	 
	
	@Override 
	public void start(Stage primaryStage)
	{ 
		/*
		 * DO NOT CHANGE ORDER !!!!
		 * 
		 * 
		 * 
		 */
		primaryStage.setTitle("Cat-o-log");
		
		StackPane root = new StackPane();
		// sets up for where the filters and such will be added and organized later in the code  
		GridPane grid = new GridPane(); 
		grid.setVgap(10); // gap between rows 
		grid.setHgap(10); // gap between columns
		ScrollBar sbV = new ScrollBar();
		ScrollBar sbH = new ScrollBar();
		root.setStyle("-fx-background-color: LightSkyBlue;"); // background color
		Scene startPage = new Scene(root, width, height); 
	    
		// a mini abstract earth  
		// fix later and put by login 
		Circle C = new Circle(15,15,15);  
        RadialGradient gradient1 = new RadialGradient(
        	0, 		 // focus angle
            .01, 	 // focus distance
            0, 	 // centerX
            0, 	 // centerY
            75,  	 // radius
            false,   // proportional 
            CycleMethod.NO_CYCLE,  
            new Stop(0, Color.GREEN), 
            new Stop(1, Color.BLUE)
            );  
        C.setFill(gradient1); 
        C.setTranslateY(-150);
        C.setTranslateX(270);
        
		
		// search bar 
		TextField searchBar = new TextField(); 
		searchBar.setPromptText("Search the Cat-o-log");
		searchBar.setMaxHeight(50);
		searchBar.getText(); // use this to access text 
		searchBar.prefHeightProperty().bind(root.heightProperty());
	    searchBar.prefWidthProperty().bind(root.widthProperty());
	    
	    // submit search button 
	    Button submit = new Button("Search!"); 
	    submit.setMaxHeight(50);
	    
	    // submit.setMaxWidth(500); need to find a way to set limits better
	    submit.prefHeightProperty().bind(root.heightProperty());
	    submit.prefWidthProperty().bind(root.widthProperty());
	    
	    submit.setOnAction(new EventHandler<ActionEvent>() 
	    {

	    	@Override
	    	public void handle(ActionEvent e) 
	    	{
	    		// will return animals that include the word(s) entered 
	    	}
	    });
	    
	    // login 
		// just there to look pretty for now
		Button login = new Button("Login");
		login.setMaxHeight(50);
	    login.prefHeightProperty().bind(root.heightProperty());
	    login.prefWidthProperty().bind(root.widthProperty());
	    
		// put the login stuff here 
		login.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	        	// will go to login scene 
	            System.out.println("This will be where you login in!");
	        }
		}); 
		
		// drop down sortBy  
		ComboBox<String> sortByDropDown = new ComboBox<String>();
		sortByDropDown.getItems().addAll("Name", "Number of Legs", "Preferred Biome", "Lifespan",
										 "Diet", "Species", "Method of Travel", "Cold Blooded", 
										 "Warm Blooded");
		sortByDropDown.getValue(); // how to access the choice 
		sortByDropDown.setMaxHeight(50);
		sortByDropDown.prefHeightProperty().bind(root.heightProperty());
		sortByDropDown.prefWidthProperty().bind(root.widthProperty());
		
		
		
		// this is basically just a title to show that they're filters
		Text T = new Text("Filters"); 
		// the check boxes that will be filters 
		CheckBox chkColdBlooded = new CheckBox("Cold Blooded");
		chkColdBlooded.setMaxHeight(20);
		chkColdBlooded.prefHeightProperty().bind(root.heightProperty());
		chkColdBlooded.prefWidthProperty().bind(root.widthProperty());
		
		chkColdBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Filter includes only cold blooded animals");
	        }
		});
		
		CheckBox chkWarmBlooded = new CheckBox("Warm Blooded");
		chkWarmBlooded.setMaxHeight(20);
		chkWarmBlooded.prefHeightProperty().bind(root.heightProperty());
		chkWarmBlooded.prefWidthProperty().bind(root.widthProperty());
		
		chkWarmBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Filter includes only warm blooded animals");
	        }
		});
		
		// have to include a text box to get the limb range 
		CheckBox limbLimits = new CheckBox("Limit Limbs");
		limbLimits.setMaxHeight(20);
		limbLimits.prefHeightProperty().bind(root.heightProperty());
		limbLimits.prefWidthProperty().bind(root.widthProperty());
		TextField limbLimitL = new TextField(); // lower limb limit 
		limbLimitL.setPromptText("Minimum Number of Limbs");
		limbLimitL.setMaxHeight(20);
		limbLimitL.setMaxWidth(100);
		limbLimitL.getText(); // use this to access text 
		limbLimitL.prefHeightProperty().bind(root.heightProperty());
		limbLimitL.prefWidthProperty().bind(root.widthProperty());
		TextField limbLimitU = new TextField(); // upper limb limit
		limbLimitU.setPromptText("Maximum Number of Limbs");
		limbLimitU.setMaxHeight(20);
		limbLimitU.setMaxWidth(100);
		limbLimitU.getText(); // use this to access text 
		limbLimitU.prefHeightProperty().bind(root.heightProperty());
		limbLimitU.prefWidthProperty().bind(root.widthProperty());
		
		limbLimits.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Uses the entered limb max and min as boundries");
	        }
		});
		
		CheckBox chkflies = new CheckBox("Flies");
		chkflies.setMaxHeight(20);
		chkflies.prefHeightProperty().bind(root.heightProperty());
		chkflies.prefWidthProperty().bind(root.widthProperty());
		chkflies.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Animals that fly");
	        }
		});
		
		CheckBox chkSlithers = new CheckBox("Slithers");
		chkSlithers.setMaxHeight(20);
		chkSlithers.prefHeightProperty().bind(root.heightProperty());
		chkSlithers.prefWidthProperty().bind(root.widthProperty());
		chkSlithers.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Animals that slither");
	        }
		});
		
		CheckBox chkWalk = new CheckBox("Walk");
		chkWalk.setMaxHeight(20);
		chkWalk.prefHeightProperty().bind(root.heightProperty());
		chkWalk.prefWidthProperty().bind(root.widthProperty());
		chkWalk.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Animals that walk");
	        }
		});
		
		// Lifespan range
		CheckBox chkLifespan = new CheckBox("Lifespan");
		chkLifespan.setMaxHeight(20);
		chkLifespan.prefHeightProperty().bind(root.heightProperty());
		chkLifespan.prefWidthProperty().bind(root.widthProperty());
		TextField lifeLimitL = new TextField(); // lower limb limit 
		lifeLimitL.setPromptText("Minimum Number of Years");
		lifeLimitL.setMaxHeight(20);
		lifeLimitL.setMaxWidth(100);
		lifeLimitL.getText(); // use this to access text 
		lifeLimitL.prefHeightProperty().bind(root.heightProperty());
		lifeLimitL.prefWidthProperty().bind(root.widthProperty());
		TextField lifeLimitU = new TextField(); // upper limb limit
		lifeLimitU.setPromptText("Maximum Number of Years");
		lifeLimitU.setMaxHeight(20);
		lifeLimitU.setMaxWidth(100);
		lifeLimitU.getText(); // use this to access text 
		lifeLimitU.prefHeightProperty().bind(root.heightProperty());
		lifeLimitU.prefWidthProperty().bind(root.widthProperty());
		
		chkLifespan.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Uses the entered years max and min as boundries");
	        }
		});
		
		// diet drop down 
		// can make a couple of check boxes later 
		ComboBox<String> dietDropDown = new ComboBox<String>();
		dietDropDown.getItems().addAll("Carnivore", "Omnivore", "Herbavore");
		dietDropDown.getValue(); // how to access the choice 
		dietDropDown.setMaxHeight(20);
		dietDropDown.setMaxWidth(100);
		dietDropDown.prefHeightProperty().bind(root.heightProperty());
		dietDropDown.prefWidthProperty().bind(root.widthProperty());
		
		CheckBox chkMammal = new CheckBox("Mammals");
		chkMammal.setMaxHeight(20);
		chkMammal.prefHeightProperty().bind(root.heightProperty());
		chkMammal.prefWidthProperty().bind(root.widthProperty());
		chkMammal.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Mammals");
	        }
		});
		
		CheckBox chkFish = new CheckBox("Mammals");
		chkFish.setMaxHeight(20);
		chkFish.prefHeightProperty().bind(root.heightProperty());
		chkFish.prefWidthProperty().bind(root.widthProperty());
		chkFish.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Fish");
	        }
		});
		
		CheckBox chkBird = new CheckBox("Birds");
		chkBird.setMaxHeight(20);
		chkBird.prefHeightProperty().bind(root.heightProperty());
		chkBird.prefWidthProperty().bind(root.widthProperty());
		chkBird.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Birds");
	        }
		});
		
		CheckBox chkReptiles = new CheckBox("Reptiles");
		chkReptiles.setMaxHeight(20);
		chkReptiles.prefHeightProperty().bind(root.heightProperty());
		chkReptiles.prefWidthProperty().bind(root.widthProperty());
		chkReptiles.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Reptiles");
	        }
		});
		
		CheckBox chkInsect = new CheckBox("Insect");
		chkInsect.setMaxHeight(20);
		chkInsect.prefHeightProperty().bind(root.heightProperty());
		chkInsect.prefWidthProperty().bind(root.widthProperty());
		
		chkInsect.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Insects");
	        }
		});
		
		CheckBox chkAmphibians = new CheckBox("Amphibians");
		chkAmphibians.setMaxHeight(20);
		chkAmphibians.prefHeightProperty().bind(root.heightProperty());
		chkAmphibians.prefWidthProperty().bind(root.widthProperty());
		chkAmphibians.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Amphibians");
	        }
		});
		
		CheckBox chkInvertebratesJointedLegs = new CheckBox("Invertebrates");
		chkInvertebratesJointedLegs.setMaxHeight(20);
		chkInvertebratesJointedLegs.prefHeightProperty().bind(root.heightProperty());
		chkInvertebratesJointedLegs.prefWidthProperty().bind(root.widthProperty());
		chkInvertebratesJointedLegs.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Invertebrates with Jointed Legs");
	        }
		});
		
		CheckBox chkInvertebratesNoJointedLegs = new CheckBox("Invertebrates");
		chkInvertebratesNoJointedLegs.setMaxHeight(20);
		chkInvertebratesNoJointedLegs.prefHeightProperty().bind(root.heightProperty());
		chkInvertebratesNoJointedLegs.prefWidthProperty().bind(root.widthProperty());
		chkInvertebratesNoJointedLegs.setOnAction(new EventHandler<ActionEvent>() 
		{
	        @Override
	        public void handle(ActionEvent event) 
	        {
	            System.out.println("Invertebrates with No Jointed Legs");
	        }
		});
		
		
		/* 
		 * !!!!!!!!!!!!!!!
		 * PLEASE DON'T MESS WITH THE CODE LINE ORDER
		 * THE GRID CAN BE EASILY FCKED UP !!!!
		 * !!!!!!!!!!!!!!!!!!
		 * 
		 * Filter Column is grid column 0
		*/
		
		
		// add filters to this column 
		grid.addColumn(0,T, chkColdBlooded, chkWarmBlooded, limbLimitL, 
				limbLimitU, limbLimits, chkflies, lifeLimitL, lifeLimitU, 
				chkLifespan, dietDropDown, chkWalk, chkSlithers, chkMammal, 
				chkFish, chkBird, chkReptiles, chkInsect, chkAmphibians, 
				chkInvertebratesJointedLegs, chkInvertebratesNoJointedLegs); 
		
		grid.addRow(0, searchBar, submit, sortByDropDown, login);
		
		root.getChildren().add(grid); 
		
		primaryStage.setScene(startPage);
		primaryStage.show(); 
	}
	
}
