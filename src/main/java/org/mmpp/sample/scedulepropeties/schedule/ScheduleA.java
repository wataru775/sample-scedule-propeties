package org.mmpp.sample.scedulepropeties.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:schedule/a/config.properties" , encoding="UTF-8")
public class ScheduleA extends AbstractSchedule{
    /**
     * ロガー
     */
    private Logger logger = LoggerFactory.getLogger(ScheduleA.class);


    @Override
    protected String getPrefix() {
        return "a";
    }

    @Override
    @Scheduled(fixedRateString = "${a.fixedRate}")
    public void start() {
        logger.info(" ** run ** " + environment.getProperty("a.name"));
        logger.info(getQuery());
    }
}