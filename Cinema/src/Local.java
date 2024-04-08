
public class Local implements Comparable<Local> {
	private String nome;

	public Local(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Local [nome=" + nome + "]";
	}

	@Override
	public int compareTo(Local local) {
		return this.nome.compareToIgnoreCase(local.getNome());
	} 

}