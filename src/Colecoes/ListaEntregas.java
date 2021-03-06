package Colecoes;

import Entities.Entrega;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListaEntregas {

    private final List<Entrega> listaEntregas;
    private int idEntrega;

    public ListaEntregas() {
        this.listaEntregas = new ArrayList<>();
        this.idEntrega = 1;
    }

    public void addEntrega(Entrega entrega){
        entrega.setId(idEntrega);
        listaEntregas.add(entrega);
        idEntrega++;
    }

    public Entrega getEntrega(int id) {
        for (Entrega en: listaEntregas) {
            if (en.getId() == id) return en;
        }
        return null;
    }

    public List<Entrega> getNaoRetiradas(){
        Predicate<Entrega> naoRetiradas = entrega -> entrega.getDataRetirada() == null;
        return listaEntregas.stream().filter(naoRetiradas).collect(Collectors.toList());
    }

    public int getSize(){
        return listaEntregas.size();
    }
}
