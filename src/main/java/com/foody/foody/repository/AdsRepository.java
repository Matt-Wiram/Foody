package com.foody.foody.repository;

import com.foody.foody.bean.Food;
import com.foody.foody.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Food, Long> {
    List<Food> getFoodByUser (User user);

    Food getFoodByTitle (String title);
}
