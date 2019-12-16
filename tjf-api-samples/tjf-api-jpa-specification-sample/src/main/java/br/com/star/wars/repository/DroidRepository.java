package br.com.star.wars.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.totvs.tjf.api.jpa.repository.ApiJpaRepository;

import br.com.star.wars.model.Droid;

@Repository
@Transactional
public interface DroidRepository extends JpaRepository<Droid, Integer>, ApiJpaRepository<Droid> {

}