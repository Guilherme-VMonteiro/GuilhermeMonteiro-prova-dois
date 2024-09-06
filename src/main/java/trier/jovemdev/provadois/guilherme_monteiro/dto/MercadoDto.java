package trier.jovemdev.provadois.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trier.jovemdev.provadois.guilherme_monteiro.entity.MercadoEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MercadoDto {

    private Long id;
    private String nome;
    private String nomeFantasia;
    private String cnpj;

    public MercadoDto(MercadoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.nomeFantasia = entity.getNomeFantasia();
        this.cnpj = entity.getCnpj();
    }
}
