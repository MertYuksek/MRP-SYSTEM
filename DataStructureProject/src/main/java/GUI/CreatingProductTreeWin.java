package GUI;

import ProductOP.ProductNode;
import ProductOP.ProductTree;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CreatingProductTreeWin {

    private int texts = 0;
    private Label info = new Label("Create Product Tree: ");
    private TextField idText = new TextField();
    private TextField nameText = new TextField();
    private Button addButton = new Button("Add Product");
    private Button prepareOrderButton = new Button("Prepare Order");
    private int parentId = 0;
    public ProductTree productTree = new ProductTree();

    public CreatingProductTreeWin(){

    }

    public Scene paint(){

        BorderPane bPane = new BorderPane();

        idText.setMaxSize(200,10);
        nameText.setMaxSize(200,10);
        nameText.setPromptText("Product Name / required");
        idText.setPromptText("Product ID");
        idText.setFocusTraversable(false);
        nameText.setFocusTraversable(false);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(info,idText,nameText,addButton,prepareOrderButton);

        Pane pane = new Pane();

        bPane.setCenter(pane);
        bPane.setBottom(vBox);

        addProductToTree(pane,bPane);
        giveProductOrder();

        Scene scene = new Scene(bPane,900,600);
        return scene;
    }

    public void addProductToTree (Pane pane, BorderPane bPane){

        addButton.setOnAction(e->{

            if(texts==0) {
                String[] tokenizer = nameText.getText().split("/");
                bPane.setCenter(showProductInPane(pane, Integer.parseInt(idText.getText()), nameText.getText()));
                productTree.add(parentId,new ProductNode(Integer.parseInt(idText.getText()),tokenizer[0],Integer.parseInt(tokenizer[1])));
                texts++;
            }
            else if(parentId!=0) {
                String[] tokenizer = nameText.getText().split("/");
                bPane.setCenter(showProductInPane(pane, Integer.parseInt(idText.getText()), nameText.getText()));
                productTree.add(parentId,new ProductNode(Integer.parseInt(idText.getText()),tokenizer[0],Integer.parseInt(tokenizer[1])));
            }
            else {
                System.out.println("You have to select product to create subproduct !!!");
            }
        });
    }

    public Pane showProductInPane(Pane pane,int id, String name){

        Text text = new Text(id+"/"+name);
        text.setX(100);
        text.setY(100);

        textAction(text,pane);

        text.setOnMouseDragged(e->{
            text.setX(e.getX());
            text.setY(e.getY());
        });

        pane.getChildren().addAll(text);
        return pane;
    }

    public void textAction(Text text , Pane pane){

        text.setOnMouseClicked(e->{

            if(e.getButton()== MouseButton.SECONDARY) {
                text.setFill(Color.BLUE);
                String[] list = text.getText().split("/");
                parentId = Integer.parseInt(list[0]);
            }

            else if(e.getButton() == MouseButton.MIDDLE){
                pane.getChildren().removeAll(text);
                String[] list = text.getText().split("/");
                //productTree.remove(new ProductNode(Integer.parseInt(list[0]),list[1]));
            }
        });
    }

    public void giveProductOrder(){

        prepareOrderButton.setOnAction(e->{
            CreatingProductOrderWin creatingProductOrderWin = new CreatingProductOrderWin(productTree);
        });
    }

    private ProductTree deneme(){

        //Deneme Kisimi

        ProductTree productTree = new ProductTree();

        productTree.root = new ProductNode(1605,"Shovel",1);
        productTree.root.childs.add(new ProductNode(13122,"Top Handle",1));
        productTree.root.childs.add(new ProductNode(48,"Scoop Shaft",1));
        productTree.root.childs.add(new ProductNode(118,"Shaft",1));
        productTree.root.childs.add(new ProductNode(62,"Nail",4));
        productTree.root.childs.add(new ProductNode(14127,"Rivet",4));
        productTree.root.childs.add(new ProductNode(314,"Scoop Assembly",1));

        productTree.root.childs.get(0).childs.add(new ProductNode(457,"Top Handle",1));
        productTree.root.childs.get(0).childs.add(new ProductNode(62,"Nail",2));
        productTree.root.childs.get(0).childs.add(new ProductNode(11495,"Bracelet",1));

        productTree.root.childs.get(0).setParent(productTree.root);
        productTree.root.childs.get(1).setParent(productTree.root);
        productTree.root.childs.get(2).setParent(productTree.root);
        productTree.root.childs.get(3).setParent(productTree.root);
        productTree.root.childs.get(4).setParent(productTree.root);
        productTree.root.childs.get(5).setParent(productTree.root);

        productTree.root.childs.get(5).childs.add(new ProductNode(2142,"Scoop",1));
        productTree.root.childs.get(5).childs.add(new ProductNode(19,"Blade",1));
        productTree.root.childs.get(5).childs.add(new ProductNode(14127,"Rivet",6));

        productTree.root.childs.get(0).childs.get(2).childs.add(new ProductNode(129,"Top Handle",1));
        productTree.root.childs.get(0).childs.get(2).childs.add(new ProductNode(1118,"Top Handle",1));

        productTree.root.childs.get(0).childs.get(0).setParent(productTree.root.childs.get(0));
        productTree.root.childs.get(0).childs.get(1).setParent(productTree.root.childs.get(0));
        productTree.root.childs.get(0).childs.get(2).setParent(productTree.root.childs.get(0));

        productTree.root.childs.get(0).childs.get(2).childs.get(0).setParent(productTree.root.childs.get(0).childs.get(2));
        productTree.root.childs.get(0).childs.get(2).childs.get(1).setParent(productTree.root.childs.get(0).childs.get(2));

        productTree.root.childs.get(5).childs.get(0).setParent(productTree.root.childs.get(5));
        productTree.root.childs.get(5).childs.get(1).setParent(productTree.root.childs.get(5));
        productTree.root.childs.get(5).childs.get(2).setParent(productTree.root.childs.get(5));

        return productTree;
    }

}
