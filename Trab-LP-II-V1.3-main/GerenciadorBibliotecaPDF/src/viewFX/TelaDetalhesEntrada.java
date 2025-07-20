package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entrada;

public class TelaDetalhesEntrada {

    public static void exibir(Entrada entrada, GerenciadorBiblioteca gerenciador, Runnable onUpdate) {
        if (entrada == null) return;

        Stage stage = new Stage();
        stage.setTitle("Editar Entrada: " + entrada.getTitulo());

        TextField campoTitulo = new TextField(entrada.getTitulo());
        TextField campoAutor = new TextField(entrada.getAutor());

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            String novoTitulo = campoTitulo.getText();
            String novoAutor = campoAutor.getText();
            if (!novoTitulo.isBlank() && !novoAutor.isBlank()) {
                entrada.setTitulo(novoTitulo);
                entrada.setAutor(novoAutor);
                if (onUpdate != null) onUpdate.run();
                stage.close();
            }
        });

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> stage.close());

        VBox layout = new VBox(10,
                new Label("Editar Título:"), campoTitulo,
                new Label("Editar Autor:"), campoAutor,
                btnSalvar, btnCancelar);

        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 300, 220));
        stage.show();
    }
}
