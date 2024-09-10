package trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas;

import lombok.Getter;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.ExcessaoPersonalizadaBase;

@Getter
public class VendaVaziaException extends ExcessaoPersonalizadaBase {

    public VendaVaziaException() {
        super("Não é possível criar uma venda sem items.");
    }
}
