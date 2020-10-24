package Populadores;

import Colecoes.ListaEntregas;
import Colecoes.ListaOperadores;
import Entities.Operador;

public class PopuladorOperadores {
    private final ListaOperadores listaOperadores;
    private final ListaEntregas listaEntregas;

    public PopuladorOperadores() {
        this.listaOperadores = new ListaOperadores();
        this.listaEntregas = new ListaEntregas();

        Operador operador0 = new Operador("David Gilmour");
        listaOperadores.addOperador(operador0);

        Operador operador1 = new Operador("Robert Plant");
        listaOperadores.addOperador(operador1);

        Operador operador2 = new Operador("Jose Gomes");
        listaOperadores.addOperador(operador2);

        Operador operador3 = new Operador("Pedro Almeida");
        listaOperadores.addOperador(operador3);

        Operador operador4 = new Operador("Regerio Aguas");
        listaOperadores.addOperador(operador4);
    }

    public Operador getOperador(String iniciais) {
        Operador operador = getListaOperadores().getOperador(iniciais);
        operador.setListaEntregas(getListaEntregas());
        return operador;
    }

    public ListaOperadores getListaOperadores() {
        return listaOperadores;
    }

    private ListaEntregas getListaEntregas() {
        return listaEntregas;
    }
}