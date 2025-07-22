package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entrada;

import java.util.List;
import java.util.Map;

public class TelaListarEntradasPorColecao {

    public static void exibir(GerenciadorBiblioteca gerenciador) {
        Stage stage = new Stage();
        stage.setTitle("Listar Entradas por Coleção");

        ComboBox<String> comboColecoes = new ComboBox<>();
        comboColecoes.getItems().addAll(gerenciador.listarNomesColecoes());
        comboColecoes.setPromptText("Selecione uma coleção");

        ListView<String> listaEntradas = new ListView<>();

        comboColecoes.setOnAction(e -> {
            String nomeColecao = comboColecoes.getValue();
            listaEntradas.getItems().clear();
            if (nomeColecao != null) {
                List<Entrada> entradas = gerenciador.getEntradasDaColecao(nomeColecao);
                for (Entrada entrada : entradas) {
                    listaEntradas.getItems().add(entrada.getTitulo());
                }
            }
        });

        VBox layout = new VBox(10, comboColecoes, listaEntradas);
        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 400, 300));
        stage.show();
    }
} 
