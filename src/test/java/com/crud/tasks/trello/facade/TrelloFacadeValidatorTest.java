package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloFacadeValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TrelloFacade trelloFacade;

    @Test
    public void checkTrelloValidatorTest(){
        //Given
        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "my_list",false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "my_task", mappedTrelloLists));
        mappedTrelloBoards.add(new TrelloBoard("2", "test", mappedTrelloLists));
        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(mappedTrelloBoards);
        //Then
        assertNotNull(filteredBoards);
        assertEquals(1, filteredBoards.size());

    }

}
