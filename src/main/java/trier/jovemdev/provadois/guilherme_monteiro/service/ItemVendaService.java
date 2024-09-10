package trier.jovemdev.provadois.guilherme_monteiro.service;

import trier.jovemdev.provadois.guilherme_monteiro.dto.ItemVendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;

public interface ItemVendaService {

    ItemVendaDto findById(Long id) throws EntidadeNaoEncontradaException;

    ItemVendaDto create(ItemVendaDto itemVendaDto);

    void delete(ItemVendaDto itemVendaDto) throws EntidadeNaoEncontradaException;
}
