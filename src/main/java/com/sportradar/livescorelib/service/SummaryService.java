package com.sportradar.livescorelib.service;

import com.sportradar.livescorelib.data.DataStore;
import com.sportradar.livescorelib.dto.MatchDTO;
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
