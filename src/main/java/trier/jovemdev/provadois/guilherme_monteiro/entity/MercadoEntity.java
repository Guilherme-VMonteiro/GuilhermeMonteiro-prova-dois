package trier.jovemdev.provadois.guilherme_monteiro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trier.jovemdev.provadois.guilherme_monteiro.dto.MercadoDto;

import java.util.List;

@Entity
@Table(name = "mercado")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MercadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(length = 14, nullable = false, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "mercado", cascade = CascadeType.DETACH)
    private List<ProdutoEntity> produtos;

    @OneToMany(mappedBy = "mercado", cascade = CascadeType.DETACH)
    private List<VendaEntity> vendas;

    public MercadoEntity(Long mercadoId) {
        this.id = mercadoId;
    }

    public MercadoEntity(MercadoDto dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.nomeFantasia = dto.getNomeFantasia();
        this.cnpj = dto.getCnpj();
    }
}
