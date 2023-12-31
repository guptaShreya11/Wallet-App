package com.nexdew.wallet.repository;

import javax.transaction.Transactional;

import com.nexdew.wallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface   UserRepository extends BaseRepository<User, Integer> {

  boolean existsByUsername(String username);
  boolean existsByEmail(String email);

  User findByUsername(String username);

  void deleteByUsername(String username);

}
