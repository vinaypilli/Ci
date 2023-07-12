package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.teamcomputers.model.SLATimelines;

@Repository
public interface SLATimelinesRepository extends JpaRepository<SLATimelines, Long> {

	List<SLATimelines> findByStatusOrderByPriorityNameAsc(boolean b);
}
