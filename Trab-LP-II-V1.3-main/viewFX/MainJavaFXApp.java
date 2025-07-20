ackage viewfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainJavaFXApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Biblioteca PDF");

        Button btnBiblioteca = new Button("Gerenciar Biblioteca");
        Button btnColecoes = new Button("Gerenciar Coleções");
        Button btnExportar = new Button("Exportar Coleções");

        // Listeners para navegação (a implementar nas próximas etapas)
        btnBiblioteca.setOnAction(e -> TelaBiblioteca.exibir());
        btnColecoes.setOnAction(e -> TelaColecoes.exibir());
        btnExportar.setOnAction(e -> TelaExportacao.exibir());

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(btnBiblioteca, btnColecoes, btnExportar);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 
