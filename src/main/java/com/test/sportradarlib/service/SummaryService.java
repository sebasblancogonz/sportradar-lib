package com.test.sportradarlib.service;

import com.test.sportradarlib.data.DataStore;
import com.test.sportradarlib.dto.MatchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Slf4j
@Service
public class SummaryService {

    public LinkedList<MatchDTO> getSummary() {
        LinkedList<MatchDTO> matches = DataStore.getMatches();
        matches.forEach(m -> log.info(m.getHomeTeam().getName() + " " + m.getHomeTeam().getScore() + " - "
                + m.getAwayTeam().getName() + " " + m.getAwayTeam().getScore()));
        return matches;
    }

}
