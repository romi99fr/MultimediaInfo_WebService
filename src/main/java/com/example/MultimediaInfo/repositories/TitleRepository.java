package com.example.MultimediaInfo.repositories;

import com.example.MultimediaInfo.models.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    @Query("select t from Title t where t.hash = ?1")
    List<Title> findByHashEquals(String hash);

    @Query("select t from Title t where t.title = ?1")
    List<Title> findByContent(String title);

    @Transactional
    @Modifying
    @Query("delete from Title t where t.hash = ?1")
    void deleteByHashEquals(String hash);

    @Transactional
    @Modifying
    @Query("delete from Title t where t.title = ?1")
    void deleteByContent(String title);
}
