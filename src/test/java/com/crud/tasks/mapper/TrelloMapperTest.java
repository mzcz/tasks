package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;


    @Test
    public void mapToBoardsListTest()  {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1","testList1",false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2","testList2",true);

        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(trelloListDto1);
        trelloListDto.add(trelloListDto2);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1","testBoard1",trelloListDto);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2","testBoard2",trelloListDto);

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(trelloBoardDto1);
        trelloBoardDto.add(trelloBoardDto2);

        //When
        List<TrelloBoard> fetchedTrelloBoards =  trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        assertEquals(2, fetchedTrelloBoards.size());


    }

    @Test
    public void mapToBoardsDtoTest()  {
        //Given
        TrelloList trelloList1 = new TrelloList("1","testList1",true);
        TrelloList trelloList2 = new TrelloList("2","testList2",true);

        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(trelloList1);
        trelloList.add(trelloList2);

        TrelloBoard trelloBoard1 = new TrelloBoard("1","testBoard1",trelloList);
        TrelloBoard trelloBoard2 = new TrelloBoard("2","testBoard2",trelloList);
        TrelloBoard trelloBoard3 = new TrelloBoard("3","testBoard2",trelloList);

        List<TrelloBoard> trelloBoard = new ArrayList<>();

        trelloBoard.add(trelloBoard1);
        trelloBoard.add(trelloBoard2);
        trelloBoard.add(trelloBoard3);

        //When
        List<TrelloBoardDto> fetchedTrelloBoardsDto =  trelloMapper.mapToBoardsDto(trelloBoard);

        //Then
        assertEquals(3, fetchedTrelloBoardsDto.size());

    }

    @Test
    public void mapToListTest()  {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1","testList1",false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2","testList2",true);

        List<TrelloListDto> trelloListDto = new ArrayList<>();

        trelloListDto.add(trelloListDto1);
        trelloListDto.add(trelloListDto2);

        //When
        List<TrelloList> fetchedTrelloLists =  trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(2, fetchedTrelloLists.size());


    }

    @Test
    public void mapToListDtoTest()  {
        //Given
        TrelloList trelloList1 = new TrelloList("1","testList1",true);
        TrelloList trelloList2 = new TrelloList("2","testList2",true);
        TrelloList trelloList3 = new TrelloList("3","testList3",true);
        TrelloList trelloList4 = new TrelloList("4","testList4",true);

        List<TrelloList> trelloList = new ArrayList<>();

        trelloList.add(trelloList1);
        trelloList.add(trelloList2);
        trelloList.add(trelloList3);
        trelloList.add(trelloList4);

        //When
        List<TrelloListDto> fetchedTrelloListsDto =  trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(4, fetchedTrelloListsDto.size());

    }

    @Test
    public void mapToCardDtoTest()  {
        //Given
        TrelloCard trelloCard = new TrelloCard("card1","description1","1","1");

        //When
        TrelloCardDto fetchedTrelloCardDto =  trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("card1", fetchedTrelloCardDto.getName());

    }

    @Test
    public void mapToCardTest()  {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("cardDto1","descriptionDto1","1","1");

        //When
        TrelloCard fetchedTrelloCard =  trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("cardDto1", fetchedTrelloCard.getName());

    }
}
