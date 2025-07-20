package viewfx;

import controller.GerenciadorBiblioteca;
import controller.GerenciadorColecoes;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Colecao;
import model.Entrada;

import java.util.List;

public class TelaColecoes {

    private static GerenciadorColecoes gerenciador = new GerenciadorColecoes();

    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Gerenciador de Coleções");

        ListView<String> listaColecoes = new ListView<>();
        atualizarLista(listaColecoes);

        Button btnNova = new Button("Criar Coleção");
        Button btnRemover = new Button("Remover Selecionada");
        Button btnDetalhes = new Button("Ver Detalhes");
        Button btnFechar = new Button("Fechar");

        btnNova.setOnAction(e -> mostrarFormularioCriar(listaColecoes));
        btnRemover.setOnAction(e -> {
            String nome = listaColecoes.getSelectionModel().getSelectedItem();
            if (nome != null) {
                gerenciador.removerColecao(nome);
                atualizarLista(listaColecoes);
            }
        });
        btnDetalhes.setOnAction(e -> mostrarDetalhes(listaColecoes.getSelectionModel().getSelectedItem()));
        btnFechar.setOnAction(e -> stage.close());

        HBox botoes = new HBox(10, btnNova, btnRemover, btnDetalhes, btnFechar);
        VBox layout = new VBox(10, new Label("Coleções Existentes:"), listaColecoes, botoes);
        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 450, 300));
        stage.show();
    }

    private static void atualizarLista(ListView<String> listView) {
        listView.getItems().clear();
        for (Colecao c : gerenciador.listarColecoes()) {
            listView.getItems().add(c.getNome());
        }
    }

    private static void mostrarFormularioCriar(ListView<String> listaColecoes) {
        Stage form = new Stage();
        form.setTitle("Nova Coleção");

        TextField campoNome = new TextField();
        campoNome.setPromptText("Nome da Coleção");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            String nome = campoNome.getText();
            if (!nome.isBlank()) {
                gerenciador.criarColecao(nome);
                atualizarLista(listaColecoes);
                form.close();
            }
        });

        VBox layout = new VBox(10, campoNome, btnSalvar);
        layout.setPadding(new Insets(15));

        form.setScene(new Scene(layout, 300, 120));
        form.show();
    }

    private static void mostrarDetalhes(String nomeColecao) {
        if (nomeColecao == null) return;

        Stage stage = new Stage();
        stage.setTitle("Detalhes da Coleção: " + nomeColecao);

        Colecao colecao = gerenciador.getColecao(nomeColecao);
        List<Entrada> entradas = colecao.getEntradas();

        ListView<String> lista = new ListView<>();
        for (Entrada e : entradas) {
            lista.getItems().add(e.getTitulo() + " - " + e.getAutor());
        }

        Button btnFechar = new Button("Fechar");
        btnFechar.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, new Label("Entradas da Coleção:"), lista, btnFechar);
        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 400, 250));
        stage.show();
    }
}
