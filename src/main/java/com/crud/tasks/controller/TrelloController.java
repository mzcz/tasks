package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards().stream()
        .filter(trelloBoard -> trelloBoard.getName().contains("Kodilla"))
                .collect(Collectors.toList());

        trelloBoards.forEach(trelloBoardDto -> {

            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());

                            System.out.println("This board contains lists: ");

        trelloBoardDto.getLists().forEach(trelloList ->
                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.getIsClosed()));

        });

        return trelloClient.getTrelloBoards();
    }


    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard", headers = "Accept=application/json")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return trelloClient.createNewCard(trelloCardDto);
    }
}