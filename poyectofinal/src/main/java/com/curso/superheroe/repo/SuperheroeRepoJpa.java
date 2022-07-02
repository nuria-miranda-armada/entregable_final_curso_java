package com.curso.superheroe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.superheroe.entity.Superheroe;

@Repository
public interface SuperheroeRepoJpa extends JpaRepository<Superheroe, Integer> {

}
