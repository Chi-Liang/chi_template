package com.fundodo.fayaospace.service;

import com.fundodo.fayaospace.exception.NotFoundException;
import com.fundodo.fayaospace.model.dto.SampleDto;
import com.fundodo.fayaospace.model.entity.Sample;
import com.fundodo.fayaospace.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public List<Sample> getAllSample() {
        List<Sample> sampleList = sampleRepository.findAll();
        return sampleList;
    }

    public List<Sample> getSampleByNameAndMobilePhone(SampleDto sampleDto) {

        List<Sample> sampleList = sampleRepository.findByNameAndMobilePhone(sampleDto.getName(), sampleDto.getMobilePhone());

        return sampleList;
    }


    @Transactional(rollbackFor = Exception.class)
    public Sample saveSample(SampleDto sampleDto) {
//        Sample sample = Sample.builder().name(sampleReq.getName()).mobilePhone(sampleReq.getMobilePhone()).build();
        Sample sample = new Sample();
        BeanUtils.copyProperties(sampleDto, sample, "id");
        return sampleRepository.save(sample);

    }

    @Transactional(rollbackFor = Exception.class)
    public Sample editSample(SampleDto sampleDto) {
        Sample sample = getSample(sampleDto.getId());
        BeanUtils.copyProperties(sampleDto, sample);
//        sample = Sample.builder().id(sampleDto.getId()).name(sampleDto.getName()).mobilePhone(sampleDto.getMobilePhone()).build();
        return sampleRepository.save(sample);
    }

    @Transactional(rollbackFor = Exception.class)
    public Sample deleteSample(SampleDto sampleDto) {
        Sample sample = getSample(sampleDto.getId());
        sampleRepository.delete(sample);
        return sample;
    }

    private Sample getSample(Long id) {
        return sampleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("sample not found"));
    }

}
