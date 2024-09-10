package trier.jovemdev.provadois.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ItemVendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;
import trier.jovemdev.provadois.guilherme_monteiro.service.VendaService;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("/{id}")
    public VendaDto getById(@PathVariable Long id) {
        return vendaService.findById(id);
    }

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
    public VendaDto adicionarItemsVenda(@PathVariable Long id, @RequestBody List<ItemVendaDto> itemsVendaList) {
        return vendaService.adicionarItemsEmUmaVendaEmAberto(id, itemsVendaList);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vendaService.excluirVendaComItens(id);
    }

    @DeleteMapping("/{id}/itemVenda/{idItemVnda}")
    public VendaDto deleteItemVenda(@PathVariable Long id, @PathVariable Long idItemVnda) {
         return vendaService.excluirItem(id, idItemVnda);
    }
}
