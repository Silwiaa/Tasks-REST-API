package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//Exc 29.2
@ExtendWith(MockitoExtension.class)
public class TrelloControllerTestSuite {

    @InjectMocks
    private TrelloController trelloController;

    @Mock
    private TrelloFacade trelloFacade;

    @Test
    void shouldFetchBoard() {
        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "test", trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloController.getTrelloBoards();

        // Then
        assertThat(trelloBoardDtos.size()).isEqualTo(1);
    }

    @Test
    void shouldCreateCard() {
        //Given
        TrelloCardDto trelloCartDto = new TrelloCardDto("Card 1", "Description 1", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", trelloCartDto.getName(), "test");

        when(trelloFacade.createCard(trelloCartDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto resultCreatedTrelloCardDto = trelloController.createTrelloCard(trelloCartDto);

        //Then
        assertEquals("1", resultCreatedTrelloCardDto.getId());
        assertEquals("Card 1",resultCreatedTrelloCardDto.getName());
        assertEquals("test",resultCreatedTrelloCardDto.getShortUrl());
    }
}
