package gov.rw.brd.repository;

import gov.rw.brd.domain.Loanee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hp on 4/17/2021.
 */
@Repository
public interface LoaneeRepository extends JpaRepository<Loanee,String>{

    @Query(value = "select (select count(*) from loanee where gender = 'Divorced') as one,\n" +
            "  (select count(*) as summation from loanee WHERE gender = 'Single') as two,\n" +
            "  (select count(*) as summation from loanee WHERE gender = 'Married') as three",nativeQuery = true)
    public String getGroupByName();
}
