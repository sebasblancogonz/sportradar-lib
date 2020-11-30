package com.sportradar.livescorelib.data;

import com.sportradar.livescorelib.dto.MatchDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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

    public LinkedList<MatchDTO> getMatches () {
        LinkedList<MatchDTO> matchList = new LinkedList<>();
        matches.forEach((s, matchDTO) -> matchList.add(matchDTO));
        return matchList;
    }

}
