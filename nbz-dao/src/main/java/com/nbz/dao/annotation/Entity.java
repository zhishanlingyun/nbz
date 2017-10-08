package com.nbz.dao.annotation;

import java.lang.annotation.*;

/**
 * 数据库-java对象实体类
 * 只支持类的平级使用,父类的注解不会被使用
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Entity {

    /**
     * 主键字段名称
     * @return
     */
    public String pk() default "id";

    /**
     * 数据字段列
     * @return
     */
    public String[] column();

    /**
     * 数据库表名称
     * @return
     */
    public String tablename();

    /**
     *
     * @return
     */
    public String splitfield() default "";


}
