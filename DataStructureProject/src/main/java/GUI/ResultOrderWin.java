package GUI;

import ProductOP.ProductNode;
import ProductOP.ProductOrder;
import ProductOP.ProductTree;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ResultOrderWin {

    private ProductTree productTree;
    private ProductOrder productOrder;

    public ResultOrderWin(ProductTree productTree , ProductOrder productOrder){
        this.productTree = productTree;
        this.productOrder = productOrder;
        Stage stage = new Stage();
        stage.setScene(paint());
        stage.show();
    }

    public Scene paint(){

        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);

        ArrayList<ProductNode> list = productTree.getList();

        for(int i=0; i<list.size(); i++){
            vBox.getChildren().add(table(list.get(i)));
        }

        vBox.setPadding(new Insets(50,100,50,100));
        BorderPane bPane = new BorderPane();
        bPane.setCenter(scrollPane);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(bPane);
        return scene;
    }

    public VBox table(ProductNode node){

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        TextField textField = new TextField();
        textField.setEditable(false);
        textField.setText("Item ID: "+node.getId()+" Lead Time = "+node.getLeadTime()+" Lot Sizing Rule = "+node.getLotRule());
        textField.setStyle("-fx-background-color: Green");
        textField.setPrefSize(280,20);
        vBox.getChildren().add(new HBox(textField));

        HBox hBox = new HBox();
        TextField textField1 = new TextField();
        textField1.setEditable(false);
        textField1.setStyle("-fx-border-color: Black");
        textField1.setText("Period");
        hBox.getChildren().add(textField1);

        for(int i=0; i<10; i++){
            TextField t = new TextField();
            t.setText(i+"");
            t.setPrefSize(50,20);
            t.setEditable(false);
            t.setStyle("-fx-border-color: Blue");
            hBox.getChildren().add(t);
        }

        vBox.getChildren().add(hBox);
        vBox.getChildren().add(hBox(node.getProductionLists().getGrossRequirement(),"Gross Requirement"));
        vBox.getChildren().add(hBox(node.getProductionLists().getScheduledReceipt(),"Scheduled Receipt"));
        vBox.getChildren().add(hBox(node.getProductionLists().getOnHandPriorPeriod(),"On Hand From Prior Period"));
        vBox.getChildren().add(hBox(node.getProductionLists().getNetRequirement(),"Net Requirement"));
        vBox.getChildren().add(hBox(node.getProductionLists().getTimePhasedNetRequirement(),"Time-Phased Net Requirement"));
        vBox.getChildren().add(hBox(node.getProductionLists().getPlannedOrderReleases(),"Planned Order Releases"));
        vBox.getChildren().add(hBox(node.getProductionLists().getPlannedOrderDelivery(),"Planned Order Deliver"));

        return vBox;
    }

    public HBox hBox(ArrayList<Integer> list, String str){

        HBox hBox1 = new HBox();
        TextField textField2 = new TextField();
        textField2.setEditable(false);
        textField2.setStyle("-fx-border-color: Black");
        textField2.setText(str);
        hBox1.getChildren().add(textField2);

        for(int i=0; i<10; i++){
            TextField t = new TextField();
            t.setText(list.get(i)+"");
            t.setPrefSize(50,20);
            t.setStyle("-fx-border-color: Red");
            t.setEditable(false);
            hBox1.getChildren().add(t);
        }
        return hBox1;
    }
}
