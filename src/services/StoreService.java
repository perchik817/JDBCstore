package services;

import models.Store;

import java.util.List;

public interface StoreService {
    String saveStore(String nameStore);
    Store findById(Long id);
    List<Store> findAll();

    Store update(Long id,String name);

}