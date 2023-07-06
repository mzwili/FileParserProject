package com.eviro.assessment.grad001.ThuthkaniMthiyane.Repository;

import com.eviro.assessment.grad001.ThuthkaniMthiyane.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AccountProfileRepository extends JpaRepository<UserDetails, Integer> {
}
