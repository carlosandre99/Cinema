public class Filme implements Comparable<Filme> {
	private String nome;
	private String nota; // de 0 a 5
	private boolean favorito;
	// opcional
	private String comentario;
	
	public Filme(String nome, String nota, boolean favorito, String comentario) {
		super();
		this.nome = nome;
		this.nota = nota;
		this.favorito = favorito;
		this.comentario = comentario;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}


	public boolean isFavorito() {
		return favorito;
	}


	private void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	@Override
	public int compareTo(Filme filme) {
		return this.nome.compareToIgnoreCase(filme.getNome()); 
	}


	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", nota=" + nota + ", favorito=" + favorito + ", comentario=" + comentario + "]";
	}

	
}