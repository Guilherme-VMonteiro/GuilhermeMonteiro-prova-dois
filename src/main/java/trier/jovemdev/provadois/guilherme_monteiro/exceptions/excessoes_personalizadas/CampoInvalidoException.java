package trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas;

import lombok.Getter;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.ExcessaoPersonalizadaBase;

@Getter
public class CampoInvalidoException extends ExcessaoPersonalizadaBase {

    public CampoInvalidoException(String campo, String valor) {
        super(String.format("Campo %s possuí valor inválido(%s).", campo, valor));
    }
}
