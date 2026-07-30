package com.Home_pocket.service;

import com.Home_pocket.dto.MarketListDTO;
import com.Home_pocket.dto.MarketListResponseDTO;
import com.Home_pocket.exception.ItemNotFoundException;
import com.Home_pocket.model.MarketList;
import com.Home_pocket.repository.MarketListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MarketListService {

    private final MarketListRepository listRepository;

    public MarketListResponseDTO addItem(MarketListDTO itemDTO){
        MarketList marketItem = MarketList.builder().itemName(itemDTO.itemName()).category(itemDTO.category()).quantity(itemDTO.quantity())
                .isFinished(itemDTO.isFinished()).mediumPrice(itemDTO.mediumPrice()).build();
        MarketList savedItem = listRepository.save(marketItem);

        return MarketListResponseDTO.builder().id(savedItem.getId()).category(savedItem.getCategory())
                .itemName(savedItem.getItemName()).quantity(savedItem.getQuantity()).mediumPrice(savedItem.getMediumPrice()).isFinished(savedItem.getIsFinished()).build();
    };

    public MarketListResponseDTO editItem(MarketListDTO itemDTO, Long id){
        MarketList itemToUpdate = findItem(id);

        itemToUpdate.setItemName(itemDTO.itemName());
        itemToUpdate.setCategory(itemDTO.category());
        itemToUpdate.setQuantity(itemDTO.quantity());
        itemToUpdate.setMediumPrice(itemDTO.mediumPrice());
        itemToUpdate.setIsFinished(itemDTO.isFinished());

        listRepository.save(itemToUpdate);

        return MarketListResponseDTO.builder().id(itemToUpdate.getId()).category(itemToUpdate.getCategory())
                .itemName(itemToUpdate.getItemName()).quantity(itemToUpdate.getQuantity()).mediumPrice(itemToUpdate.getMediumPrice()).isFinished(itemToUpdate.getIsFinished()).build();
    }

    public MarketList findItem(Long id){
        return listRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Page<MarketListResponseDTO> listItems(Pageable pageable){
        return listRepository.findAll(pageable).map(entity -> MarketListResponseDTO.builder().id(entity.getId()).category(entity.getCategory())
                .itemName(entity.getItemName()).mediumPrice(entity.getMediumPrice()).isFinished(entity.getIsFinished()).quantity(entity.getQuantity()).build());
    }

    public void deleteItem(Long id){
        MarketList itemToDelete = findItem(id);
        listRepository.delete(itemToDelete);
    }

    public void deleteAllItems(){
        listRepository.deleteAll();
    }
}