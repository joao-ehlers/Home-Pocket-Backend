package com.Home_pocket.controller;


import com.Home_pocket.dto.MarketListDTO;
import com.Home_pocket.dto.MarketListResponseDTO;
import com.Home_pocket.service.MarketListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("market-list")
public class MarketListController {

    private final MarketListService marketListService;

    @GetMapping
    public ResponseEntity<Page<MarketListResponseDTO>> listItems(@PageableDefault(sort="id") Pageable pageable){
        Page<MarketListResponseDTO> list = marketListService.listItems(pageable);

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<MarketListResponseDTO> addItemToList(@Valid @RequestBody MarketListDTO itemList){
        return ResponseEntity.status(HttpStatus.CREATED).body(marketListService.addItem(itemList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarketListResponseDTO> updateItemFromList(@PathVariable Long id, @Valid @RequestBody MarketListDTO itemUpdateList){
        return ResponseEntity.ok(marketListService.editItem(itemUpdateList, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemFromList(@PathVariable Long id){
        marketListService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearList(){
        marketListService.deleteAllItems();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
