package com.haze.user_svc.repository;

import com.haze.user_svc.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u.id from User u where u.username = :username")
    Optional<UUID> findIdByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "insert into user_connections (follower_id, followed_id)" +
            "values (:followerId, :followedId)" +
            "on conflict do nothing", nativeQuery = true)
    void followRequest(@Param("followerId")UUID followerId, @Param("followedId") UUID followedId);

    Optional<User> findByUsername(String username);
}
