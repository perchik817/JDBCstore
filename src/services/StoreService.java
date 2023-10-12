package services;

import models.Store;

import java.util.List;

public interface StoreService {
    String createStore(String nameStore);
    Store findStoreById(Long id);
    List<Store> showAllStores();
    Long deleteStore(Long id);
    Store updateStore(Long id,String name);


}