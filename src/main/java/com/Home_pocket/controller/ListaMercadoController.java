package com.Home_pocket.controller;

import com.Home_pocket.dto.ListaMercadoDTO;
import com.Home_pocket.dto.ListaMercadoResponseDTO;
import com.Home_pocket.service.ListaMercadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("lista-mercado")
public class ListaMercadoController {

    private final ListaMercadoService listaMercadoService;

    @GetMapping
    public ResponseEntity<Page<ListaMercadoResponseDTO>> listarItens(@PageableDefault(size = 10, sort="id") Pageable pageable){
        Page<ListaMercadoResponseDTO> lista = listaMercadoService.listarLista(pageable);

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ListaMercadoResponseDTO> adicionaItemLista(@Valid @RequestBody ListaMercadoDTO itemLista){
        return ResponseEntity.status(HttpStatus.CREATED).body(listaMercadoService.adicionarProduto(itemLista));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ListaMercadoResponseDTO> atualizaItemLista(@PathVariable Long id, @Valid @RequestBody ListaMercadoDTO itemAlterarLista){
        return ResponseEntity.ok(listaMercadoService.editarProduto(itemAlterarLista, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaItemLista(@PathVariable Long id){
        listaMercadoService.deletarItemLista(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> limpaLista(){
        listaMercadoService.deletarLista();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
