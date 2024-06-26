package com.fayao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogSysAlertTrack {

    @Id
    @Column(columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long no;

    @Column(length = 25)
    private String mid;

    @Column(length = 45, name = "userIp")
    private String userIp;

    @Column(length = 50, name = "alertUri")
    private String alertUri;

    @Column(name = "alertMsg", columnDefinition = "LONGTEXT")
    private String alertMsg;

    @CreationTimestamp
    @Column
    private LocalDateTime createdt;


}
