package ru.yaal.offlinedocs.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Yablokov Aleksey
 */
@Component
@SuppressWarnings("unused")
public class Timer {
    private static final Logger LOG = LoggerFactory.getLogger(Timer.class);

    private LocalDateTime start;

    @PostConstruct
    public void startTimer() {
        start = LocalDateTime.now();
        LOG.info("Timer started at " + start);
    }

    @PreDestroy
    public void stopTimer() {
        LocalDateTime end = LocalDateTime.now();
        LOG.info("Timer stopped at " + end);

        long minutes = ChronoUnit.MINUTES.between(start, end);
        long seconds = ChronoUnit.SECONDS.between(start, end);
        long millis = ChronoUnit.MILLIS.between(start, end);

        if (minutes > 3) {
            LOG.info("Working time {} minutes", minutes);
        } else if (seconds > 3) {
            LOG.info("Working time {} seconds", seconds);
        } else {
            LOG.info("Working time {} millis", millis);
        }
    }
}
