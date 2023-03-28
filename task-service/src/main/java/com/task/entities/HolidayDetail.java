package com.task.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "holiday")
public class HolidayDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter@Setter
    private Integer id;

    @Column(name = "name")
    @Getter@Setter
    private String name;

    @Column(name = "date")
    @Getter@Setter
    private String date;

    @ManyToOne
    @Getter@Setter
    private UserDetail userDetail;


}

