package jopa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public class ProductUtils {

    @Autowired
    JdbcTemplate jdbcTemplate;



    public void createTable(){
        jdbcTemplate.execute("CREATE TABLE Product(name VARCHAR(100), price DOUBLE )");
    }

    public void addNew(String name, Double price){
        jdbcTemplate.update("INSERT INTO Product VALUES(?,?)", name,price);
    }

    @Transactional
    public void addTwo(boolean fail){
        System.out.println(this.getClass());
        System.out.println("LOOK AT MY PERFORMANCE . . ");
        jdbcTemplate.update("INSERT INTO Product VALUES('Yeah', 333)");
        if(fail)badGrammar();
        jdbcTemplate.update("INSERT INTO Product VALUES('Semyon', 2323)");
        System.out.println("TRANSACTION COMPLETE!");
    }

    public void badGrammar(){
        jdbcTemplate.update("INSERT INTO ProductX");
    }

    public void print(){
        for(Map<String,Object> m : jdbcTemplate.queryForList("SELECT * FROM Product")) System.out.println(m.get("name") + " " + m.get("price"));
    }
}
