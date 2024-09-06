package trier.jovemdev.provadois.guilherme_monteiro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ItemVendaDto;

import java.math.BigDecimal;

@Entity
@Table(name = "item_venda")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "venda_id", nullable = false)
    private VendaEntity venda;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    public ItemVendaEntity(ItemVendaDto dto) {
        this.id = dto.getId();
        this.venda = new VendaEntity(dto.getId());
        this.produto = new ProdutoEntity(dto.getProdutoId());
        this.quantidade = dto.getQuantidade();
        this.valorTotal = dto.getValorTotal();
    }
}
