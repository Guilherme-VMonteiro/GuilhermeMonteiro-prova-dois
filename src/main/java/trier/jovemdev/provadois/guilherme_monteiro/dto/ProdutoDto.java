package trier.jovemdev.provadois.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trier.jovemdev.provadois.guilherme_monteiro.entity.ProdutoEntity;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

    private Long id;
    private Long mercadoId;
    private String nome;
    private Integer estoque;
    private BigDecimal valorUnitario;

    public ProdutoDto(ProdutoEntity entity) {
        this.id = entity.getId();
        this.mercadoId = entity.getMercado().getId();
        this.nome = entity.getNome();
        this.estoque = entity.getEstoque();
        this.valorUnitario = entity.getValorUnitario();
    }
}
