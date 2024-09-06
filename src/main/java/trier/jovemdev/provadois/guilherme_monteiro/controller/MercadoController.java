package trier.jovemdev.provadois.guilherme_monteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trier.jovemdev.provadois.guilherme_monteiro.dto.MercadoDto;
import trier.jovemdev.provadois.guilherme_monteiro.service.MercadoService;

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
}
