package trier.jovemdev.provadois.guilherme_monteiro.service;

import trier.jovemdev.provadois.guilherme_monteiro.dto.ItemVendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;

public interface ItemVendaService {

    ItemVendaDto create(ItemVendaDto itemVendaDto) throws EntidadeNaoEncontradaException;
}
