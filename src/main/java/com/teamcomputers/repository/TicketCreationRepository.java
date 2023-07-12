package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.model.TicketCreation;
import com.teamcomputers.model.TicketStatusEnum;
import com.teamcomputers.model.UserDao;

import org.springframework.data.domain.Pageable;

@Repository
public interface TicketCreationRepository extends JpaRepository<TicketCreation, Long> {

	TicketCreation findByTicketId(long Id);
	
	 List<TicketCreation> findAllByOrderByCreatedDateDesc();
	List<TicketCreation> findByCreatedBy(UserDao createdBy);

	List<TicketCreation> findByAssignedTo(UserDao assignedTo);

	List<TicketCreation> findByDepartment(DepartmentEntity department);

	List<TicketCreation> findAllByOrderByCreatedDateDesc(Pageable pageable);

	List<TicketCreation> findByCreatedByOrderByCreatedDateDesc(UserDao userDao, Pageable pageable);

	List<TicketCreation> findByAssignedToOrderByCreatedDateDesc(UserDao AssignedTo, Pageable pageable);

	List<TicketCreation> findByCreatedByOrderByCreatedDateDesc(UserDao createdBy);

	List<TicketCreation> findByDepartmentAndTicketStatus(DepartmentEntity department, TicketStatusEnum ticketStatus);

	List<TicketCreation> findByCreatedByAndDepartmentAndTicketStatus(UserDao createdBy, DepartmentEntity department,
			TicketStatusEnum ticketStatus);

	List<TicketCreation> findByCreatedByAndDepartment(UserDao createdBy, DepartmentEntity department);

	List<TicketCreation> findByCreatedByAndTicketStatus(UserDao createdBy, TicketStatusEnum ticketStatus);

	List<TicketCreation> findByAssignedToAndTicketStatus(UserDao assignedTo, TicketStatusEnum ticketStatus);

	List<TicketCreation> findByTicketStatus(TicketStatusEnum ticketStatus);

	@Query("SELECT t.assignedTo.userId,t.assignedTo.username , t.assignedTo.firstName ,t.assignedTo.lastName , COUNT(t.ticketStatus) FROM TicketCreation t GROUP BY  t.assignedTo")
	List<Object[]> getTicketStatusCountByAssignedTo();

	@Query("SELECT t.assignedTo.userId,t.assignedTo.username , t.assignedTo.firstName ,t.assignedTo.lastName , COUNT(t.ticketStatus) "
			+ "FROM TicketCreation t " + "WHERE t.sla = 'over' " + "GROUP BY t.assignedTo")
	List<Object[]> countByAssignedTo();

	@Query("SELECT t.department.departmentId,t.department.departmentName, COUNT(t.ticketStatus) FROM TicketCreation t WHERE t.sla = 'over' AND t.createdBy.userId = :createdBy AND t.department.departmentId = :department GROUP BY t.department")
	List<Object[]> getDepartmentTicketStatusCount(int createdBy,int department);

	// Resolver
	@Query("SELECT t.assignedTo.userId,t.assignedTo.username , t.assignedTo.firstName ,t.assignedTo.lastName , COUNT(t.ticketStatus) "
			+ "FROM TicketCreation t " + "WHERE t.department.departmentId = :department " + "GROUP BY t.assignedTo")
	List<Object[]> getAssignedToCount(int department);
	//resolver
	@Query("SELECT t.department.departmentId,t.department.departmentName, COUNT(t.ticketStatus) FROM TicketCreation t WHERE t.assignedTo.userId = :assignedTo AND t.department.departmentId = :department GROUP BY t.department")
    List<Object[]> getTicketStatusByDepartmentId(int assignedTo,int department);
    
    int countByAssignedToAndSla(UserDao assignToId, String sla);
    
    int countByDepartmentAndSla(DepartmentEntity department, String sla);

}
