package com.sportradar.livescorelib.listener;

import com.sportradar.livescorelib.event.MatchEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.sportradar.livescorelib.data.DataStore.*;

@Slf4j
@Component
public class LiveScoreListener {

    @EventListener(condition = "#event.name eq 'start'")
    void handleMatchStartEvent(MatchEvent event) {
        addMatch(event.getData());
    }

    @EventListener(condition = "#event.name eq 'end'")
    void handleMatchEndEvent(MatchEvent event) {
        endMatch(event.getData());
    }

    @EventListener(condition = "#event.name eq 'update'")
    void handleMatchUpdateEvent(MatchEvent event) {
        updateMatch(event.getData());
    }

}
