package trier.jovemdev.provadois.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provadois.guilherme_monteiro.dto.FaturamentoDiaDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.MercadoDto;
import trier.jovemdev.provadois.guilherme_monteiro.service.MercadoService;

import java.time.LocalDate;

@RestController
@RequestMapping("/mercado")
public class MercadoController {

    @Autowired
    private MercadoService mercadoService;

    //1
    @PostMapping
    public MercadoDto create(@RequestBody MercadoDto mercadoDto) {
        return mercadoService.create(mercadoDto);
    }

    @GetMapping("/{mercadoId}/{dia}")
    public FaturamentoDiaDto buscarFaturamentoPorDia(@PathVariable Long mercadoId, @PathVariable LocalDate dia) {
        return mercadoService.buscarFaturamentoPeloDia(mercadoId, dia);
    }
}
