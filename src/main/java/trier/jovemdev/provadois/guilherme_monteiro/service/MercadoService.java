package trier.jovemdev.provadois.guilherme_monteiro.service;

import trier.jovemdev.provadois.guilherme_monteiro.dto.FaturamentoDiaDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.MercadoDto;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CampoInvalidoException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CnpjExistenteException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;

import java.time.LocalDate;

public interface MercadoService {

    MercadoDto findById(Long id) throws EntidadeNaoEncontradaException;

    MercadoDto create(MercadoDto mercadoDto) throws CnpjExistenteException, CampoInvalidoException;

    FaturamentoDiaDto buscarFaturamentoPeloDia(Long mercadoId, LocalDate dia) throws EntidadeNaoEncontradaException;
}
