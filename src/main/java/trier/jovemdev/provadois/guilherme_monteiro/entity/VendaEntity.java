package trier.jovemdev.provadois.guilherme_monteiro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "mercado_id", nullable = false)
    private MercadoEntity mercado;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private StatusVendaEnum status;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.DETACH)
    private List<ItemVendaEntity> itemVendas;

    public VendaEntity(Long id) {
        this.id = id;
    }

    public VendaEntity(VendaDto dto) {
        this.id = dto.getId();
        this.mercado = new MercadoEntity(dto.getMercado());
        this.status = dto.getStatus();
        this.valorTotal = dto.getValorTotal();
        this.dataCriacao = dto.getDataCriacao();
        if (dto.getItemVendas() != null) {
            this.itemVendas = dto.getItemVendas().stream().map(ItemVendaEntity::new).toList();
        } else {
            this.itemVendas = new ArrayList<>();
        }
    }
}
