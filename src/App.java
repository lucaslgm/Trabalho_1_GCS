import Colecoes.ListaEntregas;
import Colecoes.ListaMoradores;
import Colecoes.ListaOperadores;
import Entities.Entrega;
import Entities.Morador;
import Entities.Operador;
import Utils.ManipuladorDeDatas;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args){

        Scanner inputInt = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);

        //TODO: SÓ PARA TESTES INICIAS
        final ListaOperadores Operadores = new ListaOperadores();
        final ListaMoradores  Moradores  = new ListaMoradores();
        final ListaEntregas   Entregas   = new ListaEntregas();

        //currOperador deve receber o operador do método de seleção de operadores
        Operador currOperador = new Operador("Operador Teste");
        currOperador.setListaEntregas(Entregas);

        Morador morador01 = new Morador("1234567890","morador teste", 123);
        Moradores.addMorador(morador01);

        String data = "23/10/2020 19:52:15";
        try {
            System.out.println(ManipuladorDeDatas.StringToDate(data));
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        Date date = Date.from(Instant.now());
        try {
            System.out.println(ManipuladorDeDatas.dateToString(date));
        }
        catch (ParseException e){
            e.printStackTrace();
        }


        ///////////////////////////////////////

        int op = 0;
        while (op >= 0) {
            System.out.println("Operações:\n\n" +
                    "1) Escolher Operador\n" +
                    "2) Incluir Operador\n" +
                    "3) Incluir Morador\n" +
                    "4) Listar Moradores\n" +
                    "5) Registrar Nova Entrega\n" +
                    "6) Registrar Retirada de Pacote\n" +
                    "7) Procurar Entrega via Descricao\n" +
                    "8) Listar Entregas Não Retiradas\n" +
                    "9) Gerar Relatório\n\n" +
                    "(-1 para Sair)");

            System.out.println("\nInforme uma opcao:\n");
            op = inputInt.nextInt();
            switch (op) {
                case 1:     // ESCOLHER OPERADOR
                    System.out.println("PRECISA SER FEITO!");
                    break;
                case 2:     // INCLUIR NOVO OPERADOR
                    System.out.println("PRECISA SER FEITO!");
                    break;
                case 3:     // INCLUIR MORADOR
                    System.out.println("PRECISA SER FEITO!");
                    break;
                case 4:     // LISTAR MORADORES
                    System.out.println("PRECISA SER FEITO!");
                    break;
                case 5:     // REGISTRAR NOVA ENTREGA
                    System.out.println("Informe o apartamento de destino da entrega");
                    int aptoTemp = inputInt.nextInt();

                    System.out.println("Informe uma breve descrição da entrega");
                    String descTemp = inputString.nextLine();

                    currOperador.registraEntrega(aptoTemp,descTemp);

                    //somente para testes
                    for(int i = 1; i < Entregas.getEntregas("teste").size(); i++){
                        System.out.println(Entregas.getEntregas("teste"));
                        ++i;
                    }

                    break;
                case 6:     // REGISTRAR RETIRADA DE PACOTE
                    try {
                        System.out.println("Insira o número da entrega que desejas retirar");
                        int idEntrega = inputInt.nextInt();

                        System.out.println("Insira o RG do morador que vai realizar a retirada");
                        String rgMorador = inputString.nextLine();

                        if (rgMorador == null || rgMorador.trim().isEmpty()) {
                            throw new Exception("Registro Geral inválido");
                        }

                        Morador morador = Moradores.getMorador(rgMorador);
                        if (morador == null){
                            throw new Exception("Morador não encontrado");
                        }

                        Entrega entrega = Entregas.getEntrega(idEntrega);

                        if (entrega == null) {
                            throw new Exception("Entrega não encontrada");
                        }

                        if (entrega.getDataRetirada() != null) {
                            throw new Exception("Esta entrega já foi retirada");
                        }

                        if (entrega.getApto().equals(morador.getApto())) {
                            throw new Exception("O morador de retirada não pertence ao apto da entrega");
                        }

                        currOperador.registrarRetirada(morador,idEntrega);
                        Entregas.atualizarEntrega(entrega);
                        System.out.println("Retirada registrada com sucesso!");

                        //somente para testes
                        System.out.println(Entregas.getEntrega(idEntrega));

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 7:     // PROCURAR ENTREGA VIA DESCRICAO
                    System.out.println("PRECISA SER FEITO!");
                    break;
                case 8:     // LISTAR ENTREGAS NAO RETIRADAS
                    if (Entregas.getNaoRetiradas().size() == 0) {
                        System.out.println("Não há entregas a serem retiradas");
                    }
                    Entregas.getNaoRetiradas().forEach(e -> System.out.println(e.toString()));
                    break;
                case 9:     // GERAR RELATÓRIO
                    System.out.println("Insira a data inicial (EX: 01/01/2001): ");
                    String dataIni = inputString.nextLine();

                    System.out.println("Insira a data final (EX: 01/01/2001): ");
                    String dataFim = inputString.nextLine();

                    String relatorio = currOperador.getRelatorio(dataIni, dataFim);
                    System.out.println(relatorio);

                    break;
                    default:
                        System.out.println("Fim do poço amigo, Slender vai te pegar.");
            }
        }
    }
}
