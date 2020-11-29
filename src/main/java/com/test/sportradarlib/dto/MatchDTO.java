package com.test.sportradarlib.dto;

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

    private boolean onCourse;

}
