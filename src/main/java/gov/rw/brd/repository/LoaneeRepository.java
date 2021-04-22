package gov.rw.brd.repository;

import gov.rw.brd.domain.Loanee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hp on 4/17/2021.
 */
@Repository
public interface LoaneeRepository extends JpaRepository<Loanee,String>{
}
