package trier.jovemdev.provadois.guilherme_monteiro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "mercado_id", nullable = false)
    private MercadoEntity mercado;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer estoque;

    @Column(name = "valor_unitario", nullable = false)
    private BigDecimal valorUnitario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.DETACH)
    private List<ItemVendaEntity> itemVendas;

    public ProdutoEntity(ProdutoDto dto) {
        this.id = dto.getId();
        this.mercado = new MercadoEntity(dto.getMercadoId());
        this.nome = dto.getNome();
        this.estoque = dto.getEstoque();
        this.valorUnitario = dto.getValorUnitario();
    }

    public void atualizaEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public void atualizaPreco(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
