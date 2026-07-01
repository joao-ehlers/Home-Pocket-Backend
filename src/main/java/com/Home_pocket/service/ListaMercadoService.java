package com.Home_pocket.service;

import com.Home_pocket.dto.ListaMercadoDTO;
import com.Home_pocket.dto.ListaMercadoResponseDTO;
import com.Home_pocket.model.ListaMercado;
import com.Home_pocket.repository.ListaMercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class ListaMercadoService {

    private final ListaMercadoRepository listaRepository;

    public ListaMercadoResponseDTO AdicionarProduto(ListaMercadoDTO itemDTO){
        ListaMercado itemMercado = ListaMercado.builder().nomeItem(itemDTO.nomeItem()).categoria(itemDTO.categoria()).quantidade(itemDTO.quantidade())
                .StFinalizado(itemDTO.StFinalizado()).precoMedio(itemDTO.precoMedio()).build();
        ListaMercado itemSalvo = listaRepository.save(itemMercado);

        return ListaMercadoResponseDTO.builder().id(itemSalvo.getId()).categoria(itemSalvo.getCategoria())
                .nomeItem(itemSalvo.getNomeItem()).precoMedio(itemSalvo.getPrecoMedio()).StFinalizado(itemSalvo.getStFinalizado()).build();
    };

    public ListaMercadoResponseDTO editarProduto(ListaMercadoDTO itemDTO, Long id){
        ListaMercado itemPraAtt = listaRepository.findById(id).orElseThrow(() -> new RuntimeException("Item da lista nao encontrado"));

        itemPraAtt.setNomeItem(itemDTO.nomeItem());
        itemPraAtt.setCategoria(itemDTO.categoria());
        itemPraAtt.setQuantidade(itemDTO.quantidade());
        itemPraAtt.setPrecoMedio(itemDTO.precoMedio());
        itemPraAtt.setStFinalizado(itemDTO.StFinalizado());

        listaRepository.save(itemPraAtt);

        return ListaMercadoResponseDTO.builder().id(itemPraAtt.getId()).categoria(itemPraAtt.getCategoria())
                .nomeItem(itemPraAtt.getNomeItem()).precoMedio(itemPraAtt.getPrecoMedio()).StFinalizado(itemPraAtt.getStFinalizado()).build();

    }

    public List<ListaMercadoResponseDTO> listarLista(){
        return listaRepository.findAll().stream().map(entidade -> {
            return ListaMercadoResponseDTO.builder().id(entidade.getId()).categoria(entidade.getCategoria())
                    .nomeItem(entidade.getNomeItem()).precoMedio(entidade.getPrecoMedio()).StFinalizado(entidade.getStFinalizado()).build();
        }).toList();
    }

    public void deletarItemLista(Long id){
        ListaMercado itemParaDeletar = listaRepository.findById(id).orElseThrow(() -> new RuntimeException("Item nao encontrado"));
        listaRepository.delete(itemParaDeletar);
    }
}
