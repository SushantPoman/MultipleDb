package com.multipledb.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multipledb.mysql.entities.Users;

public interface UserRepo extends JpaRepository<Users, Integer>{

}
