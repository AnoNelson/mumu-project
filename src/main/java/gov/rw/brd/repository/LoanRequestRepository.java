package gov.rw.brd.repository;

import gov.rw.brd.domain.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hp on 4/17/2021.
 */
@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest,String>{
    @Query(value = "select (select count(*) from loan_request where status = 'A') as one," +
            "(select count(*) from loan_request where status = 'D') as two," +
            "  (select count(*) from loan_request where status is NULL ) as three",nativeQuery = true)
    public String getDashboardData();


    @Query(value = "select * from loan_request ORDER BY request_date desc",nativeQuery = true)
    public List<LoanRequest> getOrderedData();

}
