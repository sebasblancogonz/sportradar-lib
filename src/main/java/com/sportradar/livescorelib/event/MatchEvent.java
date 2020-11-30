package com.sportradar.livescorelib.event;

import com.sportradar.livescorelib.dto.MatchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchEvent {

    private String name;

    private MatchDTO data;

}
