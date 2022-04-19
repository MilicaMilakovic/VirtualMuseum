package net.etfbl.ip.vm.models.entities;

import java.time.LocalDateTime;

public class Log {
    private String ip;
    private String url;
    private String page;
    private String refererPage;
    private String queryString;
    private String userAgent;
    private String requestMethod;
    private LocalDateTime timestamp;

    public Log(String ip, String url, String page, String refererPage, String queryString, String userAgent, String requestMethod, LocalDateTime timestamp) {
        this.ip = ip;
        this.url = url;
        this.page = page;
        this.refererPage = refererPage;
        this.queryString = queryString;
        this.userAgent = userAgent;
        this.requestMethod = requestMethod;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return
                "IP='" + ip + '\'' +
                ", URL='" + url + '\'' +
                ", PAGE='" + page + '\'' +
                ", REFERER PAGE='" + refererPage + '\'' +
                ", QUERY STRING='" + queryString + '\'' +
                ", USER AGENT='" + userAgent + '\'' +
                ", REQUEST METHOD='" + requestMethod + '\'' +
                ", TIMESTAMP=" + timestamp ;
    }
}
