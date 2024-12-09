package org.truonglq.cqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truonglq.cqrs.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
