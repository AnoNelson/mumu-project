package gov.rw.brd.repository;

import gov.rw.brd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nick on 2/5/2020.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByUsername(String name);

}

