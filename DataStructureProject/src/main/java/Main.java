import GUI.CreatingProductTreeWin;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        CreatingProductTreeWin creatingProductOrderWin = new CreatingProductTreeWin();
        primaryStage.setScene(creatingProductOrderWin.paint());
        primaryStage.show();
    }
}
