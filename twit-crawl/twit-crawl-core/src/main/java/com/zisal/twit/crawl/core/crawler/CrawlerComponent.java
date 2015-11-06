package com.zisal.twit.crawl.core.crawler;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface CrawlerComponent {
    String value() default "";
}
