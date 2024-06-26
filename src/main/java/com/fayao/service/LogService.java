package com.fayao.service;

import com.fayao.model.entity.LogSysAlertTrack;
import com.fayao.model.entity.LogSysRestTrack;
import com.fayao.repository.LogSysAlertTrackRepository;
import com.fayao.repository.LogSysRestTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogSysAlertTrackRepository logSysAlertTrackRepository;

    private final LogSysRestTrackRepository logSysRestTrackRepository;

    public void loggingRest(LogSysRestTrack logSysRestTrack) {
        logSysRestTrackRepository.save(logSysRestTrack);
    }

    @Async
    public void loggingAlert(LogSysAlertTrack logSysAlertTrack) {
        logSysAlertTrackRepository.save(logSysAlertTrack);
    }
}
