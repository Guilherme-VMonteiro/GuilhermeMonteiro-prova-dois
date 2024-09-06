package trier.jovemdev.provadois.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.service.ProdutoService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //5
    @GetMapping()
    public Page<ProdutoDto> findAllPaginado(
            @RequestParam Long mercadoId,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @RequestParam(required = false) String nomeProduto
    ) {
        return produtoService.findAll(mercadoId, Pageable.ofSize(size).withPage(page), nomeProduto);
    }

    //4
    @PostMapping
    public ProdutoDto create(@RequestBody ProdutoDto produtoDto) {
        return produtoService.create(produtoDto);
    }

    //6
    @PutMapping("/{id}/estoque/{estoque}")
    public ProdutoDto atualizaEstoque(@PathVariable Long id, @PathVariable Integer estoque) {
        return produtoService.atualizaEstoque(id, estoque);
    }

    //7
    @PutMapping("/{id}/valor/{valor}")
    public ProdutoDto atualizaValorUnitario(@PathVariable Long id, @PathVariable BigDecimal valor) {
        return produtoService.atualizaValorUnitario(id, valor);
    }
}
