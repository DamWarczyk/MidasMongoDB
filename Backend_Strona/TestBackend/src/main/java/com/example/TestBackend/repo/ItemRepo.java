package com.example.TestBackend.repo;

import com.example.TestBackend.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepo extends MongoRepository<Item, String> {
    void deleteItemById(String id);

    Optional<Item> findItemById (String id);
}
