package gov.rw.brd.repository;

import gov.rw.brd.domain.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by hp on 4/17/2021.
 */
@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest,String>{
    @Query(value = "select (select count(*) from loan_request where status = 'A') as one,\n" +
            "(select count(*) from loan_request where status = 'D') as two",nativeQuery = true)
    public String getDashboardData();
}
