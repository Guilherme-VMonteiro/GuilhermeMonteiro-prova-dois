package trier.jovemdev.provadois.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoQuantidadeDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;
import trier.jovemdev.provadois.guilherme_monteiro.service.VendaService;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping()
    public Page<VendaDto> findAllPaginado(
            @RequestParam Long mercadoId,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @RequestParam(required = false) StatusVendaEnum status,
            @RequestParam(required = false) Long idProduto
    ) {
        return vendaService.findAllPaginado(mercadoId, Pageable.ofSize(size).withPage(page), status, idProduto);
    }

    @PostMapping("/emAberto")
    public VendaDto criarVendaEmAberto(@RequestBody VendaDto vendaDto) {
        return vendaService.criarVendaEmAberto(vendaDto);
    }

    @PutMapping("/{id}/items")
    public VendaDto adicionarItemsVenda(@PathVariable Long id, @RequestBody List<ProdutoQuantidadeDto> produtos) {
        return vendaService.adicionarItemsEmUmaVenda(id, produtos);
    }
}
