package com.Home_pocket.service;

import com.Home_pocket.dto.ListaMercadoDTO;
import com.Home_pocket.dto.ListaMercadoResponseDTO;
import com.Home_pocket.exception.ItemNaoEncontradoException;
import com.Home_pocket.model.ListaMercado;
import com.Home_pocket.repository.ListaMercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ListaMercadoService {

    private final ListaMercadoRepository listaRepository;

    public ListaMercadoResponseDTO adicionarProduto(ListaMercadoDTO itemDTO){
        ListaMercado itemMercado = ListaMercado.builder().nomeItem(itemDTO.nomeItem()).categoria(itemDTO.categoria()).quantidade(itemDTO.quantidade())
                .stFinalizado(itemDTO.stFinalizado()).precoMedio(itemDTO.precoMedio()).build();
        ListaMercado itemSalvo = listaRepository.save(itemMercado);

        return ListaMercadoResponseDTO.builder().id(itemSalvo.getId()).categoria(itemSalvo.getCategoria())
                .nomeItem(itemSalvo.getNomeItem()).quantidade(itemSalvo.getQuantidade()).precoMedio(itemSalvo.getPrecoMedio()).StFinalizado(itemSalvo.getStFinalizado()).build();
    };

    public ListaMercadoResponseDTO editarProduto(ListaMercadoDTO itemDTO, Long id){
        ListaMercado itemPraAtt = listarItem(id);

        itemPraAtt.setNomeItem(itemDTO.nomeItem());
        itemPraAtt.setCategoria(itemDTO.categoria());
        itemPraAtt.setQuantidade(itemDTO.quantidade());
        itemPraAtt.setPrecoMedio(itemDTO.precoMedio());
        itemPraAtt.setStFinalizado(itemDTO.stFinalizado());

        listaRepository.save(itemPraAtt);

        return ListaMercadoResponseDTO.builder().id(itemPraAtt.getId()).categoria(itemPraAtt.getCategoria())
                .nomeItem(itemPraAtt.getNomeItem()).quantidade(itemPraAtt.getQuantidade()).precoMedio(itemPraAtt.getPrecoMedio()).StFinalizado(itemPraAtt.getStFinalizado()).build();
    }

    public ListaMercado listarItem(Long id){

        return listaRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException(id));
    }

    public Page<ListaMercadoResponseDTO> listarLista(Pageable pageable){
        return listaRepository.findAll(pageable).map(entidade -> ListaMercadoResponseDTO.builder().id(entidade.getId()).categoria(entidade.getCategoria())
                .nomeItem(entidade.getNomeItem()).precoMedio(entidade.getPrecoMedio()).StFinalizado(entidade.getStFinalizado()).quantidade(entidade.getQuantidade()).build());
    }

    public void deletarItemLista(Long id){
        ListaMercado itemParaDeletar = listarItem(id);;
        listaRepository.delete(itemParaDeletar);
    }

    public void deletarLista(){
        listaRepository.deleteAll();
    }
}
