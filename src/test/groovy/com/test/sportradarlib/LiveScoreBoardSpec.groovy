package com.test.sportradarlib

import com.test.sportradarlib.data.DataStore
import com.test.sportradarlib.dto.MatchDTO
import com.test.sportradarlib.dto.TeamDTO
import com.test.sportradarlib.event.MatchEvent
import com.test.sportradarlib.fixtures.EventPublisher
import com.test.sportradarlib.service.SummaryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import javax.xml.crypto.Data

@SpringBootTest
class LiveScoreBoardSpec extends Specification {

    @Autowired
    EventPublisher eventPublisher

    @Autowired
    SummaryService summaryService

    def setup() {
        DataStore.matches.forEach({it -> DataStore.endMatch(it)})
    }

    @Unroll
    void 'should add #description match in a happy path'() {
        given: 'a match and a start event'
        def match = buildMatch(homeTeam, awayTeam)
        def event = buildEvent("start", match)
        when: 'the event is sent'
        eventPublisher.publishEvent(event)
        then: 'the matches stored should have the sent match on the event'
        DataStore.getMatches().contains(match)
        where:
        description    | homeTeam | awayTeam
        "France-Italy" | "France" | "Italy"
        "Mexico-Spain" | "Mexico" | "Spain"
    }

    @Unroll
    void 'should update #description match in a happy path'() {
        given: 'a previously started match event sent'
        def match = buildMatch(homeTeam, awayTeam)
        eventPublisher.publishEvent(buildEvent("start", match))
        match.homeTeam.score = homeScore
        match.awayTeam.score = awayScore
        def updateEvent = buildEvent("update", match)
        when: 'an update event is sent with new scores'
        eventPublisher.publishEvent(updateEvent)
        then: 'the matches stored should contain the new one with the scores updated'
        def result = DataStore.getMatches().stream().filter({ m -> (m == match) }).findFirst()
        result.get().homeTeam.score == homeScore
        result.get().awayTeam.score == awayScore
        where:
        description    | homeTeam | awayTeam | homeScore | awayScore
        "France-Italy" | "France" | "Italy"  | 2         | 1
        "Mexico-Spain" | "Mexico" | "Spain"  | 3         | 3
    }

    @Unroll
    void 'should remove #description in a happy path'() {
        given: 'a previously added matches'
        def match = buildMatch(homeTeam, awayTeam)
        eventPublisher.publishEvent(buildEvent("start", match))
        when: 'an end event is published'
        eventPublisher.publishEvent(buildEvent("end", match))
        then: 'the match with the matching key should be removed'
        !DataStore.getMatches().contains(match)
        where:
        description         | homeTeam   | awayTeam
        "Spain-Germany"     | "Spain"    | "Germany"
        "Portugal-Colombia" | "Portugal" | "Colombia"
    }

    void 'should return the summary'() {
        given: 'a previously sent events'
        def match1 = buildMatch("Spain", "France")
        def match2 = buildMatch("Italy", "Germany")
        eventPublisher.publishEvent(buildEvent("start", match1))
        eventPublisher.publishEvent(buildEvent("start", match2))
        when: 'a call to the summary service is made'
        def response = summaryService.getSummary()
        then: 'it should return the matches ordered by the most recently added'
        response == DataStore.getMatches()
    }

    static def buildMatch(String homeTeam, String awayTeam) {
        MatchDTO.builder()
                .homeTeam(TeamDTO.builder().name(homeTeam).build())
                .awayTeam(TeamDTO.builder().name(awayTeam).build())
                .build()
    }

    static def buildEvent(String eventName, MatchDTO match) {
        new MatchEvent(eventName, match)
    }

}
