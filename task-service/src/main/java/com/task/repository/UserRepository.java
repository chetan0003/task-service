package com.task.repository;

import com.task.entities.HolidayDetail;
import com.task.entities.UserDetail;
import com.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetail,Integer> {

    UserDetail findByUserName(String userName);

}
