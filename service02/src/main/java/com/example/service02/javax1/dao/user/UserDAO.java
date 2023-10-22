package com.example.service02.javax1.dao.user;

import com.example.service02.javax1.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.Username LIKE ?1")
    Page<User> findByKeywords(String keywords, Pageable pageable);

    Page<User> findAllByNameLike(String keywords, Pageable pageable);

    @Query("SELECT count(u) FROM User u")
    Integer getCount();

}
