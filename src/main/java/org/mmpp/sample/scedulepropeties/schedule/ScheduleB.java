package org.mmpp.sample.scedulepropeties.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@PropertySource(value = "classpath:/schedule/b/config.properties" , encoding="UTF-8")
public class ScheduleB extends AbstractSchedule{
    /**
     * ロガー
     */
    public Logger logger = LoggerFactory.getLogger(ScheduleB.class);

    @Override
    @Scheduled(fixedRateString = "${b.fixedRate}")
    public void start() {
        logger.info(" ## run ## : " + environment.getProperty("b.name"));
        logger.info(getQuery());
    }

    @Override
    protected String getPrefix() {
        return "b";
    }
}
