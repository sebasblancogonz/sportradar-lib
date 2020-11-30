package com.sportradar.livescorelib.data;

import com.sportradar.livescorelib.dto.MatchDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static com.sportradar.livescorelib.utils.LibUtils.checkMatch;
import static com.sportradar.livescorelib.utils.LibUtils.extractId;

@Slf4j
@UtilityClass
public class DataStore {

    private final LinkedHashMap<String, MatchDTO> matches = new LinkedHashMap<>();

    public void addMatch(MatchDTO match) {
        matches.put(extractId(match), match);
    }

    public void updateMatch(MatchDTO match) {
        if (checkMatch(match, matches)) {
            matches.replace(extractId(match), match);
        } else {
            log.warn("Match not valid for update.");
        }
    }

    public void endMatch(MatchDTO matchDTO) {
        if (checkMatch(matchDTO, matches)) {
            matches.remove(extractId(matchDTO));
        } else {
            log.warn("Cannot end an unregistered match.");
        }
    }

    public List<MatchDTO> getMatches() {
        List<MatchDTO> matchList = new ArrayList<>(matches.values());
        Collections.reverse(matchList);
        return matchList;
    }

}
