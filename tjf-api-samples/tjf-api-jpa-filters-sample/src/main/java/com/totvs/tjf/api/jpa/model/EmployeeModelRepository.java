package com.totvs.tjf.api.jpa.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.totvs.tjf.api.jpa.repository.ApiJpaRepository;

@Repository
@Transactional
public interface EmployeeModelRepository extends JpaRepository<EmployeeModel, Integer>, ApiJpaRepository <EmployeeModel> {}
