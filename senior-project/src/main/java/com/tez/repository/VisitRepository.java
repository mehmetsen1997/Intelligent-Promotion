package com.tez.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.tez.model.cassandra.Visit;

@Repository
public interface VisitRepository extends CassandraRepository<Visit, Integer> {
  List<Visit> findAll();
}
