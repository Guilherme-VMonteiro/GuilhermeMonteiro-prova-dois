package trier.jovemdev.provadois.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trier.jovemdev.provadois.guilherme_monteiro.entity.ItemVendaEntity;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendaDto{

    private Long id;
    private Long vendaId;
    private ProdutoDto produtoDto;
    private Integer quantidade;
    private BigDecimal valorTotal;

    public ItemVendaDto(ItemVendaEntity entity) {
        this.id = entity.getId();
        this.vendaId = entity.getVenda().getId();
        this.produtoDto = new ProdutoDto(entity.getProduto());
        this.quantidade = entity.getQuantidade();
        this.valorTotal = entity.getValorTotal();
    }
}
