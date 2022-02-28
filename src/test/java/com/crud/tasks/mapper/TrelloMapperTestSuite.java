package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "List 1", false);

        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDto1);

        TrelloListDto trelloListDto2 = new TrelloListDto("2", "List 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "List 3", false);
        TrelloListDto trelloListDto4 = new TrelloListDto("4", "List 4", false);

        List<TrelloListDto> lists2 = new ArrayList<>();
        lists2.add(trelloListDto2);
        lists2.add(trelloListDto3);
        lists2.add(trelloListDto4);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "Board 1", lists);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "Board 2", lists2);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);

        //When
        List<TrelloBoard> resultTrelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        List<TrelloList> resultList = resultTrelloBoards.get(0).getLists();
        List<TrelloList> resultList2 = resultTrelloBoards.get(1).getLists();

        assertEquals(resultTrelloBoards.size(), 2);

        assertEquals(resultList.size(), 1);
        assertEquals(resultList2.size(), 3);

        assertEquals(resultList.get(0).getId(), "1");
        assertEquals(resultList.get(0).getName(), "List 1");
        assertFalse(resultList.get(0).isClosed());

        assertEquals(resultList2.get(0).getId(), "2");
        assertEquals(resultList2.get(0).getName(), "List 2");
        assertFalse(resultList2.get(0).isClosed());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "List 1", false);

        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);

        TrelloList trelloList2 = new TrelloList("2", "List 2", false);
        TrelloList trelloList3 = new TrelloList("3", "List 3", false);
        TrelloList trelloList4 = new TrelloList("4", "List 4", false);

        List<TrelloList> lists2 = new ArrayList<>();
        lists2.add(trelloList2);
        lists2.add(trelloList3);
        lists2.add(trelloList4);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Board 1", lists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Board 2", lists2);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> resultTrelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        List<TrelloListDto> resultList = resultTrelloBoardsDto.get(0).getLists();
        List<TrelloListDto> resultList2 = resultTrelloBoardsDto.get(1).getLists();

        assertEquals(resultTrelloBoardsDto.size(), 2);

        assertEquals(resultList.size(), 1);
        assertEquals(resultList2.size(), 3);

        assertEquals(resultList.get(0).getId(), "1");
        assertEquals(resultList.get(0).getName(), "List 1");
        assertFalse(resultList.get(0).isClosed());

        assertEquals(resultList2.get(0).getId(), "2");
        assertEquals(resultList2.get(0).getName(), "List 2");
        assertFalse(resultList2.get(0).isClosed());
    }

    @Test
    void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "List 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "List 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "List 3", false);

        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDto1);
        lists.add(trelloListDto2);
        lists.add(trelloListDto3);

        //When
        List<TrelloList> resultTrelloLists = trelloMapper.mapToList(lists);

        //Then
        TrelloList resultTrelloList1 = resultTrelloLists.get(0);
        TrelloList resultTrelloList2 = resultTrelloLists.get(1);
        TrelloList resultTrelloList3 = resultTrelloLists.get(2);

        assertEquals(resultTrelloLists.size(), 3);

        assertEquals(resultTrelloList1.getId(), "1");
        assertEquals(resultTrelloList1.getName(), "List 1");
        assertFalse(resultTrelloList1.isClosed());

        assertEquals(resultTrelloList2.getId(), "2");
        assertEquals(resultTrelloList2.getName(), "List 2");
        assertFalse(resultTrelloList2.isClosed());

        assertEquals(resultTrelloList3.getId(), "3");
        assertEquals(resultTrelloList3.getName(), "List 3");
        assertFalse(resultTrelloList3.isClosed());
    }

    @Test
    void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "List 1", false);
        TrelloList trelloList2 = new TrelloList("2", "List 2", false);
        TrelloList trelloList3 = new TrelloList("3", "List 3", false);

        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList1);
        lists.add(trelloList2);
        lists.add(trelloList3);

        //When
        List<TrelloListDto> resultTrelloListsDto = trelloMapper.mapToListDto(lists);

        //Then
        TrelloListDto resultTrelloListDto1 = resultTrelloListsDto.get(0);
        TrelloListDto resultTrelloListDto2 = resultTrelloListsDto.get(1);
        TrelloListDto resultTrelloListDto3 = resultTrelloListsDto.get(2);

        assertEquals(resultTrelloListsDto.size(), 3);

        assertEquals(resultTrelloListDto1.getId(), "1");
        assertEquals(resultTrelloListDto1.getName(), "List 1");
        assertFalse(resultTrelloListDto1.isClosed());

        assertEquals(resultTrelloListDto2.getId(), "2");
        assertEquals(resultTrelloListDto2.getName(), "List 2");
        assertFalse(resultTrelloListDto2.isClosed());

        assertEquals(resultTrelloListDto3.getId(), "3");
        assertEquals(resultTrelloListDto3.getName(), "List 3");
        assertFalse(resultTrelloListDto3.isClosed());
    }

    //Exc 29.2
    @Test
    void testMapToCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("Cart 1", "Description 1", "top", "1");

        //When
        TrelloCardDto resultTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertThat(resultTrelloCardDto).isNotNull();
        assertThat(resultTrelloCardDto.getName()).isEqualTo("Cart 1");
        assertThat(resultTrelloCardDto.getDescription()).isEqualTo("Description 1");
        assertThat(resultTrelloCardDto.getPos()).isEqualTo("top");
        assertThat(resultTrelloCardDto.getListId()).isEqualTo("1");
    }
}
