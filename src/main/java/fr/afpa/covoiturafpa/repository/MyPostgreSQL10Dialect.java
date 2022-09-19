package fr.afpa.covoiturafpa.repository;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.DoubleType;

public class MyPostgreSQL10Dialect extends PostgreSQL10Dialect {
    public MyPostgreSQL10Dialect() {
        super();
        registerFunction("get_distance", new StandardSQLFunction("covoiturafpa.get_distance"));
    }
}
