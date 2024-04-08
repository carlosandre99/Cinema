import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Lista implements Iterable<Sessao>{
	private ArrayList<Sessao> lista;
		
	public Lista(ArrayList<Sessao> lista) {
		super();
		this.lista = lista;
	}

	public ArrayList<Sessao> getLista() {
		return lista;
	}

	private void setLista(ArrayList<Sessao> lista) {
		this.lista = lista;
	}
	
	void addSessao(Sessao sessao) {
		lista.add(sessao);
	}
	
	public void listarOrdemCronologica() {
		Collections.sort(lista,new Comparator<Sessao>() {
			public int compare(Sessao sessao1, Sessao sessao2) {
				return sessao1.getData().compareTo(sessao2.getData());
			}
		});
	}
	
	public void ordenarFilmesNota() {
		Collections.sort(lista);
		
		Collections.sort(lista, new Comparator<Sessao>() {
			public int compare(Sessao s1, Sessao s2) {
				return s2.getFilme().getNota().compareTo(s1.getFilme().getNota());
			}
		});
	}

	@Override
	public Iterator<Sessao> iterator() {
		// TODO Auto-generated method stub
		return lista.iterator();
	}

	@Override
	public String toString() {
		return "Lista [lista=" + lista + "]";
	}

}