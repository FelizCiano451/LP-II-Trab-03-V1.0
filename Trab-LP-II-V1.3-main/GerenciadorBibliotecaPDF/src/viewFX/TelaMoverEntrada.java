package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entrada;

public class TelaMoverEntrada {

    public static void exibir(GerenciadorBiblioteca gerenciador) {
        Stage stage = new Stage();
        stage.setTitle("Mover Entrada entre Coleções");

        ComboBox<String> comboEntradas = new ComboBox<>();
        for (Entrada e : gerenciador.listarEntradas()) {
            comboEntradas.getItems().add(e.getTitulo());
        }

        ComboBox<String> comboOrigem = new ComboBox<>();
        comboOrigem.getItems().addAll(gerenciador.getNomesColecoes());

        ComboBox<String> comboDestino = new ComboBox<>();
        comboDestino.getItems().addAll(gerenciador.getNomesColecoes());

        Button btnMover = new Button("Mover");
        btnMover.setOnAction(e -> {
            String titulo = comboEntradas.getValue();
            String origem = comboOrigem.getValue();
            String destino = comboDestino.getValue();
            if (titulo != null && origem != null && destino != null && !origem.equals(destino)) {
                gerenciador.moverEntradaEntreColecoes(titulo, origem, destino);
                stage.close();
            }
        });

        VBox layout = new VBox(10,
                new Label("Entrada:"), comboEntradas,
                new Label("De:"), comboOrigem,
                new Label("Para:"), comboDestino,
                btnMover);
        layout.setPadding(new javafx.geometry.Insets(10));
        stage.setScene(new Scene(layout, 400, 300));
        stage.show();
    }
}
