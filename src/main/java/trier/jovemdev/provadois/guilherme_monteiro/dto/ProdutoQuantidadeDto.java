package trier.jovemdev.provadois.guilherme_monteiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoQuantidadeDto {

    private ProdutoDto produto;
    private Integer quantidade;
}
