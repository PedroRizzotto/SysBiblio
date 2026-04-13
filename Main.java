import java.util.List;

// dependências

LivroService service = new LivroService();

void main() {
    String menu = """
            ===== SysBiblio =====
            1 - Cadastrar Livro
            2 - Listar Livros
            3 - Pesquisar Livros
            4 - Remover Livro
            5 - Editar Livro
            0 - Sair
            """;

    int opcao;
    do {
        IO.print(menu);
        opcao = Input.scanInt("Digite uma opção: ");
        try {
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> pesquisar();
                case 4 -> remover();
                case 5 -> editar();
                case 0 -> IO.println("Até breve!!!");
                default -> IO.println("Opção Inválida");
            }
        } catch (Exception e) {
            IO.println("ERRO: " + e.getMessage());
        }
        IO.readln("Pressione ENTER para continuar...");
    } while (opcao != 0);
}

void cadastrar() throws Exception {
    String titulo = Input.scanString("Digite o título do livro: ");
    String autor = Input.scanString("Digite o autor do livro: ");
    int anoPublicacao = Input.scanInt("Digite o ano de publicação do livro: ");
    int numeroPaginas = Input.scanInt("Digite o número de páginas do livro: ");

    Livro novoLivro = new Livro(titulo, autor, anoPublicacao, numeroPaginas);

    service.cadastrar(novoLivro);

    IO.println("Livro cadastrado com sucesso!!!");
}

void listar() {

    List<Livro> livros = service.listar();
    imprimirLista(livros);

}

void pesquisar() {

    String pesquisa = Input.scanString("Digite parte do título, autor, ou ano de publicação: ");

    List<Livro> livros = service.pesquisar(pesquisa);
    imprimirLista(livros);

}

void imprimirLista(List<Livro> livros) {
    if (livros.isEmpty()) {
        IO.println("Nenhum livro encontrado!");
        return;
    }
    int i = 1;
    for (Livro livro : livros) {
        IO.println(i++ + " - " + livro);
    }

}

void remover() throws Exception {
    listar();
    int indice = Input.scanInt("Digite o número do livro que você deseja remover: ");
    service.remover(indice - 1);
    IO.println("Livro removido com sucesso");

}

void editar() throws Exception {
    listar();
    int indice = Input
            .scanInt("Digite o número do livro que você deseja editar: ");
    Livro livro = service.retornarLivro(indice - 1);

    IO.println("Editando o livro " + livro.getTitulo() + "(pressione ENTER para não modificar)");

    String novoTitulo = Input.scanString("Título (" + livro.getTitulo() + "): ", true);
    if (novoTitulo == "")
        novoTitulo = livro.getTitulo();

    String novoAutor = Input.scanString("Autor (" + livro.getAutor() + "): ", true);
    if (novoAutor == "")
        novoAutor = livro.getAutor();

    int novoAno = Input.scanInt("Ano (" + livro.getAnoPublicacao() + "): ", true);
    if (novoAno == 0)
        novoAno = livro.getAnoPublicacao();

    int novoNuPaginas = Input.scanInt("N. Páginas (" + livro.getNumeroPaginas() + "): ", true);
    if (novoNuPaginas == 0)
        novoNuPaginas = livro.getNumeroPaginas();

    Livro livroEditado = new Livro(novoTitulo, novoAutor, novoAno, novoNuPaginas);

    service.editar(indice - 1, livroEditado);
}
