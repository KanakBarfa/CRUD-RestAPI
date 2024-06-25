package com.javatpoint;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class CustomFilter extends Filter<ILoggingEvent> {

    private static final int SAMPLE_RATE = 10; // Log 1 out of every 10 INFO logs
    private int counter = 0;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel() == Level.INFO) {
            counter++;
            if (counter % SAMPLE_RATE == 0) {
                return FilterReply.ACCEPT;
            } else {
                return FilterReply.DENY;
            }
        }
        return FilterReply.ACCEPT;
    }
}
