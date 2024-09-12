package trier.jovemdev.provadois.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trier.jovemdev.provadois.guilherme_monteiro.dto.FaturamentoDiaDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.MercadoDto;
import trier.jovemdev.provadois.guilherme_monteiro.entity.MercadoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CampoInvalidoException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CnpjExistenteException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;
import trier.jovemdev.provadois.guilherme_monteiro.repository.MercadoRepository;
import trier.jovemdev.provadois.guilherme_monteiro.repository.custom.MercadoRepositoryCustom;
import trier.jovemdev.provadois.guilherme_monteiro.service.MercadoService;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class MercadoServiceImpl implements MercadoService {

    @Autowired
    private MercadoRepository mercadoRepository;

    @Autowired
    private MercadoRepositoryCustom mercadoRepositoryCustom;

    public MercadoDto findById(Long id) throws EntidadeNaoEncontradaException {
        return new MercadoDto(mercadoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Mercado", id)));
    }

    public MercadoDto create(MercadoDto mercadoDto) throws CnpjExistenteException, CampoInvalidoException {
        validaCamposCriacao(mercadoDto);

        return new MercadoDto(mercadoRepository.save(new MercadoEntity(mercadoDto)));
    }

    public FaturamentoDiaDto buscarFaturamentoPeloDia(Long mercadoId, LocalDate dia) throws EntidadeNaoEncontradaException {
        return new FaturamentoDiaDto(dia, mercadoRepositoryCustom.findFaturamentoPorDia(mercadoId, dia));
    }

    private void validaCamposCriacao(MercadoDto dto) throws CampoInvalidoException, CnpjExistenteException {
        if (Objects.isNull(dto.getNome()) || dto.getNome().isBlank()) {
            throw new CampoInvalidoException("nome", dto.getNome());
        } else if (Objects.isNull(dto.getNomeFantasia()) || dto.getNomeFantasia().isBlank()) {
            throw new CampoInvalidoException("nomeFantasia", dto.getNomeFantasia());
        } else if (Objects.isNull(dto.getCnpj()) || dto.getCnpj().isBlank()) {
            throw new CampoInvalidoException("cnpj", dto.getCnpj());
        }

        dto.setCnpj(validaCnpj(dto.getCnpj()));
    }

    private String validaCnpj(String cnpj) throws CampoInvalidoException, CnpjExistenteException {
        if (cnpj.length() != 14 || !cnpj.matches("^\\d+$")) {
            if (cnpj.length() != 18
                    || !cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")) {
                throw new CampoInvalidoException("cnpj", cnpj);
            }
        }

        if (cnpj.length() == 18) {
            cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");
        }

        //É NECESSARIO ATUALIZAR ESTE VALIDADOR CASO FOR ATUALIZAR O MERCADO
        if (mercadoRepository.findByCnpj(cnpj).isPresent()) {
            throw new CnpjExistenteException(cnpj);
        }

        return cnpj;
    }
}
