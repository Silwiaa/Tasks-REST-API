package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void shouldFetchEmptyList() {
        // Given
        List<TrelloListDto> trelloLists = List.of(
                new TrelloListDto(
                        "1",
                        "test_list",
                        false));

        List<TrelloBoardDto> trelloBoards = List.of(
                new TrelloBoardDto(
                        "1",
                        "test",
                        trelloLists));

        List<TrelloList> mappedTrelloLists = List.of(
                new TrelloList(
                        "1",
                        "test_list",
                        false));

        List<TrelloBoard> mappedTrelloBoards = List.of(
                new TrelloBoard(
                        "1",
                        "test",
                        mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoardsDto = List.of(new TrelloBoardDto("1", "test", trelloLists));
        List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "test_list", false));
        List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
        when(trelloMapper.mapToBoards(trelloBoardsDto)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(mappedTrelloBoards)).thenReturn(trelloBoardsDto);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> resultTrelloBoardsDto = trelloFacade.fetchTrelloBoards();

        // Then
        assertThat(resultTrelloBoardsDto).isNotNull();
        assertThat(resultTrelloBoardsDto.size()).isEqualTo(1);

        resultTrelloBoardsDto.forEach(trelloBoardDto -> {

            assertThat(trelloBoardDto.getId()).isEqualTo("1");
            assertThat(trelloBoardDto.getName()).isEqualTo("test");

            trelloBoardDto.getLists().forEach(trelloListDto -> {

                assertThat(trelloListDto.getId()).isEqualTo("1");
                assertThat(trelloListDto.getName()).isEqualTo("test_list");
                assertThat(trelloListDto.isClosed()).isFalse();

            });
        });
    }

    //Exc 29.2
    @Test
    void shouldCreateCreatedTrelloCartDto() {
        //Given
        TrelloCardDto trelloCartDto = new TrelloCardDto("Card 1", "Description 1", "top", "1");
        TrelloCard trelloCard = new TrelloCard("Card 1", "Description 1", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Card 1", "test");


        when(trelloMapper.mapToCard(trelloCartDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCartDto);
        when(trelloService.createTrelloCard(trelloCartDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto resultCreatedTrelloCardDto = trelloFacade.createCard(trelloCartDto);

        //Then
        assertEquals("1", resultCreatedTrelloCardDto.getId());
        assertEquals("Card 1",resultCreatedTrelloCardDto.getName());
        assertEquals("test",resultCreatedTrelloCardDto.getShortUrl());
    }
}