package trier.jovemdev.provadois.guilherme_monteiro.exceptions;

import lombok.Getter;

@Getter
public class MensagemExcessaoPersonalizada {

    private String mensagem;

    public MensagemExcessaoPersonalizada(String mensagem) {
        this.mensagem = mensagem;
    }
}
