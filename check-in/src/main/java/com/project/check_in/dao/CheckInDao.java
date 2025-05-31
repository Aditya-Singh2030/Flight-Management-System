package com.project.check_in.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.check_in.entity.CheckIn;

@Repository
public interface CheckInDao extends JpaRepository<CheckIn, Long>{

}
