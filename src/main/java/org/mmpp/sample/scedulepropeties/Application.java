package org.mmpp.sample.scedulepropeties;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"org.mmpp.sample.scedulepropeties"})
@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

    /**
     * 実行
     * @param args 引数
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        // 実行時のバナーをなくします
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... strings) {

    }
}