package com.Home_pocket.service;

import com.Home_pocket.dto.ListaMercadoDTO;
import com.Home_pocket.dto.ListaMercadoResponseDTO;
import com.Home_pocket.exception.ItemNaoEncontradoException;
import com.Home_pocket.model.Categoria;
import com.Home_pocket.model.ListaMercado;
import com.Home_pocket.repository.ListaMercadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListaMercadoServiceTest {

    @Mock
    private ListaMercadoRepository listaMercadoRepository;

    @InjectMocks
    private ListaMercadoService listaMercadoService;

    @Test
    void deveAdicionarProdutoComSucesso() {

        ListaMercadoDTO produtoMock = ListaMercadoDTO.builder().stFinalizado(Boolean.FALSE).precoMedio(10.70)
                .nomeItem("Leite").categoria(Categoria.valueOf("LATICINIOS")).quantidade(2).build();

        ListaMercado produtoSalvo = new ListaMercado();

        produtoSalvo.setStFinalizado(Boolean.FALSE);
        produtoSalvo.setCategoria(Categoria.valueOf("LATICINIOS"));
        produtoSalvo.setQuantidade(2);
        produtoSalvo.setNomeItem("Leite");

        when(listaMercadoRepository.save(any(ListaMercado.class))).thenReturn(produtoSalvo);

        ListaMercadoResponseDTO produtoNovo = listaMercadoService.adicionarProduto(produtoMock);

        assertNotNull(produtoNovo, "O DTO retornado esta vazio");
        assertEquals(produtoMock.nomeItem(), produtoNovo.nomeItem());
        verify(listaMercadoRepository, times(1)).save(any(ListaMercado.class));
    }

    @Test
    void deveEditarProdutoComSucesso() {

        ListaMercadoDTO produtoMock = ListaMercadoDTO.builder().stFinalizado(Boolean.FALSE).precoMedio(10.70)
                .nomeItem("Leite").categoria(Categoria.valueOf("LATICINIOS")).quantidade(3).build();

        ListaMercado produtoEditar = new ListaMercado();
        produtoEditar.setStFinalizado(Boolean.FALSE);
        produtoEditar.setCategoria(Categoria.valueOf("LATICINIOS"));
        produtoEditar.setQuantidade(2);
        produtoEditar.setNomeItem("Leite");
        produtoEditar.setId(1L);

        when(listaMercadoRepository.findById(1L)).thenReturn(Optional.of(produtoEditar));

        ListaMercado produtoEditado = new ListaMercado();
        produtoEditado.setStFinalizado(Boolean.FALSE);
        produtoEditado.setCategoria(Categoria.valueOf("LATICINIOS"));
        produtoEditado.setQuantidade(3);
        produtoEditado.setNomeItem("Leite");
        produtoEditado.setId(1L);

        when(listaMercadoRepository.save(any(ListaMercado.class))).thenReturn(produtoEditado);

        ListaMercadoResponseDTO produtoNovo = listaMercadoService.editarProduto(produtoMock, 1L);

        assertNotNull(produtoNovo, "o DTO esta vazio");
        assertEquals(produtoMock.quantidade(), produtoNovo.quantidade());
        verify(listaMercadoRepository, times(1)).findById(1L);
        verify(listaMercadoRepository, times(1)).save(any(ListaMercado.class));

    }

    @Test
    void deveListarListaComPaginacaoComSucesso() {

        Pageable pageable = PageRequest.of(0, 10);

        ListaMercado produtoListado = new ListaMercado();
        produtoListado.setStFinalizado(Boolean.FALSE);
        produtoListado.setCategoria(Categoria.valueOf("LATICINIOS"));
        produtoListado.setQuantidade(3);
        produtoListado.setNomeItem("Leite");
        produtoListado.setId(1L);

        List<ListaMercado> listaMercado= List.of(produtoListado);
        Page<ListaMercado> pageListaMercado = new PageImpl<>(listaMercado, pageable, listaMercado.size());

        when(listaMercadoRepository.findAll(any(Pageable.class))).thenReturn(pageListaMercado);

        Page<ListaMercadoResponseDTO> listaPageableDTO = listaMercadoService.listarLista(pageable);

        assertNotNull(listaPageableDTO, "lista do mercado esta vazia");
        assertEquals(1, listaPageableDTO.getContent().size());

        ListaMercadoResponseDTO dto = listaPageableDTO.getContent().getFirst();

        assertEquals(1L, dto.id());
        assertEquals("Leite", dto.nomeItem());

        verify(listaMercadoRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void deveDeletarItemDaListaComSucesso() {

        ListaMercado itemPraDeletar = ListaMercado.builder().id(1L).stFinalizado(Boolean.FALSE).precoMedio(10.70)
                .nomeItem("Leite").categoria(Categoria.valueOf("LATICINIOS")).quantidade(3).build();

        when(listaMercadoRepository.findById(1L)).thenReturn(Optional.of(itemPraDeletar));
        doNothing().when(listaMercadoRepository).delete(any(ListaMercado.class));

        listaMercadoService.deletarItemLista(itemPraDeletar.getId());

        verify(listaMercadoRepository, times(1)).findById(1L);
        verify(listaMercadoRepository, times(1)).delete(itemPraDeletar);
    }

    @Test
    void deveDeletarListaInteiraComSucesso() {

        doNothing().when(listaMercadoRepository).deleteAll();
        listaMercadoService.deletarLista();

        verify(listaMercadoRepository, times(1)).deleteAll();
    }

    @Test
    void deveRetornarItemNaoEncontradoExceptionQuandoItemNaoEncontrado(){
        when(listaMercadoRepository.findById(1L)).thenReturn(Optional.empty());

        ItemNaoEncontradoException exception = assertThrows(ItemNaoEncontradoException.class, () -> listaMercadoService.listarItem(1L));

        assertEquals("Item da lista nao encontrado para o ID: " + 1L, exception.getMessage());
        verify(listaMercadoRepository, times(1)).findById(1L);
    }
}