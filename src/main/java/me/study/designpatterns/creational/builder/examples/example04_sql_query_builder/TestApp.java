package me.study.designpatterns.creational.builder.examples.example04_sql_query_builder;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        /**
         * The application selects the proper query builder type depending on a current
         * configuration or the environment settings.
         */
        // if (env['database_type'] == 'postgres') {
        // builder = new PostgresQueryBuilder();
        // }
        // else {
        // builder = new MysqlQueryBuilder();
        // }
        //
        // clientCode($builder);
        System.out.println("Testing MySQL query builder:");
        clientCode(new MysqlQueryBuilder());

        System.out.println("\n\nTesting PostgresSQL query builder:");
        clientCode(new PostgresQueryBuilder());
    }

    /**
     * Note that the client code uses the builder object directly. A designated
     * Director class is not necessary in this case, because the client code needs
     * different queries almost every time, so the sequence of the construction
     * steps cannot be easily reused.
     *
     * Since all our query builders create products of the same type (which is a
     * string), we can interact with all builders using their common interface.
     * Later, if we implement a new Builder class, we will be able to pass its
     * instance to the existing client code without breaking it thanks to the
     * SQLQueryBuilder interface.
     */
    static void clientCode(SQLQueryBuilder queryBuilder) {
        String query = queryBuilder
                .select("users", List.of("name", "email", "password"))
                .where("age", "18", ">")
                .where("age", "30", "<")
                .limit(10, 20)
                .getSQL();

        System.out.println(query);
    }
}

/**
 * The Builder interface declares a set of methods to assemble an SQL query.
 *
 * All of the construction steps are returning the current builder object to
 * allow chaining: builder.select(...).where(...)
 */
interface SQLQueryBuilder {
    SQLQueryBuilder select(String table, List<String> fields);

    SQLQueryBuilder where(String field, String value, String operator);

    SQLQueryBuilder limit(int start, int offset);

    String getSQL();
}

class Query {

    public String base;
    public String type;
    public List<String> where = new ArrayList<String>();
    public String limit;

}

/**
 * Each Concrete Builder corresponds to a specific SQL dialect and may implement
 * the builder steps a little bit differently from the others.
 *
 * This Concrete Builder can build SQL queries compatible with MySQL.
 */
class MysqlQueryBuilder implements SQLQueryBuilder {
    protected Query query;

    private void reset() {
        this.query = new Query();
    }

    /**
     * Build a base SELECT query.
     */
    public SQLQueryBuilder select(String table, List<String> fields) {
        this.reset();
        this.query.base = "SELECT " + String.join(", ", fields) + " FROM " + table;
        this.query.type = "select";
        return this;
    }

    /**
     * Add a WHERE condition.
     */
    public SQLQueryBuilder where(String field, String value, String operator) {
        if (!List.of("select", "update", "delete").contains(this.query.type)) {
            throw new RuntimeException("WHERE can only be added to SELECT, UPDATE OR DELETE");
        }
        this.query.where.add(field + " " + operator + " '" + value + "'");
        return this;
    }

    /**
     * Add a LIMIT constraint.
     */
    public SQLQueryBuilder limit(int start, int offset) {
        if (!this.query.type.equals("select")) {
            throw new RuntimeException("LIMIT can only be added to SELECT");
        }
        this.query.limit = " LIMIT " + start + ", " + offset;
        return this;
    }

    /**
     * Get the final query string.
     */
    public String getSQL() {
        Object query = this.query;
        String sql = this.query.base;
        if (this.query.where.size() > 0) {
            sql += " WHERE " + String.join(" AND ", this.query.where);
        }
        if (this.query.limit != null) {
            sql += this.query.limit;
        }
        return sql + ";";
    }
}

/**
 * This Concrete Builder is compatible with PostgreSQL. While Postgres is very
 * similar to Mysql, it still has several differences. To reuse the common code,
 * we extend it from the MySQL builder, while overriding some of the building
 * steps.
 */
class PostgresQueryBuilder extends MysqlQueryBuilder {
    /**
     * Among other things, PostgreSQL has slightly different LIMIT syntax.
     */
    public SQLQueryBuilder limit(int start, int offset) {
        super.limit(start, offset);
        this.query.limit = " LIMIT " + start + " OFFSET " + offset;
        return this;
    }
    // + tons of other overrides...
}