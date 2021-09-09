package com.pjhu.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {

        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter() {
            @Override
            protected boolean shouldLog(HttpServletRequest request) {
                return true;
            }

            @Override
            protected void beforeRequest(HttpServletRequest request, String message) {
                super.logger.info(request.getHeader("tx_xid"));
                super.logger.info(message);
            }

            @Override
            protected void afterRequest(HttpServletRequest request, String message) {
                super.logger.info(message);
            }
        };
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(64000);
        filter.setIncludeHeaders(false);
        return filter;
    }
}
