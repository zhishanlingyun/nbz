package com.nbz.dao;

import com.alibaba.druid.filter.AutoLoad;
import com.nbz.dao.annotation.Entity;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 只封装数据访问的基本操作
 * 增删改查
 * mybatis支持
 * 编程约定:优先注解,其次简单类名
 */
public class BaseDao<T> implements IDao<T>{

    private SqlSession sqlSession;

    public void insert(T t) {
        if(t.getClass().isAnnotationPresent(Entity.class)){
            Entity entity = getEntity(t);
            //entity.
        }else {

        }
    }

    public void batchInsert(List<T> objs) {

    }

    public T queryByPk(Object key) {
        return null;
    }

    public T query(QueryCondition condition) {
        return null;
    }

    public List<T> queryList(QueryCondition condition) {
        return null;
    }

    public int queryCount(QueryCondition condition) {
        return 0;
    }

    public void update(UpdateCondition condition) {

    }

    public void batchUpdate(List<UpdateCondition> conditions) {

    }

    public void deleteByPk(Object key) {

    }

    public void batchDelete(List keys) {

    }

    public void dropByPk(Object key) {

    }

    public void batchDrop(List keys) {

    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private Entity getEntity(Object obj){
        return obj.getClass().getAnnotation(Entity.class);
    }
}
