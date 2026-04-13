import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivroService {
    private List<Livro> acervo = new ArrayList<>();

    public Livro validaLivro(Livro novoLivro) throws Exception {
        if (novoLivro == null) {
            throw new Exception("Objeto Nulo");
        }
        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty())
            throw new Exception("Título inválido");

        novoLivro.setTitulo(novoLivro.getTitulo().trim().toUpperCase());

        if (novoLivro.getAutor() == null || novoLivro.getAutor().isEmpty())
            throw new Exception("Autor inválido");

        novoLivro.setAutor(novoLivro.getAutor().trim().toUpperCase());

        if (novoLivro.getAnoPublicacao() < 1900
                || novoLivro.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano de publicação inválido");

        return novoLivro;
    }

    public void cadastrar(Livro novoLivro) throws Exception {

        Livro novoLivroValidado = validaLivro(novoLivro);

        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(novoLivroValidado.getTitulo())
                    && livro.getAutor().equalsIgnoreCase(novoLivroValidado.getAutor())
                    && livro.getAnoPublicacao() == novoLivroValidado.getAnoPublicacao())
                throw new Exception("Já existe livro cadastrado com este Titulo, Autor, e Ano de publicação");
        }

        // Nesta parte estaria chamando a camada Repository
        acervo.add(novoLivroValidado);
        ordenarPorTitulo();
    }

    public List<Livro> listar() {
        return acervo;
    }

    // melhoria livre: ordenar os livros por título
    private void ordenarPorTitulo() {
        for (int i = 0; i < acervo.size() - 1; i++) {
            for (int j = 0; j < acervo.size() - 1 - i; j++) {
                if (acervo.get(j).getTitulo().compareTo(acervo.get(j + 1).getTitulo()) > 0) {
                    Livro temp = acervo.get(j);
                    acervo.set(j, acervo.get(j + 1));
                    acervo.set(j + 1, temp);
                }
            }
        }
    }

    public List<Livro> pesquisar(String pesquisa) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        pesquisa = pesquisa.toUpperCase();
        for (Livro livro : acervo) {
            String infoLivro = livro.getTitulo() + livro.getAutor() + livro.getAnoPublicacao();
            if (infoLivro.contains(pesquisa))
                livrosEncontrados.add(livro);
        }
        return livrosEncontrados;
    }

    public void remover(int indice) throws Exception {
        if (indice < 0 || indice > acervo.size())
            throw new Exception("Indíce inválido");
        acervo.remove(indice);
    }

    public Livro retornarLivro(int indice) throws Exception {
        if (indice < 0 || indice > acervo.size())
            throw new Exception("Indíce inválido");
        return acervo.get(indice);
    }

    public void editar(int indice, Livro livro) throws Exception {
        Livro livroValidado = validaLivro(livro);

        acervo.set(indice, livroValidado);
        ordenarPorTitulo();
    }
}
