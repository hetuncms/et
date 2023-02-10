package com.example.cms9cc.admin.repositories;


import com.example.cms9cc.admin.bean.TopAdBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopAdMapping extends JpaRepository<TopAdBean, Integer> {

}
