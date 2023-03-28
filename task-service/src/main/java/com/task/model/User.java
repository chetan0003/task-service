package com.task.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String token;


}
