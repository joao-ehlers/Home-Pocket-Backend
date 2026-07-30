package com.homepocket.service;

import com.homepocket.dto.MarketListDTO;
import com.homepocket.dto.MarketListResponseDTO;
import com.homepocket.exception.ItemNotFoundException;
import com.homepocket.model.Category;
import com.homepocket.model.MarketList;
import com.homepocket.repository.MarketListRepository;
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
class MarketListServiceTest {

    @Mock
    private MarketListRepository marketListRepository;

    @InjectMocks
    private MarketListService marketListService;

    @Test
    void shouldAddItemWithSucess() {

        MarketListDTO itemMock = MarketListDTO.builder().isFinished(Boolean.FALSE).mediumPrice(10.70)
                .itemName("Milk").category(Category.valueOf("DAIRY")).quantity(2).build();

        MarketList savedItem = new MarketList();

        savedItem.setIsFinished(Boolean.FALSE);
        savedItem.setCategory(Category.valueOf("DAIRY"));
        savedItem.setQuantity(2);
        savedItem.setItemName("Milk");

        when(marketListRepository.save(any(MarketList.class))).thenReturn(savedItem);

        MarketListResponseDTO newItem = marketListService.addItem(itemMock);

        assertNotNull(newItem, "The returned DTO is empty");
        assertEquals(itemMock.itemName(), newItem.itemName());
        verify(marketListRepository, times(1)).save(any(MarketList.class));
    }

    @Test
    void shouldEditItemSuccessfully() {

        MarketListDTO itemMock = MarketListDTO.builder().isFinished(Boolean.FALSE).mediumPrice(10.70)
                .itemName("Milk").category(Category.valueOf("DAIRY")).quantity(3).build();

        MarketList itemToEdit = new MarketList();
        itemToEdit.setIsFinished(Boolean.FALSE);
        itemToEdit.setCategory(Category.valueOf("DAIRY"));
        itemToEdit.setQuantity(2);
        itemToEdit.setItemName("Milk");
        itemToEdit.setId(1L);

        when(marketListRepository.findById(1L)).thenReturn(Optional.of(itemToEdit));

        MarketList editedItem = new MarketList();
        editedItem.setIsFinished(Boolean.FALSE);
        editedItem.setCategory(Category.valueOf("DAIRY"));
        editedItem.setQuantity(3);
        editedItem.setItemName("Milk");
        editedItem.setId(1L);

        when(marketListRepository.save(any(MarketList.class))).thenReturn(editedItem);

        MarketListResponseDTO newItem = marketListService.editItem(itemMock, 1L);

        assertNotNull(newItem, "The returned DTO is empty");
        assertEquals(editedItem.getQuantity(), newItem.quantity());
        verify(marketListRepository, times(1)).findById(1L);
        verify(marketListRepository, times(1)).save(any(MarketList.class));

    }

    @Test
    void shouldListItemsWithPaginationSuccessfully() {

        Pageable pageable = PageRequest.of(0, 10);

        MarketList listedItem = new MarketList();
        listedItem.setIsFinished(Boolean.FALSE);
        listedItem.setCategory(Category.valueOf("DAIRY"));
        listedItem.setQuantity(3);
        listedItem.setItemName("Milk");
        listedItem.setId(1L);

        List<MarketList> marketList = List.of(listedItem);
        Page<MarketList> marketListPage = new PageImpl<>(marketList, pageable, marketList.size());

        when(marketListRepository.findAll(any(Pageable.class))).thenReturn(marketListPage);

        Page<MarketListResponseDTO> pageableDTO = marketListService.listItems(pageable);

        assertNotNull(pageableDTO, "The market list is empty");
        assertEquals(1, pageableDTO.getContent().size());

        MarketListResponseDTO dto = pageableDTO.getContent().getFirst();

        assertEquals(1L, dto.id());
        assertEquals("Milk", dto.itemName());

        verify(marketListRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void shouldDeleteItemFromListSuccessfully() {

        MarketList itemToDelete = MarketList.builder().id(1L).isFinished(Boolean.FALSE).mediumPrice(10.70)
                .itemName("Milk").category(Category.valueOf("DAIRY")).quantity(3).build();

        when(marketListRepository.findById(1L)).thenReturn(Optional.of(itemToDelete));
        doNothing().when(marketListRepository).delete(any(MarketList.class));

        marketListService.deleteItem(itemToDelete.getId());

        verify(marketListRepository, times(1)).findById(1L);
        verify(marketListRepository, times(1)).delete(itemToDelete);
    }

    @Test
    void shouldDeleteEntireListSuccessfully() {

        doNothing().when(marketListRepository).deleteAll();
        marketListService.deleteAllItems();

        verify(marketListRepository, times(1)).deleteAll();
    }

    @Test
    void shouldReturnItemNotFoundExceptionWhenItemNotFound(){
        when(marketListRepository.findById(1L)).thenReturn(Optional.empty());

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> marketListService.findItem(1L));

        assertEquals("List item not found for ID: " + 1L, exception.getMessage());
        verify(marketListRepository, times(1)).findById(1L);
    }
}