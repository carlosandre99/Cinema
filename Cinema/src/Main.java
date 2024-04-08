import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {

    private static Scanner teclado = new Scanner(System.in);
    private static ArrayList<Local> locais = new ArrayList<Local>();
    private static ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
    private static Lista lista = new Lista(sessoes);

    private static Locale LocaleBrasil = new Locale("pt","BR");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy, EEEE - HH'h'mm", LocaleBrasil);
    private static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("(dd/MMM/yyyy, EEEE)", LocaleBrasil);
    private static DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MMMM/yyyy", LocaleBrasil);
    private static DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("dd (EEEE, HH'h'mm)", LocaleBrasil);


    public static void main(String[] args) {

        locais.add(new Local("Jockey Plaza"));
        locais.add(new Local("Pátio Batel"));
        locais.add(new Local("Shopping Jardim das Americas"));

        LocalDateTime data1 = LocalDateTime.of(2022, 12, 28, 20, 30);
        LocalDateTime data2 = LocalDateTime.of(2022, 3, 17, 21, 30);
        LocalDateTime data3 = LocalDateTime.of(2022, 1, 6, 22, 0);
        LocalDateTime data4 = LocalDateTime.of(2022, 12, 26, 14, 0);

        sessoes.add(new Sessao(new Filme("Avatar 2","5",true,"Melhor filme da franquia"),locais.get(0),data1,15F,"A pipoca estava boa"));
        sessoes.add(new Sessao(new Filme("Capitã Marvel","4",false,"Filme muito triste"),locais.get(1),data2,20F,"A sessão estava muito lotada"));
        sessoes.add(new Sessao(new Filme("Frozen 2","4",false,"Filme divertido"),locais.get(2),data3,50F,"Cinema muito caro"));
        sessoes.add(new Sessao(new Filme("Pantera Negra 2","5",true,"Filme muito legal"),locais.get(0),data4,20F,"A fila da sessão estava muito grande"));

        int opcao;

        do {
            System.out.println("\n0 - Sair");
            System.out.println("1 - Cadastrar Filme");
            System.out.println("2 - Mostrar Dados do Filme (Filme e sessão)");
            System.out.println("3 - Editar Nota, Comentário do Filme ou da Sessão");
            System.out.println("4 - Listagem em Ordem Alfabética");
            System.out.println("5 - Listagem em Ordem de Avaliação");
            System.out.println("6 - Listagem em Ordem Cronológica");
            System.out.println("7 - Listagem dos Filmes Favoritos");

            System.out.println("Selecione a sua opção:");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch(opcao) {
                case 1:{ //Cadastro de uma nova sessão
                    lista.addSessao(new Sessao(cadastrarFilme(),cadastrarLocal(),receberData(),receberPreco(),receberComentarioSessao()));
                    break;
                }
                case 2:{
                    Sessao sessao;
                    sessao = procurarSessao();
                    if(sessao != null) {
                        exibirSessao(sessao);
                    }
                    else {
                        System.out.println("Não foi encontrada a Sessão!");
                    }
                    break;
                }
                case 3:{
                    editarFilme();
                    break;
                }
                case 4:{
                    ordenarFilmesAZ();
                    break;
                }
                case 5:{
                    lista.ordenarFilmesNota();
                    tabelaOrdemNota();
                    break;
                }
                case 6:{
                    lista.listarOrdemCronologica();
                    tabelaOrdemCronologica();
                    break;
                }
                case 7:{
                    listarFavoritos();
                    break;
                }
                case 0:{
                    break;
                }
            }
        }while(opcao != 0);

    }

    private static Filme cadastrarFilme() {

        String nome;
        String nota;
        String resposta; //Retirar da lógica quando corrigir o programa
        boolean favorito = false;
        String comentario;
        Filme filme;

        System.out.println("--    Cadastro de Filme    --");
        System.out.print("\nCadastre o filme:\nNome do Filme?\n");
        nome = teclado.nextLine();

        System.out.print("\nNota?\n");
        nota = teclado.nextLine();

        System.out.print("\nÉ um de seus filmes favoritos?\n<S/N>\n");
        resposta = teclado.nextLine();
        if(resposta.toUpperCase().charAt(0) == 'S') {
            favorito = true;
        }

        System.out.print("\nComente o filme:\n");
        comentario = teclado.nextLine();

        filme = new Filme(nome,nota,favorito,comentario);

        return filme;
    }

    private static Local cadastrarLocal() {
        int opcaoLocal;
        Local local;
        int cont = 0;

        System.out.print("\nEscolha um local da lista ou cadastre um novo:\n");
        for(Local l : locais) {
            System.out.print(cont  + "- " + l.getNome() + "\n");
            cont ++;
        }
        opcaoLocal = teclado.nextInt();
        teclado.nextLine();

        if(opcaoLocal < locais.size()) {
            local = new Local(locais.get(opcaoLocal).getNome());
        }
        else {
            System.out.print("\nCadastre um novo local:\n");
            String novoLocal;
            novoLocal = teclado.nextLine();
            local = new Local(novoLocal);
            locais.add(local);
        }
        return local;
    }

    private static LocalDateTime receberData() {
        LocalDateTime data;
        String horario;
        String diaMesAno;
        int min = 0;
        int hora = 0;
        int dia = 0;
        int mes = 0;
        int ano = 0;

        System.out.print("\nInforme o horário da sessão (HH:MM):\n");
        horario = teclado.nextLine();
        if(horario.charAt(2) == ':') {
            hora = Integer.parseInt(horario.substring(0,2));
            min = Integer.parseInt(horario.substring(3,5));
        }

        System.out.print("\nInforme a data da sessão (DD/MM/AAAA):\n");
        diaMesAno = teclado.nextLine();
        if(diaMesAno.charAt(2) == '/' && diaMesAno.charAt(5) == '/') {
            dia = Integer.parseInt(diaMesAno.substring(0,2));
            mes = Integer.parseInt(diaMesAno.substring(3,5));
            ano = Integer.parseInt(diaMesAno.substring(6,10));
        }

        data = LocalDateTime.of(ano,mes,dia,hora,min,0);
        return data;
    }

    private static float receberPreco() {
        float preco;
        System.out.print("\nPreço do ingresso:\n");
        preco = teclado.nextFloat();
        teclado.nextLine();

        return preco;
    }

    private static String receberComentarioSessao() {
        String comentario;
        System.out.print("\nComente a sessão:\n");
        comentario = teclado.nextLine();

        return comentario;
    }

    private static void exibirSessao(Sessao sessao) {
        System.out.print("\nNome: " + sessao.getFilme().getNome());
        System.out.print("\nNota: " + sessao.getFilme().getNota());
        System.out.print("\nFavorito: ");
        if(sessao.getFilme().isFavorito() == true) {
            System.out.print("Sim");
        }
        else {
            System.out.print("Não");
        }
        System.out.print("\nComentário: " + sessao.getFilme().getComentario() + "\n");

        System.out.print("\nLocal da Sessão: " + sessao.getLocal().getNome());
        System.out.print("\nData e Horário da Sessão: " + sessao.getData().format(formatter));
        System.out.print("\nPreço: R$ " + sessao.getPreco());
        System.out.print("\nComentário da Sessão: " + sessao.getComentario() + "\n");
    }

    private static Sessao procurarSessao() {
        String nome;
        Sessao sessao = null;

        System.out.print("\n--     Busca de Filmes     --\nDigite o filme que deseja procurar:");
        nome = teclado.nextLine();
        for(Sessao s : sessoes) {
            if(s.getFilme().getNome().toUpperCase().contains(nome.toUpperCase())) {
                System.out.print("\nEncontrado: " + s.getFilme().getNome());
                System.out.print("\nCorresponde a busca? <S/N>\n");

                String opcao;
                opcao = teclado.nextLine();
                if(opcao.toUpperCase().charAt(0) == 'S') {
                    sessao = s;
                    return sessao;
                }
            }
        }
        return sessao;
    }

    private static void editarFilme(){
        String opcao;
        Sessao sessao;

        sessao = procurarSessao();

        if(sessao != null) {
            System.out.print("\nGostaria de editar a nota <S/N> ?\n");
            opcao = teclado.nextLine();
            if(opcao.toUpperCase().charAt(0) == 'S') {
                String nota;

                System.out.print("\nQual é a nova nota?\n");
                nota = teclado.nextLine();
                sessao.getFilme().setNota(nota);
            }

            System.out.print("\nGostaria de editar o comentário do filme <S/N> ?\n");
            opcao = teclado.nextLine();
            if(opcao.toUpperCase().charAt(0) == 'S') {
                String comentario;

                System.out.print("\nDigite o novo comentário:\n");
                comentario = teclado.nextLine();
                sessao.getFilme().setComentario(comentario);
            }

            System.out.print("\nGostaria de editar o comentário da sessão <S/N> ?\n");
            opcao = teclado.nextLine();
            if(opcao.toUpperCase().charAt(0) == 'S') {
                String comentario;

                System.out.print("\nDigite o novo comentário:\n");
                comentario = teclado.nextLine();
                sessao.setComentario(comentario);
            }
        }

        else {
            System.out.println("Filme não encontrado!");
        }
    }

    private static void ordenarFilmesAZ() {
        Collections.sort(sessoes);

        System.out.println("Lista de Filmes ordenados em ordem alfabética:\n");
        for(Sessao s : sessoes) {
            System.out.println(s.getFilme().getNome() + " (" + s.getData().format(formatter) + ")");
        }
    }

    private static void tabelaOrdemNota() {
        int ultimaNota = 0;

        System.out.println("--    Lista de Filmes - Ordenados por Nota    --");
        for(Sessao s : lista) {
            if(Integer.parseInt(s.getFilme().getNota()) != ultimaNota) {
                System.out.println("\nNota: " + s.getFilme().getNota());
                ultimaNota = Integer.parseInt(s.getFilme().getNota());
            }

            System.out.print(s.getFilme().getNome() + " ");
            System.out.print(s.getData().format(formatter2));
            System.out.println(" - " + s.getLocal().getNome());
        }
    }

    private static void tabelaOrdemCronologica() {
        int mesAnterior = 0;

        for(Sessao s : lista) {

            if(s.getData().getMonthValue() != mesAnterior) {
                System.out.println("\n" + s.getData().format(formatter3));
                mesAnterior = s.getData().getMonthValue();
            }

            System.out.print(s.getData().format(formatter4));
            System.out.print(" - " + s.getFilme().getNome());
            System.out.print(" - " + s.getLocal().getNome() + "\n");
        }
    }

    private static void listarFavoritos() {
        ArrayList<Sessao> favoritos = new ArrayList<Sessao>();

        for(Sessao s : sessoes) {
            if(s.getFilme().isFavorito() == true) {
                favoritos.add(s);
            }
        }

        Collections.sort(favoritos);

        System.out.println("Filmes Favoritos:\n");
        for(Sessao s : favoritos) {
            System.out.println(s.getFilme().getNome() + " (" + s.getData().format(formatter) + ")");
        }
    }
}