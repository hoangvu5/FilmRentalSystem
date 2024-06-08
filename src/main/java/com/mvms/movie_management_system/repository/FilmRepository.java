package com.mvms.movie_management_system.repository;

import com.mvms.movie_management_system.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    // Select films with category X, actor Y, and has 1 or more copies
    @Query("select f from Film f " +
            "join f.filmCategories fc join fc.category c " +
            "join f.filmActors fa join fa.actor a " +
            "join f.inventories i " +
            "where c.name = :categoryName " +
            "and a.firstName = :actorName " +
            "group by f.id " +
            "having count(i.id) >= 1")
    List<Film> findFilmsByCategoryActorAndInventoryCount(
            @Param("categoryName") String categoryName,
            @Param("actorName") String actorName
    );
}
