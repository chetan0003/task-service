package com.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Holiday implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String date;
    private Integer userId;
}
