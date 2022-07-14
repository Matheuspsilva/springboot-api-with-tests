package com.matheussilvadev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheussilvadev.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
