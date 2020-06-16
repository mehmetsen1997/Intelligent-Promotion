package com.tez.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tez.model.postgresql.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
	
}
