package com.fayao.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class SampleDto {

    @NotNull(message = "id不得為空",groups = {Edit.class,Delete.class})
    private Long id;

    @NotBlank(message = "name不得為空",groups = {Save.class,Edit.class,GetByCondition.class})
    private String name;

    @NotBlank(message = "mobilePhone不得為空",groups = {Save.class,Edit.class,GetByCondition.class})
    private String mobilePhone;

    public interface Save{}
    public interface Edit{}
    public interface Delete{}
    public interface GetByCondition{}
}
