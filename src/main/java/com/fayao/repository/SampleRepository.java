package com.fayao.repository;

import com.fayao.model.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample, Long> {

    public List<Sample> findByNameAndMobilePhone(String name, String mobilePhone);

}
