package com.teamcomputers.webhookMetaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamcomputers.webhookMetaModel.IncomingModel;

@Repository
public interface IncomingDataRepository extends JpaRepository<IncomingModel, Long>{

	
}
