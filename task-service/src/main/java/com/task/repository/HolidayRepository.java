package com.task.repository;

import com.task.entities.HolidayDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayDetail, Integer> {

    @Query("FROM HolidayDetail h WHERE h.userDetail.id =:userId")
    List<HolidayDetail> findAllByUserId(@Param("userId") Integer userId);
}
