package com.reloadingapp.gui;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.Map;

public class StandardDeviationTable<K, V> extends TableView<Map.Entry<K, V>> {

    public void display(String title, Map<K, V> deviations) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);


        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        TableColumn<Map.Entry<K, V>, K> caliberColumn = new TableColumn<>("Caliber");
        caliberColumn.setMinWidth(200);
        caliberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<K, V>, K>, ObservableValue<K>>() {
            @Override
            public ObservableValue<K> call(TableColumn.CellDataFeatures<Map.Entry<K, V>, K> param) {
                return new SimpleObjectProperty<K>(param.getValue().getKey());
            }
        });


        TableColumn<Map.Entry<K, V>,V> deviationColumn = new TableColumn<>("Deviation");
        deviationColumn.setMinWidth(200);
        deviationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<K, V>, V>, ObservableValue<V>>() {
            @Override
            public ObservableValue<V> call(TableColumn.CellDataFeatures<Map.Entry<K, V>, V> param) {
                return new SimpleObjectProperty<V>(param.getValue().getValue());
            }
        });

        ObservableList<Map.Entry<K, V>> items = FXCollections.observableArrayList(deviations.entrySet());
        this.setItems(items);
        this.getColumns().setAll(caliberColumn, deviationColumn);


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(closeButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(this, hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }



}
