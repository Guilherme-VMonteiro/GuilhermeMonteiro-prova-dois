package trier.jovemdev.provadois.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ItemVendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.entity.VendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.*;
import trier.jovemdev.provadois.guilherme_monteiro.repository.VendaRepository;
import trier.jovemdev.provadois.guilherme_monteiro.repository.custom.VendaRepositoryCustom;
import trier.jovemdev.provadois.guilherme_monteiro.service.ItemVendaService;
import trier.jovemdev.provadois.guilherme_monteiro.service.MercadoService;
import trier.jovemdev.provadois.guilherme_monteiro.service.ProdutoService;
import trier.jovemdev.provadois.guilherme_monteiro.service.VendaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaRepositoryCustom vendaRepositoryCustom;

    @Autowired
    private MercadoService mercadoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemVendaService itemVendaService;

    public VendaDto findById(Long vendaId) throws EntidadeNaoEncontradaException {
        return vendaRepositoryCustom.findById(vendaId).orElseThrow(() -> new EntidadeNaoEncontradaException("venda", vendaId));
    }

    public Page<VendaDto> findAllPaginado(Long idMercado, Pageable pageable, StatusVendaEnum status, Long idProduto) throws EntidadeNaoEncontradaException {
        return vendaRepositoryCustom.findAll(idMercado, pageable, status, idProduto);
    }

    public VendaDto criarVendaEmAberto(VendaDto dto) throws EntidadeNaoEncontradaException, CampoInvalidoException, VendaVaziaException, EstoqueInsuficienteException {
        VendaDto vendaDto = new VendaDto();
        vendaDto.setMercado(mercadoService.findById(dto.getMercado().getId()));
        vendaDto.setStatus(StatusVendaEnum.EM_ABERTO);
        vendaDto.setDataCriacao(LocalDate.now());
        vendaDto.setValorTotal(BigDecimal.ZERO);

        if (!dto.getItemVendas().isEmpty()) {
            validaTodosProdutosDeUmaVenda(dto.getItemVendas().stream().map(itemVendaDto -> itemVendaDto.getProdutoDto().getId()).toList());

            VendaDto vendaSalva = new VendaDto(vendaRepository.save(new VendaEntity(vendaDto)));

            vendaSalva = adicionarItemsEmUmaVendaEmAberto(vendaSalva.getId(), dto.getItemVendas());

            atualizaTotalVenda(vendaSalva);

            vendaSalva = findById(vendaSalva.getId());
            validaVenda(vendaSalva);

            return vendaSalva;
        } else {
            throw new VendaVaziaException();
        }
    }

    public VendaDto criarVendaFinalizadaComItens(VendaDto vendaDto) throws EntidadeNaoEncontradaException, CampoInvalidoException {
        return finalizarVenda(criarVendaEmAberto(vendaDto).getId());
    }

    public VendaDto adicionarItemsEmUmaVendaEmAberto(Long vendaId, List<ItemVendaDto> itemsVendaList) throws EntidadeNaoEncontradaException, CampoInvalidoException, VendaFechadaException, EstoqueInsuficienteException {
        VendaDto venda = findById(vendaId);

        if (venda.getStatus().equals(StatusVendaEnum.FINALIZADO)) {
            throw new VendaFechadaException();
        }

        itemsVendaList.sort(Comparator.comparing(itemVendaDto -> itemVendaDto.getProdutoDto().getId()));

        for (ItemVendaDto itemVendaDto : itemsVendaList) {
            ProdutoDto produto = produtoService.findById(itemVendaDto.getProdutoDto().getId());

            if (itemVendaDto.getQuantidade() > produto.getEstoque()) {
                excluirVendaComItens(venda.getId());
                throw new EstoqueInsuficienteException(produto, itemVendaDto.getQuantidade());
            }

            itemVendaDto.setVendaId(venda.getId());
            itemVendaDto.setProdutoDto(produto);
            itemVendaDto.setValorTotal(produto.getValorUnitario().multiply(new BigDecimal(itemVendaDto.getQuantidade())));

            itemVendaService.create(itemVendaDto);

            produtoService.diminuiEstoqueEmVenda(produto, itemVendaDto.getQuantidade());
        }

        atualizaTotalVenda(venda);

        return findById(venda.getId());
    }

    public VendaDto excluirItem(Long vendaId, Long idItemVenda) throws EntidadeNaoEncontradaException {
        VendaDto venda = findById(vendaId);

        validaDelecao(venda);

        ItemVendaDto itemVendaDto = venda.getItemVendas().stream().filter(itemVenda -> itemVenda.getId().equals(idItemVenda)).findFirst().orElseThrow((() -> new EntidadeNaoEncontradaException("ItemVenda", idItemVenda)));

        itemVendaService.delete(itemVendaDto);

        venda.getItemVendas().removeIf(itemVenda -> itemVenda.getId().equals(itemVendaDto.getId()));

        atualizaTotalVenda(venda);

        venda = findById(vendaId);

        if (findById(vendaId).getItemVendas().isEmpty()) {
            excluirVendaSemItens(vendaId);
            return null;
        }

        return venda;
    }

    public void excluirVendaComItens(Long vendaId) throws EntidadeNaoEncontradaException, VendaFechadaException {
        VendaDto venda = findById(vendaId);

        validaDelecao(venda);

        venda.getItemVendas().forEach(itemVenda -> itemVendaService.delete(itemVenda));

        vendaRepository.delete(new VendaEntity(findById(venda.getId())));
    }

    private void excluirVendaSemItens(Long vendaId) throws EntidadeNaoEncontradaException {
        vendaRepository.delete(new VendaEntity(findById(vendaId)));
    }

    public VendaDto finalizarVenda(Long vendaId) throws EntidadeNaoEncontradaException {
        VendaEntity vendaEntity = new VendaEntity(findById(vendaId));

        vendaEntity.finalizarVenda();

        return new VendaDto(vendaRepository.save(vendaEntity));
    }

    private void validaTodosProdutosDeUmaVenda(List<Long> listaDeIds) throws EntidadeNaoEncontradaException {
        List<Long> listaDeProdutoId = new ArrayList<>(listaDeIds);
        List<ProdutoDto> listaDeProdutos = produtoService.coletaProdutosPorListaDeIds(listaDeProdutoId);

        Set<Long> setDeProdutoId = new HashSet<>(listaDeProdutoId);

        if (setDeProdutoId.size() != listaDeProdutos.size()) {
            Collections.sort(listaDeProdutoId);
            for (int i = 0; i < listaDeProdutos.size(); i++) {
                if (Objects.equals(listaDeProdutos.get(i).getId(), listaDeProdutoId.get(i))) {
                    throw new EntidadeNaoEncontradaException("Produto", listaDeProdutoId.get(i));
                }
            }
        }
    }

    private void validaVenda(VendaDto vendaDto) throws VendaVaziaException {
        if (vendaDto.getItemVendas().isEmpty()) {
            excluirVendaSemItens(vendaDto.getId());
            throw new VendaVaziaException();
        }
    }

    private void validaDelecao(VendaDto vendaDto) throws VendaFechadaException, EntidadeNaoEncontradaException {
        if (findById(vendaDto.getId()).getStatus().equals(StatusVendaEnum.FINALIZADO)) {
            throw new VendaFechadaException();
        }
    }

    private void atualizaTotalVenda(VendaDto vendaDto) throws EntidadeNaoEncontradaException {
        VendaDto venda = findById(vendaDto.getId());

        venda.setValorTotal(venda.getItemVendas().stream().map(ItemVendaDto::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));

        vendaRepository.save(new VendaEntity(venda));
    }
}
