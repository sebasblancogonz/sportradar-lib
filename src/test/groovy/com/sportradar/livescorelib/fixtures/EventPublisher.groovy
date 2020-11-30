package com.sportradar.livescorelib.fixtures

import com.sportradar.livescorelib.event.MatchEvent
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
@Slf4j
class EventPublisher {

    @Autowired
    final ApplicationEventPublisher publisher

    EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher
    }

    void publishEvent(MatchEvent event) {
        log.info("Event sent")
        publisher.publishEvent(event)
    }

}
