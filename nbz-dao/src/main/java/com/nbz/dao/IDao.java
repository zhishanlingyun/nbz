package com.nbz.dao;

import java.util.List;

/**
 * dao层的基本操作
 */
public interface IDao<T> {

    public void insert(T t);

    public void batchInsert(List<T> objs);

    public T queryByPk(Object key);

    public T query(QueryCondition condition);

    /**
     * 查询全部/分页数据
     * @param condition
     * @return
     */
    public List<T> queryList(QueryCondition condition);

    public int queryCount(QueryCondition condition);

    public void update(UpdateCondition condition);

    public void batchUpdate(List<UpdateCondition> conditions);

    /**
     * 逻辑删除
     * @param key
     */
    public void deleteByPk(Object key);

    /**
     * 批量逻辑删除
     */
    public void batchDelete(List keys);

    /**
     * 物理删除
     * @param key
     */
    public void dropByPk(Object key);

    /**
     * 批量物理删除
     * @param keys
     */
    public void batchDrop(List keys);

}
