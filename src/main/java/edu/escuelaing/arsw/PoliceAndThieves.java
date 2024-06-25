package edu.escuelaing.arsw;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class PoliceAndThieves {
    private Integer[][] tableInt;
    private Table table;

    public PoliceAndThieves() {
        this.table = Table.getInstance();
        this.table.createThief();
    }

    public static void main(String[] args) {
        SpringApplication.run(PoliceAndThieves.class, args);
    }
}
