package trier.jovemdev.provadois.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trier.jovemdev.provadois.guilherme_monteiro.entity.ItemVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.MercadoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.VendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDto {

    private Long id;
    private MercadoDto mercado;
    private StatusVendaEnum status;
    private BigDecimal valorTotal;
    private LocalDate dataCriacao;
    private List<ItemVendaDto> itemVendas;

    public VendaDto(VendaEntity entity){
        this.id = entity.getId();
        this.mercado = new MercadoDto(entity.getMercado());
        this.status = entity.getStatus();
        this.valorTotal = entity.getValorTotal();
        this.dataCriacao = entity.getDataCriacao();
    }
}
