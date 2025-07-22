package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaRemoverColecao {

    public static void exibir(GerenciadorBiblioteca gerenciador) {
        Stage stage = new Stage();
        stage.setTitle("Remover Coleção");

        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll(gerenciador.getNomesColecoes());

        Button btnRemover = new Button("Remover");
        btnRemover.setOnAction(e -> {
            String nome = combo.getValue();
            if (nome != null) {
                gerenciador.removerColecao(nome);
                stage.close();
            }
        });

        VBox layout = new VBox(10, new Label("Coleção para remover:"), combo, btnRemover);
        layout.setPadding(new javafx.geometry.Insets(10));
        stage.setScene(new Scene(layout, 350, 150));
        stage.show();
    }
}
