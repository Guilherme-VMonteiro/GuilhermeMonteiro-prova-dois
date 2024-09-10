package trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas;

import lombok.Getter;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.ExcessaoPersonalizadaBase;

@Getter
public class VendaFechadaException extends ExcessaoPersonalizadaBase {

    public VendaFechadaException() {
        super("Não é possível modificar uma venda fechada.");
    }
}
