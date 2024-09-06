package trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas;

import trier.jovemdev.provadois.guilherme_monteiro.exceptions.ExcessaoPersonalizadaBase;

public class EntidadeNaoEncontradaException extends ExcessaoPersonalizadaBase {


    public EntidadeNaoEncontradaException(String entidade, Long id) {
        super(String.format("%s com id: %s não encontrada.", entidade, id));
    }
}
