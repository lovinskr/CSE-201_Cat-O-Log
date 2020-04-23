
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
 * 
 * To Do:
 * get animals to have their own page 
 * sign up fully working 
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.Arrays;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
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
	private int width = 500;
	private int height = 500;
	int loginAttempts = 0;
	private boolean isLoggedIn = false; 
	VBox root = new VBox(); 
	CheckBox[] checked = new CheckBox[25]; // the list of check boxes checked or not
	int checkedIndex = 0; 
	Animal[] frontPage = new Animal[500];
	
	GridPane top = new GridPane(); 
	
	public static void main(String[] args) 
	{
		launch(args);
	}

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
		GridPane constantSearchAndLogin = new GridPane();
				
		// the check boxes that will be filters
		CheckBox chkColdBlooded = makeChkBox("Cold Blooded");
		chkColdBlooded.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{	
				chkColdBlooded.setStyle("-fx-color: LightSkyBlue");
				
				if(chkColdBlooded.isSelected() == false)
					chkColdBlooded.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("cold", 0);
					refreshAnimalGrid(constantSearchAndLogin);
				}
				
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
					chkWarmBlooded.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Warm", 0);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkFlies.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Flies", 1);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkSlithers.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Slithers", 1);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkWalk.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Walks", 1);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkSwim.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Swims", 1);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkFish.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Fish", 2);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkBird.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Bird", 2);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkReptiles.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Reptile", 2);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkInsect.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Insect", 2);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkAmphibians.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Amphibian", 2);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkMammal.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Mammal", 2);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkArthropods.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Arthropod", 2);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkAquatic.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Aquatic", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkTundra.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Tundra", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkTaiga.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Taiga", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkTDForest.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Temperate Deciduous Forest", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkScrubForest.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Scrub Forest", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkGrassland.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Grassland", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkDesert.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Desert", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkTempRForest.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Temperate Rain Forest", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkTropRForest.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Tropical Rain Forest", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkNearctic.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Nearctic", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkPalearctic.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Palearctic", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkAfrican.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("African", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkNeoTropical.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("NeoTropical", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkOriental.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Oriental", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
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
					chkAustralian.setStyle("-fx-color: MediumAquaMarine");
				else
				{
					frontPage = animalCatalog.filterAnimals("Australian", 3);
					refreshAnimalGrid(constantSearchAndLogin);
				}
			}
		});
		
		// Lifespan range
		CheckBox chkLifespan = makeChkBox("Average Lifespan");
		TextField lifeLimitL = makeTF(); // lower limb limit
		lifeLimitL.setPromptText("Min Years");
		lifeLimitL.setMaxHeight(20);
		TextField lifeLimitU = makeTF(); // upper limb limit
		lifeLimitU.setPromptText("Max Years");
		lifeLimitU.setMaxHeight(20); 
		chkLifespan.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				
			}
		});

		// have to include a text box to get the limb range
		CheckBox limbLimits = makeChkBox("Limit Limbs");
		TextField limbLimitL = makeTF(); // lower limb limit
		limbLimitL.setPromptText("Min Limbs"); 
		limbLimitL.setMaxHeight(20);
		TextField limbLimitU = makeTF(); // upper limb limit
		limbLimitU.setPromptText("Max Limbs");
		limbLimitU.setMaxHeight(20);
		limbLimits.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{	
				
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
				 checkCheckboxes(); 
			}
		});
		
		GridPane checkBoxes = new GridPane();
		checkBoxes.setVgap(4);
		checkBoxes.setPadding(new Insets(5, 5, 5,5));
		checkBoxes.add(cwBloodedTP, 0, 0);
		checkBoxes.add(animalClassTP, 0, 1);
		checkBoxes.add(biomesTP, 0, 2);
		checkBoxes.add(travelsByTP, 0, 3);
		checkBoxes.add(regionsTP, 0, 4); 
		checkBoxes.add(dietDropDown, 0, 5);
		checkBoxes.add(lifeLimitL, 0, 6);
		checkBoxes.add(lifeLimitU, 0, 7);
		checkBoxes.add(chkLifespan, 0, 8);
		checkBoxes.add(limbLimitL, 0, 9);
		checkBoxes.add(limbLimitU, 0, 10);
		checkBoxes.add(limbLimits, 0, 11);
		checkBoxes.add(filterB, 0, 12); 
		
		
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
	
		TextField UTF = makeTF(); // user name
		UTF.setPromptText("Username");
		PasswordField PTF = new PasswordField(); // password
		PTF.setMaxHeight(50);
		PTF.prefHeightProperty().bind(root.heightProperty());
		PTF.prefWidthProperty().bind(root.widthProperty());
		PTF.setPromptText("Password");
		Button login = makeButton("Login");
		constantSearchAndLogin.setVgap(3); // gap between rows
		constantSearchAndLogin.setHgap(3); // gap between columns
		constantSearchAndLogin.addRow(1, UTF, PTF, login, signUp);
		root.getChildren().addAll(constantSearchAndLogin);
		login.setOnAction(new EventHandler<ActionEvent>() 
		{
			 
			@Override
			public void handle(ActionEvent event) 
			{
				String username = UTF.getText().trim(); 
				String password = PTF.getText().trim(); 
				Text u = new Text(username); 
				
				// check with accounts class 
				try 
				{ 
					Accounts temp = new Accounts();
					boolean hasAccount = temp.hasAccount(username, password); 
					if(hasAccount == true && isLoggedIn == false)
					{
						loggedIn(); 
						login.setText("Logout");
						constantSearchAndLogin.getChildren().removeAll(UTF, PTF, signUp); 
						constantSearchAndLogin.getChildren().add(u);
					}
					else if(hasAccount == true && isLoggedIn == true)
					{
						loggedOut(); 
						login.setText("Login");
						constantSearchAndLogin.getChildren().clear();
						UTF.clear();
						PTF.clear();
						constantSearchAndLogin.addRow(0, UTF, PTF, login, signUp);
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
		TextField searchBar = makeTF();
		searchBar.setPromptText("Search the Cat-o-log");
		
		// submit the search button 
		Button submit = makeButton("Search");
		submit.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent e) 
			{
				String search = searchBar.getText();
				frontPage = animalCatalog.searchFor(search);
				GridPane g = animalGrid(frontPage);
				root.getChildren().clear();
				GridPane csl = new GridPane();
				root.getChildren().addAll(constantSearchAndLogin);
				ScrollPane s = new ScrollPane(g);
				
				GridPane t = new GridPane();
				t.addRow(0, searchBar, submit); 
				t.addRow(1, tp, sortByDropDown);
				t.setVgap(3); // gap between rows
				t.setHgap(3); // gap between columns
				root.getChildren().add(t);
				root.getChildren().add(s);
			}
		});
		constantSearchAndLogin.addRow(0, searchBar, submit);
		
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
					refreshAnimalGrid(constantSearchAndLogin);
				}
				else if(chosen.contentEquals("Z-A"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortReverse());
					refreshAnimalGrid(constantSearchAndLogin);
				}
				else if(chosen.contentEquals("Number of Limbs"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortLimbs());
					refreshAnimalGrid(constantSearchAndLogin);
				}
				else if(chosen.contentEquals("Preffered Biome"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortBiome());
					refreshAnimalGrid(constantSearchAndLogin);
				}
				else if(chosen.contentEquals("Travel Method"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortTravel());
					refreshAnimalGrid(constantSearchAndLogin);
				}
				else if(chosen.contentEquals("Cold Blooded"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortDiet());
					refreshAnimalGrid(constantSearchAndLogin);
				}
				else if(chosen.contentEquals("Warm Blooded"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortDiet());
					refreshAnimalGrid(constantSearchAndLogin);
				}
				else if(chosen.contentEquals("Lifespan"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortLifespan());
					refreshAnimalGrid(constantSearchAndLogin);
				}
				else if(chosen.contentEquals("Diet"))
				{
					frontPage = animalCatalog.createSmallArray(frontPage);
					Arrays.sort(frontPage, new SortDiet());
					refreshAnimalGrid(constantSearchAndLogin);
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
	TextField makeTF() 
	{
		TextField temp = new TextField();
		temp.setMaxHeight(50);
		
		temp.getText(); // use this to access text
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
					User newUser = new User(u, p); 
					String[] ee = {e};
					newUser.setEmail(ee);
					newUser.setFName(f);
					newUser.setLName(l);
					String[] pp = {pn};
					newUser.setPhoneNumber(pp);
					
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
					animalPersonalPage(tempName.getText());
				}
			});
			gridRow++;
		}
		return grid; 
	}
	
	void refreshAnimalGrid(GridPane constantSearchAndLogin)
	{
		root.getChildren().clear(); 
		GridPane g = animalGrid(frontPage);
		g.setVgap(3); // gap between rows
		g.setHgap(3); // gap between columns
		root.getChildren().clear(); 
		top.setVgap(3); // gap between rows
		top.setHgap(3); // gap between columns
		root.getChildren().add(constantSearchAndLogin);
		ScrollPane s = new ScrollPane(g);
		root.getChildren().add(top);
		root.getChildren().add(s);
	}
	
	/*
	 * will check all the other check boxes
	 */
	void checkCheckboxes()
	{
		Object[] f = root.getChildren().toArray(); 
		
		for(int c = 0; c < f.length; c++)
		{
			if(f[c] instanceof CheckBox)
			{
				CheckBox temp = (CheckBox) f[c]; 
				if(temp.isSelected())
				{
					String t = temp.getText(); 
					System.out.println(t); 
					
				}
			}
		}
		
	}
	
	void animalPersonalPage(String animalNametesting)
	{
		
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
	}
}
