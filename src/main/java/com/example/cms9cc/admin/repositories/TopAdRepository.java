package com.example.cms9cc.admin.repositories;


import com.example.cms9cc.admin.bean.TopAdBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TopAdRepository extends JpaRepository<TopAdBean, Integer> {
    <S extends TopAdBean> List<S> findAllByStatusTimeAfterOrderBySort(Date status);
}
