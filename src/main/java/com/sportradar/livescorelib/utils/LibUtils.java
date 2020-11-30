package com.sportradar.livescorelib.utils;

import com.sportradar.livescorelib.dto.MatchDTO;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.LinkedHashMap;

@UtilityClass
public class LibUtils {

    public static String extractId(MatchDTO matchDTO) {
        return String.join("-", Arrays.asList(matchDTO.getHomeTeam().getName().toUpperCase(),
                matchDTO.getAwayTeam().getName().toUpperCase()));
    }

    public static boolean checkMatch(MatchDTO match, LinkedHashMap<String, MatchDTO> matches) {
        return matches.get(extractId(match)) != null;
    }

}
