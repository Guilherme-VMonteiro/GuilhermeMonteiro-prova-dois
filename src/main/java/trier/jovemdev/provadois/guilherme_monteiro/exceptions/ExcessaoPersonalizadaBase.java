package trier.jovemdev.provadois.guilherme_monteiro.exceptions;

import lombok.Getter;

@Getter
public class ExcessaoPersonalizadaBase extends RuntimeException {

    private MensagemExcessaoPersonalizada mensagemExcessaoPersonalizada;

    public ExcessaoPersonalizadaBase(String mensagemExcessaoPersonalizada) {
        this.mensagemExcessaoPersonalizada = new MensagemExcessaoPersonalizada(mensagemExcessaoPersonalizada);
    }
}
