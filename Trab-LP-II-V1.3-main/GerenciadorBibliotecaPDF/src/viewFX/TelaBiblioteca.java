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
import java.util.stream.Collectors;

public class TelaBiblioteca {

    private static GerenciadorBiblioteca gerenciador = new GerenciadorBiblioteca();

    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Biblioteca de PDFs");

        ListView<String> listaEntradas = new ListView<>();
        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Buscar por título ou autor...");
        campoBusca.textProperty().addListener((obs, oldVal, newVal) -> atualizarLista(listaEntradas, newVal));
        atualizarLista(listaEntradas, "");

        Button btnAdicionar = new Button("Adicionar Entrada");
        Button btnRemover = new Button("Remover Selecionada");
        Button btnEditar = new Button("Editar Selecionada");
        Button btnBibtex = new Button("Exportar BibTeX");
        Button btnColecoes = new Button("Gerenciar Coleções");
        Button btnBibtexColecao = new Button("BibTeX da Coleção");
        Button btnImportarBib = new Button("Importar BibTeX");
        Button btnExportarZip = new Button("Exportar Coleção ZIP");
        Button btnVisualizarColecao = new Button("Visualizar Coleção");
        Button btnEditarColecao = new Button("Editar Coleção");
        Button btnRemoverColecao = new Button("Remover Coleção");
        Button btnMoverEntrada = new Button("Mover Entrada");
        Button btnFechar = new Button("Fechar");

        btnAdicionar.setOnAction(e -> mostrarFormularioAdicionar(listaEntradas, campoBusca));
        btnRemover.setOnAction(e -> {
            String selecionado = listaEntradas.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                gerenciador.removerEntrada(selecionado);
                atualizarLista(listaEntradas, campoBusca.getText());
            }
        });
        btnEditar.setOnAction(e -> {
            String selecionado = listaEntradas.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                Entrada entrada = gerenciador.getEntrada(selecionado);
                TelaDetalhesEntrada.exibir(entrada, gerenciador, () -> atualizarLista(listaEntradas, campoBusca.getText()));
            }
        });
        btnBibtex.setOnAction(e -> {
            String selecionado = listaEntradas.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                Entrada entrada = gerenciador.getEntrada(selecionado);
                TelaBibTeXEntrada.exibir(entrada);
            }
        });
        btnBibtexColecao.setOnAction(e -> TelaBibTeXColecao.exibir(gerenciador));
        btnColecoes.setOnAction(e -> TelaColecoes.exibir(gerenciador));
        btnImportarBib.setOnAction(e -> TelaImportarBib.exibir(gerenciador, () -> atualizarLista(listaEntradas, campoBusca.getText())));
        btnExportarZip.setOnAction(e -> TelaExportarZipColecao.exibir(gerenciador));
        btnVisualizarColecao.setOnAction(e -> TelaVisualizarColecao.exibir(gerenciador));
        btnEditarColecao.setOnAction(e -> TelaEditarColecao.exibir(gerenciador));
        btnRemoverColecao.setOnAction(e -> TelaRemoverColecao.exibir(gerenciador));
        btnMoverEntrada.setOnAction(e -> TelaMoverEntrada.exibir(gerenciador));
        btnFechar.setOnAction(e -> stage.close());

        HBox botoes = new HBox(10, btnAdicionar, btnRemover, btnEditar, btnBibtex, btnBibtexColecao, btnColecoes, btnVisualizarColecao, btnEditarColecao, btnRemoverColecao, btnMoverEntrada, btnImportarBib, btnExportarZip, btnFechar);
        VBox layout = new VBox(10, new Label("Entradas da Biblioteca:"), campoBusca, listaEntradas, botoes);
        layout.setPadding(new Insets(15));

        stage.setScene(new Scene(layout, 1250, 350));
        stage.show();
    }

    private static void atualizarLista(ListView<String> listView, String filtro) {
        listView.getItems().clear();
        List<Entrada> entradas = gerenciador.listarEntradas();
        if (filtro != null && !filtro.isBlank()) {
            String termo = filtro.toLowerCase();
            entradas = entradas.stream()
                    .filter(e -> e.getTitulo().toLowerCase().contains(termo) || e.getAutor().toLowerCase().contains(termo))
                    .collect(Collectors.toList());
        }
        for (Entrada entrada : entradas) {
            listView.getItems().add(entrada.getTitulo());
        }
    }

    private static void mostrarFormularioAdicionar(ListView<String> listaEntradas, TextField campoBusca) {
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
                atualizarLista(listaEntradas, campoBusca.getText());
                form.close();
            }
        });

        VBox layout = new VBox(10, tipoEntrada, campoTitulo, campoAutor, btnSalvar);
        layout.setPadding(new Insets(15));

        form.setScene(new Scene(layout, 300, 200));
        form.show();
    }
}
