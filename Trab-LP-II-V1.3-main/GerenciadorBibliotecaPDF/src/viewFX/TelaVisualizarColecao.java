package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Colecao;
import model.Entrada;

public class TelaVisualizarColecao {

    public static void exibir(GerenciadorBiblioteca gerenciador) {
        Stage stage = new Stage();
        stage.setTitle("Visualizar Coleção");

        ComboBox<String> comboColecao = new ComboBox<>();
        comboColecao.getItems().addAll(gerenciador.getNomesColecoes());
        comboColecao.setPromptText("Selecione uma coleção");

        TextArea areaEntradas = new TextArea();
        areaEntradas.setEditable(false);

        comboColecao.setOnAction(e -> {
            String nome = comboColecao.getValue();
            Colecao colecao = gerenciador.getColecao(nome);
            if (colecao != null) {
                StringBuilder sb = new StringBuilder();
                for (Entrada entrada : colecao.getEntradas()) {
                    sb.append(entrada.resumo()).append("\n\n");
                }
                areaEntradas.setText(sb.toString());
            }
        });

        VBox layout = new VBox(10, comboColecao, areaEntradas);
        layout.setPadding(new javafx.geometry.Insets(10));

        stage.setScene(new Scene(layout, 600, 400));
        stage.show();
    }
} 
