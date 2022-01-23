package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TrelloClient {
    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String userName;

    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = createUrlGetBoard(trelloApiEndpoint, userName, trelloAppKey, trelloToken);

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = createUrlGetCard(trelloApiEndpoint, trelloAppKey, trelloToken, trelloCardDto);

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }

    private URI createUrlGetBoard(String apiEndpoint, String user, String appKey, String appToken) {
        URI createdUrl =  UriComponentsBuilder.fromHttpUrl(apiEndpoint + "/members/" + user +"/boards")
                .queryParam("key", appKey)
                .queryParam("token", appToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();

        return createdUrl;
    }

    private URI createUrlGetCard(String apiEndpoint, String appKey, String appToken, TrelloCardDto trelloCard) {
        URI createdUrl =  UriComponentsBuilder.fromHttpUrl(apiEndpoint + "/cards")
                .queryParam("key", appKey)
                .queryParam("token", appToken)
                .queryParam("name", trelloCard.getName())
                .queryParam("desc", trelloCard.getDescription())
                .queryParam("pos", trelloCard.getPos())
                .queryParam("idList", trelloCard.getListId())
                .build()
                .encode()
                .toUri();

        return createdUrl;
    }
}
