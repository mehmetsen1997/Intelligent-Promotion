package com.tez.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tez.model.postgresql.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

}
