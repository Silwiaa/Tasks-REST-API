package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//Exc 29.2
@SpringBootTest
public class DomainTestSuite {

    @Test
    void testCreateAttachmentByTypeDto() {
        //Given
        TrelloDto trelloDto = new TrelloDto(1,2);

        //When
        AttachmentByTypeDto attachmentByTypeDto = new AttachmentByTypeDto(trelloDto);

        //Then
        assertThat(attachmentByTypeDto).isNotNull();
        assertThat(attachmentByTypeDto.getTrello()).isEqualTo(trelloDto);
        assertThat(attachmentByTypeDto.getTrello().getBoard()).isEqualTo(1);
        assertThat(attachmentByTypeDto.getTrello().getCard()).isEqualTo(2);
    }

    @Test
    void testCreateTrelloBadgesDto() {
        //Given
        TrelloDto trelloDto = new TrelloDto(1,2);
        AttachmentByTypeDto attachmentByTypeDto = new AttachmentByTypeDto(trelloDto);

        //When
        TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto(2, attachmentByTypeDto);

        //Then
        assertThat(trelloBadgesDto).isNotNull();
        assertThat(trelloBadgesDto.getVotes()).isEqualTo(2);
    }

    @Test
    void testCreateTrelloCard() {
        //Given
        String name = "Cart 1";
        String description = "Description 1";
        String pos = "top";
        String listId = "1";

        //When
        TrelloCard trelloCard = new TrelloCard(name, description, pos, listId);

        //Then
        assertThat(trelloCard).isNotNull();
        assertThat(trelloCard.getName()).isEqualTo("Cart 1");
        assertThat(trelloCard.getDescription()).isEqualTo("Description 1");
        assertThat(trelloCard.getPos()).isEqualTo("top");
        assertThat(trelloCard.getListId()).isEqualTo("1");
    }
}
