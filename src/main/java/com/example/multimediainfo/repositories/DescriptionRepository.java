package com.example.multimediainfo.repositories;

import com.example.multimediainfo.models.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
    @Query("select d from Description d where d.hash = ?1")
    List<Description> findByHashEquals(String hash);

    @Query("select d from Description d where d.description = ?1")
    List<Description> findByContent(String description);

    @Modifying
    @Query("delete from Description d where d.hash = ?1")
    void deleteByHashEquals(String hash);

    @Modifying
    @Query("delete from Description d where d.description = ?1")
    void deleteByContent(String description);
}
