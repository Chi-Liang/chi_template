package com.fundodo.fayaospace.service;

import com.fundodo.fayaospace.model.entity.LogSysAlertTrack;
import com.fundodo.fayaospace.model.entity.LogSysRestTrack;
import com.fundodo.fayaospace.repository.LogSysAlertTrackRepository;
import com.fundodo.fayaospace.repository.LogSysRestTrackRepository;
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
