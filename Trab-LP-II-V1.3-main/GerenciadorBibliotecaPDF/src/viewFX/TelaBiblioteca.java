package viewfx;

import controller.GerenciadorBiblioteca;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entrada;

import java.util.List;

public class TelaBiblioteca {

    private static GerenciadorBiblioteca gerenciador = new GerenciadorBiblioteca();

    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Biblioteca de PDFs");

        ListView<String> listaEntradas = new ListView<>();
        atualizarLista(listaEntradas);

        Button btnAdicionar = new Button("Adicionar Entrada");
        Button btnRemover = new Button("Remover Selecionada");
        Button btnEditar = new Button("Editar Selecionada");
        Button btnFechar = new Button("Fechar");

        btnAdicionar.setOnAction(e -> mostrarFormularioAdicionar(listaEntradas));
        btnRemover.setOnAction(e -> {
            String selecionado = listaEntradas.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                gerenciador.removerEntrada(selecionado);
                atualizarLista(listaEntradas);
            }
        });
        btnEditar.setOnAction(e -> {
            String selecionado = listaEntradas.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                Entrada entrada = gerenciador.getEntrada(selecionado);
                TelaDetalhesEntrada.exibir(entrada, gerenciador, () -> atualizarLista(listaEntradas));
            }
        });
        btnFechar.setOnAction(e -> stage.close());

        HBox botoes = new HBox(10, btnAdicionar, btnRemover, btnEditar, btnFechar);
        VBox layout = new VBox(10, new Label("Entradas da Biblioteca:"), listaEntradas, botoes);
        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 500, 300));
        stage.show();
    }

    private static void atualizarLista(ListView<String> listView) {
        listView.getItems().clear();
        for (Entrada entrada : gerenciador.listarEntradas()) {
            listView.getItems().add(entrada.getTitulo());
        }
    }

    private static void mostrarFormularioAdicionar(ListView<String> listaEntradas) {
        Stage form = new Stage();
        form.setTitle("Nova Entrada");

        TextField campoTitulo = new TextField();
        campoTitulo.setPromptText("Título");

        TextField campoAutor = new TextField();
        campoAutor.setPromptText("Autor");

        ComboBox<String> tipoEntrada = new ComboBox<>();
        tipoEntrada.getItems().addAll("Livro", "NotaDeAula", "Slide");
        tipoEntrada.setPromptText("Tipo");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            String tipo = tipoEntrada.getValue();
            String titulo = campoTitulo.getText();
            String autor = campoAutor.getText();
            if (tipo != null && !titulo.isBlank() && !autor.isBlank()) {
                switch (tipo) {
                    case "Livro" -> gerenciador.adicionarEntrada(new model.Livro(titulo, autor));
                    case "NotaDeAula" -> gerenciador.adicionarEntrada(new model.NotaDeAula(titulo, autor));
                    case "Slide" -> gerenciador.adicionarEntrada(new model.Slide(titulo, autor));
                }
                atualizarLista(listaEntradas);
                form.close();
            }
        });

        VBox layout = new VBox(10, tipoEntrada, campoTitulo, campoAutor, btnSalvar);
        layout.setPadding(new Insets(15));

        form.setScene(new Scene(layout, 300, 200));
        form.show();
    }
}
