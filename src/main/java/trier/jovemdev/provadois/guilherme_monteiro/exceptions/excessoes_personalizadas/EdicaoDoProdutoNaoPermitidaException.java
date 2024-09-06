package trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas;

import lombok.Getter;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.ExcessaoPersonalizadaBase;

@Getter
public class EdicaoDoProdutoNaoPermitidaException extends ExcessaoPersonalizadaBase {

    public EdicaoDoProdutoNaoPermitidaException(Long idProduto) {
        super(String.format("Não é possivel alterar dados do produto id: %s. Motivo: O produto possui vendas em aberto", idProduto));
    }
}
