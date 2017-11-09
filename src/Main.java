

import com.reloadingapp.ballisticformulas.StandardDeviation;
import com.reloadingapp.gui.AlertPopup;
import com.reloadingapp.gui.StandardDeviationTable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectiles.NineMM;
import projectiles.Projectile;
import projectiles.TwoTwoThree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    private TableView<Projectile> table;
    private TextField weightInput, velocityInput;
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private List<Projectile> velocitySelections = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;
        window.setTitle("Reloading Program");

        //Projectile column
        TableColumn<Projectile, String> nameColumn = new TableColumn<>("Caliber");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("caliber"));

        //Weight column
        TableColumn<Projectile, Double> priceColumn = new TableColumn<>("Weight");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        //Velocity column
        TableColumn<Projectile, String> quantityColumn = new TableColumn<>("Velocity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("velocity"));

        TableColumn<Projectile, Double> muzzleColumn = new TableColumn<>("Muzzle Energy");
        muzzleColumn.setMinWidth(100);
        muzzleColumn.setCellValueFactory(new PropertyValueFactory<>("energy"));

        //Projectile type input

        choiceBox.getItems().add("9mm");
        choiceBox.getItems().add("223");
        choiceBox.setMinWidth(100);
        choiceBox.setValue("9mm");

        //Weight input
        weightInput = new TextField();
        weightInput.setPromptText("Weight");

        //Velocity input
        velocityInput = new TextField();
        velocityInput.setPromptText("Velocity");

        //Add button for adding new items to the table
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button getStandardDeviation = new Button("Get Standard Deviation");
        getStandardDeviation.setOnAction(e -> standardDeviationButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(choiceBox, weightInput, velocityInput, addButton, getStandardDeviation, deleteButton);

        table = new TableView<>();
        table.getSelectionModel().setCellSelectionEnabled(false);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn, muzzleColumn);
        table.setRowFactory(tv -> {
            TableRow<Projectile> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2){
                    Projectile clickedRow = row.getItem();
                    velocitySelections.add(clickedRow);
                    for(Projectile test : velocitySelections){
                        System.out.println(test.getVelocity());
                    }
                }
            });
        return row;
        });



        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    //Add button clicked
    private void addButtonClicked() {
        Projectile bullet = null;
        switch (choiceBox.getValue()) {
            case "9mm":
                bullet = new NineMM();
                bullet.setVelocity(Double.parseDouble(velocityInput.getText()));
                bullet.setWeightInGrains(Double.parseDouble(weightInput.getText()));
                bullet.calculateMuzzleEnergy();
                velocitySelections.add(bullet);
                break;
            case "223":
                bullet = new TwoTwoThree();
                bullet.setVelocity(Double.parseDouble(velocityInput.getText()));
                bullet.setWeightInGrains(Double.parseDouble(weightInput.getText()));
                bullet.calculateMuzzleEnergy();
                velocitySelections.add(bullet);
                break;
        }


        table.getItems().add(bullet);
        weightInput.clear();
        velocityInput.clear();
    }





    //Delete button clicked
    private void deleteButtonClicked() {
        ObservableList<Projectile> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        Projectile testRemove = table.getSelectionModel().getSelectedItem();
        velocitySelections.remove(testRemove);
        productSelected.forEach(allProducts::remove);
    }

    //Standard Deviation button clicked

    private void standardDeviationButtonClicked(){
        if(velocitySelections.size() == 0){
            AlertPopup.display("Error", "No velocities have been selected please select velocities to calculate first.");
        }
        else {
            StandardDeviationTable test = new StandardDeviationTable<>();
            StandardDeviation velocityDeviation = new StandardDeviation(velocitySelections);
            test.display("Standard Deviation", velocityDeviation.deviationsByCaliber());

        }
        }


    //Get all of the products
    private ObservableList<Projectile> getProduct() {
        ObservableList<Projectile> products = FXCollections.observableArrayList();
        Projectile nineMM = new NineMM(125, 950);
        nineMM.calculateMuzzleEnergy();
        products.add(nineMM);
        return products;
    }


}