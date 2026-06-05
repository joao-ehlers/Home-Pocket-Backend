package com.Home_pocket.service;

import com.Home_pocket.dto.ListaMercadoDTO;
import com.Home_pocket.dto.ListaMercadoResponseDTO;
import com.Home_pocket.model.ListaMercado;
import com.Home_pocket.repository.ListaMercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}
