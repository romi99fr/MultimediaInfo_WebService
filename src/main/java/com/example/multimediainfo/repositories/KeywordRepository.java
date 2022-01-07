package com.example.multimediainfo.repositories;

import com.example.multimediainfo.models.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    @Query("select k from Keyword k where k.hash = ?1")
    List<Keyword> findByHashEquals(String hash);

    @Query("select k from Keyword k where k.keyword = ?1")
    List<Keyword> findByContent(String keyword);

    @Modifying
    @Query("delete from Keyword k where k.hash = ?1")
    void deleteByHashEquals(String hash);

    @Modifying
    @Query("delete from Keyword k where k.keyword = ?1")
    void deleteByContent(String keyword);
}
