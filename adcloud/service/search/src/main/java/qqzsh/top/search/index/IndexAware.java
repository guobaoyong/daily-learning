package qqzsh.top.search.index;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 14:52
 * @Description 索引增删改查
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
