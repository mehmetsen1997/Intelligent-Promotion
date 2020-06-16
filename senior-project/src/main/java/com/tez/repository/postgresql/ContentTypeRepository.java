package com.tez.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tez.model.postgresql.ContentType;

@Repository
public interface ContentTypeRepository extends JpaRepository<ContentType, Integer> {

}
