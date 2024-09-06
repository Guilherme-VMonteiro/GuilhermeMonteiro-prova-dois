package trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas;

import lombok.Getter;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.ExcessaoPersonalizadaBase;

@Getter
public class CnpjExistenteException extends ExcessaoPersonalizadaBase {

    public CnpjExistenteException(String cnpj) {
        super(String.format("CNPJ %s já cadastrado.", cnpj));
    }
}
