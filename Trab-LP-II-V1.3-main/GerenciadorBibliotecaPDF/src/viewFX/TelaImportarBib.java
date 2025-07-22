package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TelaImportarBib {

    public static void exibir(GerenciadorBiblioteca gerenciador, Runnable aoAtualizar) {
        Stage stage = new Stage();
        stage.setTitle("Importar BibTeX");

        Label info = new Label("Selecione um arquivo .bib para importar:");
        Button btnSelecionar = new Button("Selecionar Arquivo");

        btnSelecionar.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Selecionar Arquivo BibTeX");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("BibTeX (*.bib)", "*.bib"));
            File arquivo = chooser.showOpenDialog(stage);
            if (arquivo != null) {
                gerenciador.importarBibTeX(arquivo.getAbsolutePath());
                aoAtualizar.run();
                stage.close();
            }
        });

        VBox layout = new VBox(10, info, btnSelecionar);
        layout.setPadding(new javafx.geometry.Insets(10));

        stage.setScene(new Scene(layout, 300, 150));
        stage.show();
    }
}
