package com.project.dbwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.dbwt.model.*;

@Repository
public interface NewsAggregatorRepository extends JpaRepository<NewsAggregatorModel, Long> {

}