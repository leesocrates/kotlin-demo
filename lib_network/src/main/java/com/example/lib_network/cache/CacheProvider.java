package com.example.lib_network.cache;

/**
 * author : Lee
 * date : 2019/10/30
 * description :
 */
public interface CacheProvider<K, V> {
    /**
     * 添加缓存
     * @param key 缓存对应的key
     * @param cacheEntity 缓存的内容
     */
    void add(K key, V cacheEntity);

    /**
     * 删除缓存
     * @param key 要删除的缓存对应的key
     */
    void delete(K key);

    /**
     * 更新缓存
     * @param key 要更新缓存的key
     * @param cacheEntity 要更新缓存的内容
     */
    void update(K key, V cacheEntity);

    /**
     * 获取缓存
     * @param key
     * @return key对应的缓存内容，可能为空
     */
    V get(K key);
}
