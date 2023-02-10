package com.example.cms9cc.admin.repositories;


import com.example.cms9cc.admin.bean.StatisticsBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepo extends JpaRepository<StatisticsBean,Integer> {

}
