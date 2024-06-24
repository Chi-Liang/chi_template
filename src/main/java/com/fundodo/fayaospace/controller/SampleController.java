package com.fundodo.fayaospace.controller;

import com.fundodo.fayaospace.model.dto.SampleDto;
import com.fundodo.fayaospace.model.entity.Sample;
import com.fundodo.fayaospace.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @PostMapping("/getAllSample")
    public ResponseEntity<List<Sample>> getAllSample() {
        return ResponseEntity.ok(sampleService.getAllSample());
    }

    @PostMapping("/getByConditionSample")
    public ResponseEntity<List<Sample>> getByConditionSample(@Validated(SampleDto.GetByCondition.class) @RequestBody SampleDto sampleDto) {
        return ResponseEntity.ok(sampleService.getSampleByNameAndMobilePhone(sampleDto));
    }

    @PostMapping("/saveSample")
    public ResponseEntity<Sample> saveSample(@Validated(SampleDto.Save.class) @RequestBody SampleDto sampleDto) {
        return ResponseEntity.ok(sampleService.saveSample(sampleDto));
    }

    @PostMapping("/editSample")
    public ResponseEntity<Sample> editSample(@Validated(SampleDto.Edit.class) @RequestBody SampleDto sampleDto) {
        return ResponseEntity.ok(sampleService.editSample(sampleDto));
    }

    @PostMapping("/deleteSample")
    public ResponseEntity<Sample> deleteSample(@Validated(SampleDto.Delete.class) @RequestBody SampleDto sampleDto) {
        return ResponseEntity.ok(sampleService.deleteSample(sampleDto));
    }

}
