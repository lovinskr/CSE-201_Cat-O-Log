
/*
 * This is the entire front end of the Cat-o-log. 
 * 
 * 
 * color options:
 * https://www.w3schools.com/colors/colors_names.asp 
 *
 *
 * The line order is extremely particular and CAN NOT be changed 
 * 
 * To do: 
 * sort by travel method 
 * add comment 
 * delete comment 
 * saving users full info 
 * requests 
 * 
 */

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends Application 
{
	/* 
	 * These global variables are used in places where sending them 
	 * as arguments would make it a very long list of parameters 
	 * 
	 */
	private int width = 500;
	private int height = 500;
	int loginAttempts = 0;
	private boolean isLoggedIn = false; 
	VBox root = new VBox(); // the entire GUI is built from this up 
	LinkedList checked = new LinkedList<CheckBox>(); // the list of check boxes checked
	Animal[] frontPage = new Animal[500]; // the list of animals that are currently shown
	GridPane constantLogin = new GridPane(); // the constant login at the top 
	GridPane top = new GridPane(); // hold the login and search bar and everything that stays at the top permenantly 
	String loggedInUN = ""; 
	String loggedInPW = ""; 
	Boolean isAdminLoggedIn = false; 
	User currentUser = null; 
	int lowerLimbLimit = -88; // used if the filters check limit limbs/lifespan 
	int upperLimbLimit = -88; 
	int lowerAgeLimit = -88; 
	int upperAgeLimit = -88; 
	String dietDropChoice = ""; // used if filter diet dropdown 
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	/*
	 *  This is the method that does most of the work 
	 *  creates the first set up and calls helper methods when 
	 *  buttons are clicked, ect. 
	 */
	@Override
	public void start(Stage primaryStage) throws IOException  
	{
		// sets up the stage that holds all the parts 
		primaryStage.setTitle("Cat-o-log");
		root.getChildren().addAll(); 
		root.setSpacing(10);
		root.setPadding(new Insets(10)); 
		Catalog animalCatalog = new Catalog(); 
		frontPage = animalCatalog.getAnimals(); 
		GridPane grid = animalGrid(frontPage);
		grid.setPadding(new Insets(10, 10, 10, 10)); // pads edges
		ScrollPane sp = new ScrollPane(grid);
		sp.setPannable(true); 
				
		// the check boxes that will be filters
		CheckBox chkColdBlooded = makeChkBox("Cold Blooded");
		chkColdBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{	
				chkColdBlooded.setStyle("-fx-color: LightSkyBlue");
				
				if(chkColdBlooded.isSelected() == false)
				{
					chkColdBlooded.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkColdBlooded); 
				}
				else
					checked.add(chkColdBlooded); 
			}
		});

		CheckBox chkWarmBlooded = makeChkBox("Warm Blooded");
		chkWarmBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override 
			public void handle(ActionEvent event) 
			{
				chkWarmBlooded.setStyle("-fx-color: LightSkyBlue");
				
				if(chkWarmBlooded.isSelected() == false)
				{
					chkWarmBlooded.setStyle("-fx-color: MediumAquaMarine");
				checked.remove(chkWarmBlooded); 
			}
			else
				checked.add(chkWarmBlooded); 
			}
		});

		CheckBox chkFlies = makeChkBox("Flies");
		chkFlies.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkFlies.setStyle("-fx-color: LightSkyBlue");
				
				if(chkFlies.isSelected() == false)
				{
					chkFlies.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkFlies); 
				}
				else
					checked.add(chkFlies); 
				
			}
		});

		CheckBox chkSlithers = makeChkBox("Slithers");
		chkSlithers.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkSlithers.setStyle("-fx-color: LightSkyBlue");
				
				if(chkSlithers.isSelected() == false)
				{
					chkSlithers.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkSlithers); 
				}
				else
					checked.add(chkSlithers); 
				
			}
		});

		CheckBox chkWalk = makeChkBox("Walk");
		chkWalk.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkWalk.setStyle("-fx-color: LightSkyBlue");
				
				if(chkWalk.isSelected() == false)
				{
					chkWalk.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkWalk); 
				}
				else
					checked.add(chkWalk); 
				
			}
		});

		CheckBox chkSwim = makeChkBox("Swims");
		chkSwim.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkSwim.setStyle("-fx-color: LightSkyBlue");
				
				if(chkSwim.isSelected() == false)
				{
					chkSwim.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkSwim); 
				}
				else
					checked.add(chkSwim); 
				
			}
		});

		CheckBox chkFish = makeChkBox("Fish");
		chkFish.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkFish.setStyle("-fx-color: LightSkyBlue");
				
				if(chkFish.isSelected() == false)
				{
					chkFish.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkFish); 
				}
				else
					checked.add(chkFish); 
				
			}
		});

		CheckBox chkBird = makeChkBox("Birds");
		chkBird.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkBird.setStyle("-fx-color: LightSkyBlue");
				
				if(chkBird.isSelected() == false)
				{
					chkBird.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkBird); 
				}
				else
					checked.add(chkBird); 
				
			}
		});

		CheckBox chkReptiles = makeChkBox("Reptiles");
		chkReptiles.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkReptiles.setStyle("-fx-color: LightSkyBlue");
				
				if(chkReptiles.isSelected() == false)
				{
					chkReptiles.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkReptiles); 
				}
				else
					checked.add(chkReptiles); 
				
			}
		});

		CheckBox chkInsect = makeChkBox("Insect");
		chkInsect.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				chkInsect.setStyle("-fx-color: LightSkyBlue");
				
				if(chkInsect.isSelected() == false)
				{
					chkInsect.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkInsect); 
				}
				else
					checked.add(chkInsect); 
				
			}
		});

		CheckBox chkAmphibians = makeChkBox("Amphibians");
		chkAmphibians.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkAmphibians.setStyle("-fx-color: LightSkyBlue");
				
				if(chkAmphibians.isSelected() == false)
				{
					chkAmphibians.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkAmphibians); 
				}
				else
					checked.add(chkAmphibians); 
				
			}
		});

		CheckBox chkMammal = makeChkBox("Mammals");
		chkMammal.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkMammal.setStyle("-fx-color: LightSkyBlue");
				
				if(chkMammal.isSelected() == false)
				{
					chkMammal.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkMammal); 
				}
				else
					checked.add(chkMammal); 
				
			}
		});
		
		CheckBox chkArthropods = makeChkBox("Arthropods");
		chkArthropods.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event)
			{
				chkArthropods.setStyle("-fx-color: LightSkyBlue");
				
				if(chkArthropods.isSelected() == false)
				{
					chkArthropods.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkArthropods); 
				}
				else
					checked.add(chkArthropods); 
				
			}
		});

		CheckBox chkAquatic = makeChkBox("Aquatic");
		chkAquatic.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkAquatic.setStyle("-fx-color: LightSkyBlue");
				
				if(chkAquatic.isSelected() == false)
				{
					chkAquatic.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkAquatic); 
				}
				else
					checked.add(chkAquatic); 
				
			}
		});
		
		CheckBox chkTundra = makeChkBox("Tundra");
		chkTundra.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTundra.setStyle("-fx-color: LightSkyBlue");
				
				if(chkTundra.isSelected() == false)
				{
					chkTundra.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkTundra); 
				}
				else
					checked.add(chkTundra); 
				
			}
		});
		
		CheckBox chkTaiga = makeChkBox("Taiga");
		chkTaiga.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTaiga.setStyle("-fx-color: LightSkyBlue");
				
				if(chkTaiga.isSelected() == false)
				{
					chkTaiga.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkTaiga); 
				}
				else
					checked.add(chkTaiga); 
				
			}
		});
		
		CheckBox chkTDForest = makeChkBox("Temperate Deciduous Forest");
		chkTDForest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTDForest.setStyle("-fx-color: LightSkyBlue");
				
				if(chkTDForest.isSelected() == false)
				{
					chkTDForest.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkTDForest); 
				}
				else
					checked.add(chkTDForest); 
				
			}
		});
		
		CheckBox chkScrubForest = makeChkBox("Scrub Forest");
		chkScrubForest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkScrubForest.setStyle("-fx-color: LightSkyBlue");
				
				if(chkScrubForest.isSelected() == false)
				{
					chkScrubForest.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkScrubForest); 
				}
				else
					checked.add(chkScrubForest); 
				
			}
		});
		
		CheckBox chkGrassland = makeChkBox("Grassland");
		chkGrassland.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkGrassland.setStyle("-fx-color: LightSkyBlue");
				
				if(chkGrassland.isSelected() == false)
				{
					chkGrassland.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkGrassland); 
				}
				else
					checked.add(chkGrassland); 
				
			}
		});
		
		CheckBox chkDesert = makeChkBox("Desert");
		chkDesert.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkDesert.setStyle("-fx-color: LightSkyBlue");
				
				if(chkDesert.isSelected() == false)
				{
					chkDesert.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkDesert); 
				}
				else
					checked.add(chkDesert); 
				
			}
		});
		
		CheckBox chkTempRForest = makeChkBox("Temperate Rain Forest");
		chkTempRForest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				
				chkTempRForest.setStyle("-fx-color: LightSkyBlue");
				
				if(chkTempRForest.isSelected() == false)
				{
					chkTempRForest.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkTempRForest); 
				}
				else
					checked.add(chkTempRForest); 
				
			}
		});
		
		CheckBox chkTropRForest = makeChkBox("Tropical Rain Forest");
		chkTropRForest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkTropRForest.setStyle("-fx-color: LightSkyBlue");
				
				if(chkTropRForest.isSelected() == false)
				{
					chkTropRForest.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkTropRForest); 
				}
				else
					checked.add(chkTropRForest); 
				
			}
		});
		
		// preferred region which doesn't have a filter yet 
		CheckBox chkNearctic = makeChkBox("Nearctic");
		chkNearctic.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkNearctic.setStyle("-fx-color: LightSkyBlue");
				
				if(chkNearctic.isSelected() == false)
				{
					chkNearctic.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkNearctic); 
				}
				else
					checked.add(chkNearctic); 
				
			}
		});
		
		CheckBox chkPalearctic = makeChkBox("Palearctic");
		chkPalearctic.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkPalearctic.setStyle("-fx-color: LightSkyBlue");
				
				if(chkPalearctic.isSelected() == false)
				{
					chkPalearctic.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkPalearctic); 
				}
				else
					checked.add(chkPalearctic); 
				
			}
		});
		
		CheckBox chkAfrican = makeChkBox("African");
		chkAfrican.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkAfrican.setStyle("-fx-color: LightSkyBlue");
				
				if(chkAfrican.isSelected() == false)
				{
					chkAfrican.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkAfrican); 
				}
				else
					checked.add(chkAfrican); 
				
			}
		});

		CheckBox chkNeoTropical = makeChkBox("NeoTropical");
		chkNeoTropical.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkNeoTropical.setStyle("-fx-color: LightSkyBlue");
				
				if(chkNeoTropical.isSelected() == false)
				{
					chkNeoTropical.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkNeoTropical); 
				}
				else
					checked.add(chkNeoTropical); 
				
			}
		});
		
		CheckBox chkOriental = makeChkBox("Oriental");
		chkOriental.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkOriental.setStyle("-fx-color: LightSkyBlue");
				
				if(chkOriental.isSelected() == false)
				{
					chkOriental.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkOriental); 
				}
				else
					checked.add(chkOriental); 
				
			}
		});
		
		CheckBox chkAustralian = makeChkBox("Australian");
		chkAustralian.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkAustralian.setStyle("-fx-color: LightSkyBlue");
				
				if(chkAustralian.isSelected() == false)
				{
					chkAustralian.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkAustralian); 
				}
				else
					checked.add(chkAustralian); 
				
			}
		});
		
		// Lifespan range
		CheckBox chkLifespan = makeChkBox("Average Lifespan");
		TextField lifeLimitL = makeTF("Min Years"); // lower limb limit 
		lifeLimitL.setMaxHeight(20);
		TextField lifeLimitU = makeTF("Max Years"); // upper limb limit
		lifeLimitU.setMaxHeight(20); 
		chkLifespan.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				chkLifespan.setStyle("-fx-color: LightSkyBlue");
				
				if(chkLifespan.isSelected() == false)
				{
					chkLifespan.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(chkLifespan); 
					lowerAgeLimit = -88; 
					upperAgeLimit = -88; 
				}
				else
				{
					if(lifeLimitL.getText().length() != 0)
						lowerAgeLimit = Integer.parseInt(lifeLimitL.getText()); 
					if(lifeLimitU.getText().length() != 0)
						upperAgeLimit = Integer.parseInt(lifeLimitU.getText()); 
					checked.add(chkLifespan); 
				}
				
			}
		});

		// have to include a text box to get the limb range
		CheckBox limbLimits = makeChkBox("Limit Limbs");
		TextField limbLimitL = makeTF("Min Limbs"); // lower limb limit
		limbLimitL.setMaxHeight(20);
		TextField limbLimitU = makeTF("Max Limbs"); // upper limb limit
		limbLimitU.setMaxHeight(20);
		limbLimits.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{	
				limbLimits.setStyle("-fx-color: LightSkyBlue");
				
				if(limbLimits.isSelected() == false)
				{
					limbLimits.setStyle("-fx-color: MediumAquaMarine");
					checked.remove(limbLimits); 
					lowerLimbLimit = -88; 
					upperLimbLimit = -88; 
				}
				else
				{
					if(limbLimitU.getText().length() != 0)
						upperLimbLimit = Integer.parseInt(limbLimitU.getText()); 
					if(limbLimitL.getText().length() != 0)
						lowerLimbLimit = Integer.parseInt(limbLimitL.getText()); 
					
					checked.add(limbLimits); 
				}
				
			}
		});
		
		// diet drop down
		// can make a couple of check boxes later
		String[] dietOptions = { "Carnivore", "Omnivore", "Herbavore" };
		ComboBox<String> dietDropDown = makeDropDown(dietOptions);
		dietDropDown.setMaxHeight(20);
		dietDropDown.setPromptText("Diet");
		dietDropDown.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				dietDropChoice = dietDropDown.getValue(); 
			}
		});
		
		 /* 
		 * Filter Column is grid column 0
		 * the big grid's holds the titledPane 
		 * that holds a smaller grid that holds the check boxes 
		 */
		
		TitledPane tp = new TitledPane();
		tp.setText("Filter");
		
		TitledPane biomesTP = new TitledPane(); 
		biomesTP.setText("Preferred Biome");
		GridPane biomesGP = new GridPane(); 
		biomesGP.setVgap(4);
		biomesGP.add(chkTundra, 0, 0);
		biomesGP.add(chkTaiga, 0, 1);
		biomesGP.add(chkTDForest, 0, 2);
		biomesGP.add(chkScrubForest, 0, 3);
		biomesGP.add(chkGrassland, 0, 4);
		biomesGP.add(chkDesert, 0, 5);
		biomesGP.add(chkTropRForest, 0, 6);
		biomesGP.add(chkTempRForest, 0, 7);
		biomesGP.add(chkAquatic, 0, 8);
		biomesTP.setContent(biomesGP);
		biomesTP.setExpanded(false);
		
		TitledPane regionsTP = new TitledPane();
		regionsTP.setText("Average Region");
		GridPane regionsGP = new GridPane();
		regionsGP.setVgap(4);
		regionsGP.add(chkNearctic, 0, 0); 
		regionsGP.add(chkNeoTropical, 0, 1); 
		regionsGP.add(chkAfrican, 0, 2); 
		regionsGP.add(chkPalearctic, 0, 3); 
		regionsGP.add(chkOriental, 0, 4); 
		regionsGP.add(chkAustralian, 0, 5); 
		regionsTP.setContent(regionsGP);
		regionsTP.setExpanded(false);
		
		TitledPane cwBloodedTP = new TitledPane();
		cwBloodedTP.setText("Blood Type");
		GridPane cwBloodedGP = new GridPane();
		cwBloodedGP.setVgap(4);
		cwBloodedGP.add(chkColdBlooded, 0, 1);
		cwBloodedGP.add(chkWarmBlooded, 0, 2);
		cwBloodedTP.setContent(cwBloodedGP);
		cwBloodedTP.setExpanded(false);
		
		
		TitledPane animalClassTP = new TitledPane();
		animalClassTP.setText("Class");
		GridPane animalClassGP = new GridPane(); 
		animalClassGP.setVgap(4);
		animalClassGP.add(chkMammal, 0, 0);
		animalClassGP.add(chkFish, 0, 1);
		animalClassGP.add(chkBird, 0, 2);
		animalClassGP.add(chkReptiles, 0, 3);
		animalClassGP.add(chkInsect, 0, 4);
		animalClassGP.add(chkAmphibians, 0, 5);
		animalClassGP.add(chkArthropods, 0, 6);
		animalClassTP.setContent(animalClassGP); 
		animalClassTP.setExpanded(false);
		
		TitledPane travelsByTP = new TitledPane();
		travelsByTP.setText("Method of Travel");
		GridPane travelsByGP = new GridPane(); 
		travelsByGP.setVgap(4);
		travelsByGP.add(chkFlies, 0, 0);
		travelsByGP.add(chkSlithers, 0, 1);
		travelsByGP.add(chkWalk, 0, 2);
		travelsByGP.add(chkSwim, 0, 3);
		travelsByTP.setContent(travelsByGP);
		travelsByTP.setExpanded(false);
		
		Button filterB = makeButton("Apply Filter"); 
		filterB.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				 try {
					checkCheckboxes();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		
		GridPane checkBoxes = new GridPane();
		checkBoxes.setVgap(4);
		checkBoxes.setPadding(new Insets(5, 5, 5,5));
		checkBoxes.add(filterB, 0, 0);
		checkBoxes.add(animalClassTP, 0, 1);
		checkBoxes.add(biomesTP, 0, 2);
		checkBoxes.add(travelsByTP, 0, 3);
		checkBoxes.add(regionsTP, 0, 4); 
		checkBoxes.add(cwBloodedTP, 0, 5);
		checkBoxes.add(dietDropDown, 0, 6);
		checkBoxes.add(lifeLimitL, 0, 7);
		checkBoxes.add(lifeLimitU, 0, 8);
		checkBoxes.add(chkLifespan, 0, 9);
		checkBoxes.add(limbLimitL, 0, 10);
		checkBoxes.add(limbLimitU, 0, 11);
		checkBoxes.add(limbLimits, 0, 12); 
		
		
		tp.setExpanded(false);
		tp.setContent(checkBoxes);
		 
		
		Button signUp = makeButton("Sign Up"); 
		signUp.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				signUpPopUp(); 
			}
		});
	
		TextField UTF = makeTF("Username"); // user name
		PasswordField PTF = new PasswordField(); // password
		PTF.setPromptText("Password");
		PTF.setMaxHeight(50);
		PTF.prefHeightProperty().bind(root.heightProperty());
		PTF.prefWidthProperty().bind(root.widthProperty());
		Button login = makeButton("Login");
		constantLogin.setVgap(3); // gap between rows
		constantLogin.setHgap(3); // gap between columns
		constantLogin.addRow(1, UTF, PTF, login, signUp);
		root.getChildren().addAll(constantLogin);
		login.setOnAction(new EventHandler<ActionEvent>() 
		{
			 
			@Override
			public void handle(ActionEvent event) 
			{
				String username = UTF.getText().trim(); 
				String password = PTF.getText().trim(); 
				Button u = makeButton(username); 
				
				
				// check with accounts class 
				try 
				{ 
					Accounts temp = new Accounts();
					int hasAccount = temp.hasAccount(username, password); 
					if(hasAccount != -66 && isLoggedIn == false)
					{
						loggedInUN = username; 
						loggedInPW = password; 
						currentUser = (User) temp.userlist.get(hasAccount); 
						if(currentUser.administrator)
						{
							isAdminLoggedIn = true; 
						}
						loggedIn(); 
						login.setText("Logout");
						constantLogin.getChildren().removeAll(UTF, PTF, signUp, login); 
						constantLogin.addRow(0, u, login);
						u.setOnAction(new EventHandler<ActionEvent>() 
						{
							@Override
							public void handle(ActionEvent event) 
							{
								userPersonalPage(); 
							}
						});
						
						
					}
					else if(hasAccount != -66 && isLoggedIn == true || login.getText().equals("Logout"))
					{
						loggedOut(); 
						login.setText("Login");
						constantLogin.getChildren().clear();
						UTF.clear();
						PTF.clear();
						constantLogin.addRow(0, UTF, PTF, login, signUp);
						refreshAnimalGrid(constantLogin);
					}
					else
					{ 
						counter();
						Stage dialog = new Stage();
		                dialog.initModality(Modality.APPLICATION_MODAL);
		                dialog.initOwner(primaryStage);
		                
		                StackPane popup = new StackPane();
		                popup.setStyle("-fx-background-color: AZURE");
		                Text text = new Text("Your password or username was incorrect.");
		                if(loginAttempts > 1)
		                {
		                	text = new Text("Your password or username was incorrect."
		                				  + "You have tried " + loginAttempts + " times.");
		                }
		                popup.getChildren().add(text);
		                 
		                Scene dialogScene = new Scene(popup, 400, 200);
		                dialog.setScene(dialogScene);
		                dialog.show(); 
					}
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			 
		});
		
		/*
		 * permanent search bar header 
		 * adding them directly to root keeps them at the top 
		 */ 
		top.setVgap(3); // gap between rows
		top.setHgap(3); // gap between columns 
		String[] sortBy = { "A-Z", "Z-A", "Number of Limbs", "Preferred Biome", "Lifespan", "Diet",
				"Travel Method", "Cold Blooded", "Warm Blooded" };
		ComboBox<String> sortByDropDown = makeDropDown(sortBy);
		
		// search bar 
		TextField searchBar = makeTF("Search the Cat-o-log");
		
		// submit the search button 
		Button submit = makeButton("Search");
		submit.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent e) 
			{
				String search = ""; 
				search = searchBar.getText();
				frontPage = animalCatalog.searchFor(search);
				frontPage = animalCatalog.createSmallArray(frontPage);
				Arrays.sort(frontPage);
				refreshAnimalGrid(constantLogin);
			}
		});
		constantLogin.addRow(0, searchBar, submit);
		
		/* drop down sortBy
		 * each is alphabetical as default 
		 * for example if method of travel is chosen then 
		 * it goes fly slither swim walk 
		 * 
		 * with each animal sorted by the travel method
		 * alphabetically first 
		 * 
		 * cold blooded then warm blooded if cold blooded is chosen
		 * 
		 *  repetitive code since I can't make global variables
		 *  that a function/method would need  
		 *  because of the throws IOException 
		 *  
		 *  
		*/ 
		sortByDropDown.setPromptText("Sort By");
		sortByDropDown.setMaxHeight(20);
		sortByDropDown.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				String chosen = sortByDropDown.getValue(); 
				if(chosen.contentEquals("A-Z"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage);
					refreshAnimalGrid(constantLogin);
				}
				else if(chosen.contentEquals("Z-A"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortReverse());
					refreshAnimalGrid(constantLogin);
				}
				else if(chosen.contentEquals("Number of Limbs"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortLimbs());
					refreshAnimalGrid(constantLogin);
				}
				else if(chosen.contentEquals("Preferred Biome"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortBiome());
					refreshAnimalGrid(constantLogin);
				}
				else if(chosen.contentEquals("Travel Method"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortTravel());
					refreshAnimalGrid(constantLogin);
				}
				else if(chosen.contentEquals("Cold Blooded"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortCold());
					refreshAnimalGrid(constantLogin);
				}
				else if(chosen.contentEquals("Warm Blooded"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortWarm());
					refreshAnimalGrid(constantLogin);
				}
				else if(chosen.contentEquals("Lifespan"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortLifespan());
					refreshAnimalGrid(constantLogin);
				}
				else if(chosen.contentEquals("Diet"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortDiet());
					refreshAnimalGrid(constantLogin);
				}
			}
		});
		
		top.addRow(0, searchBar, submit); 
		top.addRow(1, tp, sortByDropDown);
		root.getChildren().add(top);
		root.setStyle("-fx-background-color: MediumAquaMarine;");
		root.getChildren().add(grid);
		root.getChildren().add(sp);
		
        Scene startPage = new Scene(root, 500, 500);
		primaryStage.setScene(startPage);
		primaryStage.show();
	}
	
	/*
	 * standardizes the check boxes  
	 */
	CheckBox makeChkBox(String boxName) 
	{
		CheckBox temp = new CheckBox(boxName);
		temp.setMaxHeight(5);
		temp.setStyle(
			      "-fx-color: MediumAquaMarine; " // make this color match the animal front page
			    + "-fx-font-size: 12;"
			    + ";"
			);
		//temp.prefHeightProperty().bind(root.heightProperty());
		//temp.prefWidthProperty().bind(root.widthProperty());

		return temp;
	}
	
	/*
	 * standardizes the buttons
	 */
	Button makeButton(String str) 
	{
		Button temp = new Button(str);
		temp.setMaxHeight(50);
		// submit.setMaxWidth(500); need to find a way to set limits better
		temp.prefHeightProperty().bind(root.heightProperty());
		temp.prefWidthProperty().bind(root.widthProperty());

		return temp;
	}
	
	/*
	 * a tailored drop down (combobox) 
	 * for our needs 
	 */
	ComboBox<String> makeDropDown(String[] values) 
	{
		ComboBox temp = new ComboBox(FXCollections.observableArrayList(values));

		temp.setMaxHeight(50);
		temp.prefHeightProperty().bind(root.heightProperty());
		temp.prefWidthProperty().bind(root.widthProperty());

		return temp;
	}

	/*
	 * standardizes the textboxes 
	 */
	TextField makeTF(String text) 
	{
		TextField temp = new TextField();
		temp.setMaxHeight(50);
		
		temp.setPromptText(text);
		temp.prefHeightProperty().bind(root.heightProperty());
		temp.prefWidthProperty().bind(root.widthProperty());

		return temp;
	}
	
	/*
	 * Does the sign up stuff 
	 */
	void signUpPopUp()
	{
		Stage signup = new Stage();
		StackPane signupSP = new StackPane();
        signupSP.setStyle("-fx-background-color: MediumAquaMarine");
        
        TextField newUsername = new TextField(); 
        newUsername.setPromptText("Username");
        PasswordField newPassword = new PasswordField(); 
        newPassword.setPromptText("Password");
        PasswordField newPasswordCheck = new PasswordField();
        newPasswordCheck.setPromptText("Re-Enter Password");
        TextField newFN = new TextField(); 
        newFN.setPromptText("First Name");
        TextField newLN = new TextField(); 
        newLN.setPromptText("Last Name");
        TextField newE = new TextField();
        newE.setPromptText("Email");
        TextField newP = new TextField();
        newP.setPromptText("Phone Number");
        Button signingUp = new Button("Sign Up");
        
        GridPane signUpGP = new GridPane(); 
        signUpGP.prefWidthProperty().bind(signupSP.widthProperty());
        signUpGP.prefHeightProperty().bind(signupSP.heightProperty());
        signUpGP.setVgap(10); // gap between rows
        signUpGP.setHgap(10); // gap between columns
        signUpGP.addRow(0, newFN, newLN);
        signUpGP.addRow(1, newE, newP);
        signUpGP.addRow(2, newUsername);
        signUpGP.addRow(3, newPassword, newPasswordCheck);  
        signUpGP.addRow(4, signingUp);
        signupSP.getChildren().add(signUpGP);
        signUpGP.setAlignment(Pos.CENTER);
        signingUp.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				String p = newPassword.getText(); 
				String pc = newPasswordCheck.getText();
				String u = newUsername.getText(); 
				String e = newE.getText(); 
				String f = newFN.getText(); 
				String l = newLN.getText();
				String pn = newP.getText(); 
				// if the passwords do not match 
				if(p.equals(pc) == false)
		        {
					Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(signup);
	                
	                StackPane popup = new StackPane();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Your passwords do not match.");
	                
	                popup.getChildren().add(text);
	                 
	                Scene dialogScene = new Scene(popup, 200, 200);
	                dialog.setScene(dialogScene);
	                dialog.show(); 
		        }
				// if the password textfield is empty 
				else if(p.isEmpty()) 
				{
					Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(signup);
	                
	                StackPane popup = new StackPane();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Please enter in a password.");
	                
	                popup.getChildren().add(text);
	                 
	                Scene dialogScene = new Scene(popup, 200, 200);
	                dialog.setScene(dialogScene);
	                dialog.show(); 
				}
				// if the username textfield is empty 
				else if(u.isEmpty())
				{
					Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(signup);
	                
	                StackPane popup = new StackPane();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Please enter in a username.");
	                
	                popup.getChildren().add(text);
	                 
	                Scene dialogScene = new Scene(popup, 200, 200);
	                dialog.setScene(dialogScene);
	                dialog.show(); 
				}
				else if(e.isEmpty())
				{
					Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(signup);
	                
	                StackPane popup = new StackPane();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Please enter in an email.");
	                
	                popup.getChildren().add(text);
	                 
	                Scene dialogScene = new Scene(popup, 200, 200);
	                dialog.setScene(dialogScene);
	                dialog.show(); 
				}
				else
				{
					User newUser = new User(u, p, e, pn, f, l); 
					
					try {
						Accounts acc = new Accounts();
						acc.Makaccount(newUser);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					Text done = new Text("Thank you for signing up!");
					signupSP.getChildren().clear();
					signupSP.getChildren().add(done);
				}
			}
		});
        
        Scene dialogScene = new Scene(signupSP, 400, 400);
        
        signup.setScene(dialogScene);
        signup.show(); 
	}

	/*
	 * creates the animal grid that is added to the scroll pane 
	 */
	GridPane animalGrid(Animal[] useThese)
	{
		frontPage = useThese; 
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: LightSkyBlue;"); // background color
		grid.setVgap(10); // gap between rows
		grid.setHgap(10); // gap between columns
		grid.setPadding(new Insets(10, 10, 10, 10)); // pads edges 
		grid.prefWidthProperty().bind(root.widthProperty());
		grid.prefHeightProperty().bind(root.heightProperty());
		// eight columns under row 0 for the animals 
		Text name = new Text("Name");
		name.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		//name.setFill(Color.DARKCYAN);
		Text prefBiome = new Text("Preferred Biome");
		prefBiome.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text classAni = new Text("Class");
		classAni.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text numLi = new Text("Limb Number");
		numLi.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text mOT = new Text("Travel Method");
		mOT.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text bT = new Text("Blood Type");
		bT.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text d = new Text("Diet");
		d.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		Text lS = new Text("Average Lifespan");
		lS.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		grid.addRow(1, name, classAni, mOT, numLi, prefBiome, bT, d, lS);
		
		int gridRow = 2; 
		for(int c = 0; c < frontPage.length && frontPage[c] != null; c++)
		{
			Button tempName = new Button(frontPage[c].getName());
			Text tempClass = new Text(frontPage[c].getAnimalClass());
			String[] tm = frontPage[c].getTravelMethods();
			Text tempMOT = new Text(tm[0]);
			Text tempNL = new Text(String.valueOf(frontPage[c].getNumOfLimbs()));
			Text tempPB = new Text(frontPage[c].getPreferredBiome());
			int tbt = (frontPage[c].getColdOrWarmBlooded());
			Text tempBT = new Text("");
			if(tbt == 1)
				tempBT = new Text("Cold");
			else 
				tempBT = new Text("Warm");
			Text tempD = new Text(frontPage[c].getDiet());
			Text tempLS = new Text(String.valueOf(frontPage[c].getAverageLifespan()));
			grid.addRow(gridRow, tempName, tempClass, tempMOT, tempNL, tempPB, tempBT, tempD, tempLS);
			tempName.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					Animal lookAt = null; 
					for(int l = 0; l < frontPage.length && frontPage[l] != null; l++)
					{
						if(frontPage[l].getName().equalsIgnoreCase(tempName.getText()))
							lookAt = frontPage[l];
					}
					try {
						animalPersonalPage(lookAt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			gridRow++;
		}
		return grid; 
	}
	
	void refreshAnimalGrid(GridPane constantLogin)
	{
		root.getChildren().clear(); 
		GridPane g = animalGrid(frontPage);
		g.setVgap(3); // gap between rows
		g.setHgap(3); // gap between columns
		root.getChildren().clear(); 
		top.setVgap(3); // gap between rows
		top.setHgap(3); // gap between columns
		root.getChildren().add(constantLogin);
		ScrollPane s = new ScrollPane(g);
		root.getChildren().add(top);
		root.getChildren().add(s);
	}
	
	/*
	 * Activated by clicking the apply filters button 
	 * checks to see which check boxes are selected to be filtered 
	 * and does so 
	 */
	void checkCheckboxes() throws IOException
	{
		Catalog animalCatalog = new Catalog(); 
		frontPage = null; 
		ListIterator<CheckBox> c = checked.listIterator(); 
		
		// goes through the linked list 
		while(c.hasNext())
		{
			CheckBox temp = c.next(); 
			if(temp.getText().equals("Limit Limbs"))
			{
				/*
				 * if they only enter in the upper limit 
				 * 			the lower limit is automatically 0 
				 * else if they enter in an upper limit that is higher than or equal to the lower 
				 * 			both limits sent 
				 * else if lower is >= 0 and upper empty 
				 * 			lower is the lower bound 
				 * else if they enter in a lower limit that is greater than upper limit 
				 * 			they are swapped 
				 * and if both are negative then no filters are applied 
				 */
				if(lowerLimbLimit < 0 && upperLimbLimit >= 0)
					frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.rangeAnimals(0, upperLimbLimit, 1));
				else if(lowerLimbLimit >= 0 && upperLimbLimit >= lowerLimbLimit)
					frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.rangeAnimals(lowerLimbLimit, upperLimbLimit, 1)); 
				else if(lowerLimbLimit >= 0 && upperLimbLimit < 0)
					frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.rangeAnimals(lowerLimbLimit, Integer.MAX_VALUE, 1)); 
				else if(lowerLimbLimit >= 0 && upperLimbLimit < lowerLimbLimit)
					frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.rangeAnimals(upperLimbLimit, lowerLimbLimit, 1)); 
			}
			
			if(temp.getText().equals("Average Lifespan"))
			{
				/*
				 * if they only enter in the upper limit 
				 * 			the lower limit is automatically 0 
				 * else if they enter in an upper limit that is higher than or equal to the lower 
				 * 			both limits sent 
				 * else if lower is >= 0 and upper empty 
				 * 			lower is the lower bound 
				 * else if they enter in a lower limit that is greater than upper limit 
				 * 			they are swapped 
				 * and if both are negative then no filters are applied 
				 */
				if(lowerAgeLimit <= 0 && upperAgeLimit >= 0)
					frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.rangeAnimals(0, upperAgeLimit, 0));
				else if(lowerAgeLimit >= 0 && upperAgeLimit >= lowerAgeLimit)
					frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.rangeAnimals(lowerAgeLimit, upperAgeLimit, 0)); 
				else if(lowerAgeLimit >= 0 && upperAgeLimit < 0)
					frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.rangeAnimals(lowerAgeLimit, 700, 0)); 
				else if(lowerAgeLimit >= 0 && upperAgeLimit < lowerAgeLimit)
					frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.rangeAnimals(upperAgeLimit, lowerAgeLimit, 0)); 
			}
			
			if(temp.getText().equals("Mammals"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Mammal", 2)); 
			}
			
			if(temp.getText().equals("Fish"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Fish", 2)); 
			}
			if(temp.getText().equals("Birds"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Bird", 2)); 
			}
			if(temp.getText().equals("Reptiles"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Reptile", 2)); 
			}
			if(temp.getText().equals("Insect"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Insect", 2)); 
			}
			if(temp.getText().equals("Amphibians"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Amphibian", 2)); 
			}
			if(temp.getText().equals("Arthropods"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Arthropod", 2)); 
			}
			if(temp.getText().equals("Tundra"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Tundra", 3)); 
			}
			if(temp.getText().equals("Taiga"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Taiga", 3)); 
			}
			if(temp.getText().equals("Temperate Deciduous Forest"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Temperate Deciduous Forest", 3)); 
			}
			if(temp.getText().equals("Scrub Forest"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Scrub Forest", 3)); 
			}
			if(temp.getText().equals("Grassland"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Grassland", 3)); 
			}
			if(temp.getText().equals("Desert"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Desert", 3)); 
			}
			if(temp.getText().equals("Tropical Rain Forest"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Tropical Rain Forest", 3)); 
			}
			if(temp.getText().equals("Temperate Rain Forest"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Temperate Rain Forest", 3)); 
			}
			if(temp.getText().equals("Aquatic"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Aquatic", 3)); 
			}
			if(temp.getText().equals("Flies"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Flies", 1)); 
			}
			if(temp.getText().equals("Slithers"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Slithers", 1)); 
			}
			if(temp.getText().equals("Swims"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Swims", 1)); 
			}
			if(temp.getText().equals("Walk"))
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals("Walk", 1)); 
			}
			
			if(dietDropChoice.length() != 0)
			{
				frontPage = animalCatalog.joinArrays(frontPage, animalCatalog.filterAnimals(dietDropChoice, 4)); 
			}
			
		}
		
		refreshAnimalGrid(constantLogin);
	}
	
	/*
	 * Animal's personal page where 
	 * 	logged in users can 
	 * 			add comments
	 * 			
	 * 	not logged in users can 
	 * 			view comments 
	 * 
	 */
	void animalPersonalPage(Animal weirdDog) throws IOException
	{
		root.getChildren().clear(); 
		GridPane info = new GridPane(); 
		info.setVgap(4);
		info.setHgap(4);
		info.prefWidthProperty().bind(root.widthProperty());
		info.prefHeightProperty().bind(root.heightProperty());
		info.setStyle("-fx-background-color: LightSkyBlue;"); // background color
		
		// labels stuff 
		Text a = new Text("Name: ");
		a.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Text b = new Text("Preferred Biome: ");
		b.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Text c = new Text("Class: "); 
		c.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Text d = new Text("Diet Type: "); 
		d.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Text e = new Text("Common Region: "); 
		e.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Text f = new Text("Blood Type: "); 
		f.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Text g = new Text("Limbs: "); 
		g.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Text h = new Text("Average Lifespan: ");
		h.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Text i = new Text("Travel Methods: "); 
		i.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		// make a place for photos at top of the grid
		
		info.addColumn(1, a, c, d, e, b, g, f, h, i);
		
		// animal info 
		a = new Text(weirdDog.getName()); 
		a.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		b = new Text(weirdDog.getPreferredBiome()); 
		b.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		c = new Text(weirdDog.getAnimalClass());
		c.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		d = new Text(weirdDog.getDiet());
		d.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		e = new Text(weirdDog.getCommonRegion());
		e.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		if(weirdDog.getColdOrWarmBlooded() == 0)
			f = new Text("Warm");
		else 
			f = new Text("Cold");
		f.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		g = new Text(weirdDog.getNumOfLimbs() + "");
		g.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		h = new Text(weirdDog.getAverageLifespan() + " years"); 
		h.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		
		String travelMetho = ""; 
		String[] tm = weirdDog.getTravelMethods();
		
		int k = 3; 
		for(int j = 0; j < tm.length && tm[j] != null; j++)
		{
			travelMetho += tm[j];
			if(k > 0)
				travelMetho += ", "; 
			else 
				travelMetho += " "; 
			k--; 
		}
		
		i = new Text(travelMetho); 
		i.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
		
		info.addColumn(2, a, c, d, e, b, g, f, h, i);
		Text commentsHeader = new Text("Comments");
		commentsHeader.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		info.addColumn(3, commentsHeader);
		
		// can comment  
		if(!loggedInUN.equals(""))
		{
			TextArea newComment = new TextArea(); 
			newComment.setPromptText("Type Your Comment Here");
			Button addComment = makeButton("Submit Comment");
			
			addComment.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					try {
						weirdDog.addComment(newComment.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					newComment.clear();
				}
			});
			
			info.addColumn(3, newComment,addComment);
		}
		
		String[] currentComments = weirdDog.getComments(); 
		for(int x = 0; x < currentComments.length; x++)
		{
			Text te = new Text(currentComments[x]); 
			info.addColumn(3, te);
		}
			
		ScrollPane scroller = new ScrollPane(info); 
		root.getChildren().add(constantLogin);
		root.getChildren().add(top); 
		root.getChildren().add(scroller); 
	}
	
	void userPersonalPage()
	{
		root.getChildren().clear(); 
		GridPane userPage = new GridPane();
		userPage.setVgap(4);
		userPage.setHgap(4);
		userPage.prefWidthProperty().bind(root.widthProperty());
		userPage.prefHeightProperty().bind(root.heightProperty());
		userPage.setStyle("-fx-background-color: CadetBlue;"); // background color
		
		Text i = new Text("Your Listed Information"); 
		Text un = new Text("Username: " + currentUser.username);
		Text p = new Text("Phone Number: " + currentUser.getPhoneNumber()); 
		Text e = new Text("Email: " + currentUser.getEmail()); 
		Button cu = makeButton("Change Username"); 
		cu.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				try {
					changeUsernamePopup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button cp = makeButton("Change Password"); 
		cp.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				try {
					changePasswordPopup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button ce = makeButton("Change or Add Email"); 
		ce.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				try {
					changeEmailPopup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		
		Button cph = makeButton("Change or Add Phone Number"); 
		cph.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				try {
					changePhonePopup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button ra = makeButton("Request Animal"); 
		ra.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				requestAnimal();
			}
		});
		cp.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				try {
					changePasswordPopup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button seeRequests = makeButton("Animal Requests"); 
		seeRequests.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				try {
					viewAnimalRequests();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		userPage.addColumn(0, i, un, p, e, cu, cp, ce, cph, ra);
		
		if(currentUser.administrator)
			userPage.addColumn(0, seeRequests);
		
		ScrollPane scroller = new ScrollPane(userPage); 
		root.getChildren().add(constantLogin);
		root.getChildren().add(top); 
		root.getChildren().add(scroller); 
	}
	
	void changePasswordPopup() throws IOException
	{
		Accounts acc = new Accounts(); 
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL); 
        
        StackPane popup = new StackPane();
        popup.setStyle("-fx-background-color: AZURE");
        
        TextField oldP = makeTF("Confirm Current Password"); 
        TextField newP = makeTF("New Password"); 
        TextField newPC = makeTF("Confirm New Password"); 
        Button pB = makeButton("Change Password"); 
        
        GridPane cp = new GridPane();
		cp.setVgap(4);
		cp.setHgap(4);
		cp.prefWidthProperty().bind(popup.widthProperty());
		cp.prefHeightProperty().bind(popup.heightProperty());
		cp.setStyle("-fx-background-color: DarkSeaGreen;"); // background color
		
		cp.addColumn(0, oldP, newP, newPC, pB);
		ScrollPane s = new ScrollPane(cp); 
		popup.getChildren().add(s); 
		Scene dialogScene = new Scene(popup, 300, 300);
		
        pB.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				if(loggedInPW.contentEquals(""));
				{
					System.out.println("error");
				}
				if(loggedInPW.equals((oldP.getText().trim())))
				{
					try {
						acc.changePassword(loggedInUN, newP.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					cp.getChildren().clear();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Your password has been changed!");
	                
	                popup.getChildren().add(text);
				}
			}
		});
        
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	
	/*
	 * Only an administrator can edit animal requests 
	 * 
	 */
	void viewAnimalRequests() throws IOException
	{
		root.getChildren().clear(); 
		
		GridPane reqs = new GridPane();
		reqs.setVgap(4);
		reqs.setHgap(4);
		reqs.prefWidthProperty().bind(root.widthProperty());
		reqs.prefHeightProperty().bind(root.heightProperty());
		reqs.setStyle("-fx-background-color: DarkSeaGreen;"); // background color
		
		Text name = new Text("Name : "); 
		
		reqs.addColumn(0, name);
		
		AnimalRequests requests = new AnimalRequests(); 
		Request[] reqArr = requests.getRequests(); 
		
		for(int c = 0; c < reqArr.length; c++)
		{
			Text temp = new Text(reqArr[c].getName()); 
			Button deleteReq = makeButton("Deny Request"); 
			Button approveReq = makeButton("Approve Request");
			Button viewReq = makeButton("View Full Request");
			reqs.addRow(c, temp, viewReq, approveReq, deleteReq);
			
			
			Request lookAt = null; 
			for(int l = 0; l < reqArr.length && reqArr[l] != null; l++)
			{
				if(reqArr[l].getName().equalsIgnoreCase(temp.getText()))
					lookAt = reqArr[l];
			}
			
			Request aReq = lookAt; // because javafx doesn't like to make my life easy 
			int x = c; // because javafx doesn't like to make my life easy 
			deleteReq.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					requests.removeRequest(aReq); 
				}
			});
			
			approveReq.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					
					try {
						requests.addAnimal(new Catalog(), aReq);
						requests.removeRequest(aReq); 
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
			viewReq.setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					
					Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					StackPane popup = new StackPane();
				    popup.setStyle("-fx-background-color: AZURE");
				    
				    GridPane cp = new GridPane(); 
				    
				    Text a = new Text("Name: " + aReq.name);
					a.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
					Text b = new Text("Preferred Biome: " + aReq.prefferedBiome);
					b.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
					Text c = new Text("Class: " + aReq.animalClass); 
					c.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
					Text d = new Text("Diet Type: " + aReq.diet); 
					d.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
					Text e = new Text("Common Region: " + aReq.commonRegion); 
					e.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
					Text f = new Text("Blood Type: " + (aReq.coldOrWarmBlooded == 0 ? "Warm": "Cold")); 
					f.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
					Text g = new Text("Limbs: " + aReq.numOfLimbs); 
					g.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
					Text h = new Text("Average Lifespan: " + aReq.averageLifespan);
					h.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
					Text i = new Text("Travel Methods: " + aReq.methodsOfTravel[0]); 
					i.setFont(Font.font("verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
				    cp.addColumn(0, a, b, c, d, e, f, g, h, i);
				    
				    ScrollPane s = new ScrollPane(cp); 
					popup.getChildren().add(s); 
					Scene dialogScene = new Scene(popup, 300, 300);
				    
				    dialog.setScene(dialogScene);
				    dialog.show();
				}
			});
		}
		
		
		
		
		
		ScrollPane scroller = new ScrollPane(reqs); 
		root.getChildren().add(constantLogin);
		root.getChildren().add(top); 
		root.getChildren().add(scroller); 
	}
	
	/*
	 * the popup that allows the user to change or add a phone number 
	 */
	void changePhonePopup() throws IOException
	{
		Accounts acc = new Accounts(); 
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(scroller);
        
        StackPane popup = new StackPane();
        popup.setStyle("-fx-background-color: AZURE");
        
        TextField newN = makeTF("New Phone Number"); 
        Button aN = makeButton("Add Phone Number"); 
        
        Button rP = makeButton("Replace Phone Number"); 
        
        GridPane cp = new GridPane();
		cp.setVgap(4);
		cp.setHgap(4);
		cp.prefWidthProperty().bind(popup.widthProperty());
		cp.prefHeightProperty().bind(popup.heightProperty());
		cp.setStyle("-fx-background-color: DarkSeaGreen;"); // background color
		
		cp.addColumn(0, newN, aN, rP);
		ScrollPane s = new ScrollPane(cp); 
		popup.getChildren().add(s); 
		Scene dialogScene = new Scene(popup, 300, 300);
		aN.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				String[] phs = {newN.getText(), currentUser.getPhoneNumber()};
				currentUser.setPhoneNumber(phs); 
				
				cp.getChildren().clear();
                popup.setStyle("-fx-background-color: AZURE");
                Text text = new Text("Your phone number has been added!");
                
                popup.getChildren().add(text); 
			}
		});
        rP.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
					try {
						acc.changePhoneNumber(loggedInUN, newN.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					cp.getChildren().clear();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Your phone number has been changed!");
	                
	                popup.getChildren().add(text); 
				}
			
		});
        
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	void changeUsernamePopup() throws IOException
	{
		Accounts acc = new Accounts(); 
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(scroller);
        
        StackPane popup = new StackPane();
        popup.setStyle("-fx-background-color: AZURE");
        
        TextField oldU = makeTF("Confirm Current Username"); 
        TextField newU = makeTF("New Username"); 
        Button uB = makeButton("Change Username"); 
        
        GridPane cp = new GridPane();
		cp.setVgap(4);
		cp.setHgap(4);
		cp.prefWidthProperty().bind(popup.widthProperty());
		cp.prefHeightProperty().bind(popup.heightProperty());
		cp.setStyle("-fx-background-color: DarkSeaGreen;"); // background color
		
		cp.addColumn(0, oldU, newU, uB);
		ScrollPane s = new ScrollPane(cp); 
		popup.getChildren().add(s); 
		Scene dialogScene = new Scene(popup, 300, 300);
		
        uB.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				
					try {
						acc.changeUsername(loggedInUN, newU.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					cp.getChildren().clear();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Your username has been changed! Logout and back in to update.");
	                
	                popup.getChildren().add(text); 
			}
		});
        
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	/*
	 * can create a request that is saved until approved or deleted 
	 */
	void requestAnimal()
	{
		root.getChildren().clear(); 
		
		GridPane userPage = new GridPane();
		userPage.setVgap(4);
		userPage.setHgap(4);
		userPage.prefWidthProperty().bind(root.widthProperty());
		userPage.prefHeightProperty().bind(root.heightProperty());
		userPage.setStyle("-fx-background-color: DarkSeaGreen;"); // background color
		
		TextField animalName = makeTF("Animal Name"); 
		TextField animalDiet= makeTF("Animal Diet"); 
		TextField animalClass = makeTF("Animal Class");
		TextField animalRegion = makeTF("Animal Region"); 
		TextField animalBiome = makeTF("Animal Biome"); 
		TextField animalLimbs = makeTF("Animal Limbs");
		TextField animalLS = makeTF("Animal LifeSpan"); 
		TextField animalTM = makeTF("Animal Main Travel Method"); 
		TextField animalB = makeTF("Animal Bloodtype, 0 = Warm and 1 = Cold"); 
		Button submitRequest = makeButton("Submit Request");
		submitRequest.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				String[] tm = {animalTM.getText().isEmpty()? "N/A" : animalTM.getText()}; 
				int li = 0; 
				if(!animalLimbs.getText().isEmpty())
				{
					li = Integer.parseInt(animalLimbs.getText()); 
				}
				int lis = 0; 
				if(!animalLS.getText().isEmpty())
				{
					lis = Integer.parseInt(animalLS.getText()); 
				}
				// if the user does not enter in anything then "N/A" is entered 
				Request newR = new Request(animalName.getText().isEmpty()? "N/A" : animalName.getText(),
										   animalDiet.getText().isEmpty()? "N/A" : animalDiet.getText(), 
										   animalBiome.getText().isEmpty()? "N/A" : animalBiome.getText(),
										   animalRegion.getText().isEmpty()? "N/A" : animalRegion.getText(),
										   animalClass.getText().isEmpty()? "N/A" : animalClass.getText(),
										   li, lis, tm,
										   animalB.getText().isEmpty()? "-99" : animalB.getText());
				
				AnimalRequests requests = new AnimalRequests(); 
				try {
					requests.addRequest(newR);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				// request sent popup 
				Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                //dialog.initOwner(scroller);
                
                StackPane popup = new StackPane();
                popup.setStyle("-fx-background-color: AZURE");
                Text text = new Text("Thank you for requesting an animal!");
                
                popup.getChildren().add(text);
                
                Scene dialogScene = new Scene(popup, 300, 300);
                dialog.setScene(dialogScene);
                dialog.show();
			}
		});
		
		userPage.addColumn(0, animalName, animalClass, animalBiome, animalLimbs, animalLS,
				animalRegion, animalDiet, animalB, animalTM, submitRequest);
		
		ScrollPane scroller = new ScrollPane(userPage); 
		root.getChildren().add(constantLogin);
		root.getChildren().add(top); 
		Text a = new Text("Enter in the animal you would like the Cat-o-log to add!");
		root.getChildren().add(a); 
		root.getChildren().add(scroller); 
	}
	
	/*
	 * This popup is how the user changes their email or 
	 * adds one to their account 
	 */
	void changeEmailPopup() throws IOException
	{
		Accounts acc = new Accounts(); 
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(scroller);
        
        StackPane popup = new StackPane();
        popup.setStyle("-fx-background-color: AZURE");
        
        TextField newE = makeTF("New Email"); 
        Button aN = makeButton("Add Email"); 
       
        Button rP = makeButton("Replace Email"); 
        
        GridPane cp = new GridPane();
		cp.setVgap(4);
		cp.setHgap(4);
		cp.prefWidthProperty().bind(popup.widthProperty());
		cp.prefHeightProperty().bind(popup.heightProperty());
		cp.setStyle("-fx-background-color: DarkSeaGreen;"); // background color
		
		cp.addColumn(0, newE, aN, rP);
		ScrollPane s = new ScrollPane(cp); 
		popup.getChildren().add(s); 
		Scene dialogScene = new Scene(popup, 300, 300);
		aN.setOnAction(new EventHandler<ActionEvent>() 
		{
				@Override
				public void handle(ActionEvent event) 
				{
					String[] es = {newE.getText(), currentUser.getEmail()};
					currentUser.setEmail(es); 
					
					cp.getChildren().clear();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Your email has been added!");
	                
	                popup.getChildren().add(text); 
				}
			});
        rP.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
					try {
						acc.changeEmail(loggedInUN, newE.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					cp.getChildren().clear();
	                popup.setStyle("-fx-background-color: AZURE");
	                Text text = new Text("Your email has been changed!");
	                
	                popup.getChildren().add(text); 
				}
			
		});
        
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	/* 
	 * This stuff below 
	 * exists only because javafx can't handle button counters 
	 * or certain global variables 
	 */
	void counter()
	{
		loginAttempts++; 
	}
	
	void loggedIn()
	{
		isLoggedIn = true; 
		loginAttempts = 0;
	}
	
	void loggedOut()
	{
		isLoggedIn = false; 
		isAdminLoggedIn = false;
		currentUser = null; 
		loggedInUN = ""; 
		loggedInPW = "";
	}
}
