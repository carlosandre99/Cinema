import java.time.LocalDateTime;

public class Sessao implements Comparable<Sessao>{
	private Filme filme;
	private Local local;
	private LocalDateTime data;
	private float preco;
	private String comentario;

	public Sessao(Filme filme, Local local, LocalDateTime data, float preco, String comentario) {
		super();
		this.filme = filme;
		this.local = local;
		this.data = data;
		this.preco = preco;
		this.comentario = comentario;
	}

	public LocalDateTime getData() {
		return data;
	}

	private void setData(LocalDateTime data) {
		this.data = data;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Sessao [filme=" + filme + ", local=" + local + ", preco=" + preco + ", comentario=" + comentario + "]";
	}

	@Override
	public int compareTo(Sessao sessao) {
		return this.filme.getNome().compareToIgnoreCase(sessao.getFilme().getNome());
	}

}