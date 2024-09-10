package trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas;

import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.ExcessaoPersonalizadaBase;

public class EstoqueInsuficienteException extends ExcessaoPersonalizadaBase {
    public EstoqueInsuficienteException(ProdutoDto produto, Integer quantidade) {
        super(String.format("Estoque insuficiente para o produto %s, id: %s. Requisitado: %s, Existente: %s", produto.getNome(), produto.getId(), quantidade, produto.getEstoque()));
    }
}
