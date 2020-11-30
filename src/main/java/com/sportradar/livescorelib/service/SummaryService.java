package com.sportradar.livescorelib.service;

import com.sportradar.livescorelib.data.DataStore;
import com.sportradar.livescorelib.dto.MatchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SummaryService {

    public List<MatchDTO> getSummary() {
        List<MatchDTO> matches = DataStore.getMatches();
        matches.forEach(m -> log.info(m.getHomeTeam().getName() + " " + m.getHomeTeam().getScore() + " - "
                + m.getAwayTeam().getName() + " " + m.getAwayTeam().getScore()));
        return matches;
    }

}
