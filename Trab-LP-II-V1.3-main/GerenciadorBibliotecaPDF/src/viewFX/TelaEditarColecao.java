package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEditarColecao {

    public static void exibir(GerenciadorBiblioteca gerenciador) {
        Stage stage = new Stage();
        stage.setTitle("Renomear Coleção");

        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll(gerenciador.getNomesColecoes());

        TextField campoNovoNome = new TextField();
        campoNovoNome.setPromptText("Novo nome da coleção");

        Button btnRenomear = new Button("Renomear");
        btnRenomear.setOnAction(e -> {
            String atual = combo.getValue();
            String novo = campoNovoNome.getText();
            if (atual != null && !novo.isBlank()) {
                gerenciador.renomearColecao(atual, novo);
                stage.close();
            }
        });

        VBox layout = new VBox(10, new Label("Coleção:"), combo, campoNovoNome, btnRenomear);
        layout.setPadding(new javafx.geometry.Insets(10));
        stage.setScene(new Scene(layout, 400, 200));
        stage.show();
    }
}
