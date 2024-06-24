package com.fundodo.fayaospace.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogSysRestTrack {

    @Id
    @Column(columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long no;

    @Column(length = 25, name = "traceTag")
    private String traceTag;

    @Column(length = 60, name = "requestUri")
    private String requestUri;

    @Column(length = 25)
    private String mid;

    @Column(length = 45, name = "userIp")
    private String userIp;

    @Column(name = "requestData", columnDefinition = "LONGTEXT")
    private String requestData;

    @Column(name = "responseData", columnDefinition = "LONGTEXT")
    private String responseData;

    @Column(length = 10, name = "responseCode")
    private String responseCode;

    @Column(name = "runningTime", columnDefinition = "INT")
    private long runningTime;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

}
