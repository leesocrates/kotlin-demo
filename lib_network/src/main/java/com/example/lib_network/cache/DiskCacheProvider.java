package com.example.lib_network.cache;

/**
 * author : Lee
 * date : 2019/10/30
 * description :
 */
public class DiskCacheProvider implements CacheProvider<String, CacheEntity> {
    @Override
    public void add(String key, CacheEntity cacheEntity) {
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void update(String key, CacheEntity cacheEntity) {

    }

    @Override
    public CacheEntity get(String key) {
        return null;
    }
}
