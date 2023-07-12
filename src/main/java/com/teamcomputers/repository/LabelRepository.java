package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.Label;

@Repository
public interface  LabelRepository extends JpaRepository<Label, Long>{

	List<Label> findByStatus(boolean b);
	Label findByLabelName(String labelName);
	Label findByLabelNameAndLabelIdNot(String labelName, Long labelId);
}
