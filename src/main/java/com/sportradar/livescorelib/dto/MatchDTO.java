package com.sportradar.livescorelib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {

    private TeamDTO homeTeam;

    private TeamDTO awayTeam;

}
