package vava.soltesvasko.lezeckastena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vava.soltesvasko.lezeckastena.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> { }