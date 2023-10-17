package com.example.service02.javax1.dao.user;

import com.example.service02.javax1.model.report.Report1;
import com.example.service02.javax1.model.user.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteDAO extends JpaRepository<Favorite, Long> {

    @Query (value = "SELECT f FROM Favorite f WHERE f.userID = ?1")
    List<Favorite> getAllFav(String username);

    @Query (value = "SELECT f FROM Favorite f WHERE f.userID = ?1 and f.productID= ?2")
    Favorite getAllFav2(String username,Long id);

    @Query (value = "SELECT count(f) FROM Favorite f WHERE f.userID = ?1")
    Integer getCountFav(String username);

    @Query (value = "SELECT count(f) FROM Favorite f WHERE f.productID = ?1")
    Integer getCountFavLike(Long id);

    @Query (value="SELECT new Report(f.productID, p.productName ,count(f) as countReport FROM Favorite f, Product p "+"WHERE p.productName LIKE ?1"
            + " GROUP BY p.productID, p.productName")
    Page<Report1> getListReport(String keyword, Pageable pageable);
}
