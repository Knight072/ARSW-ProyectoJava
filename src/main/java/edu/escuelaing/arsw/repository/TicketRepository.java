package edu.escuelaing.arsw.repository;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * The TicketRepository class is a repository component that manages tickets using
 * Redis. It provides methods to generate and validate tickets.
 *
 * <p>It is marked with the @Component annotation to indicate that it is a Spring
 * component.
 */
@Component
public class TicketRepository {

    /**
     * The Redis template used to perform operations on Redis.
     */
    @Autowired
    private StringRedisTemplate template;

    /**
     * The ListOperations used to perform list operations on Redis.
     */
    @Resource(name = "stringRedisTemplate")
    private ListOperations<String, String> listTickets;

    /**
     * The current ticket number.
     */
    private int ticketnumber;

    /**
     * Constructs a TicketRepository.
     */
    public TicketRepository() {
    }

    /**
     * Generates a new ticket, stores it in Redis, and returns the ticket number.
     * This method is synchronized to ensure thread safety.
     *
     * @return the generated ticket number
     */
    public synchronized Integer getTicket() {
        Integer ticket = ticketnumber++;
        listTickets.leftPush("ticketStore", ticket.toString());
        System.out.println(ticket);
        return ticket;
    }

    /**
     * Validates a ticket by checking if it exists in Redis and removing it if it does.
     *
     * @param ticket the ticket to validate
     * @return true if the ticket is valid and was removed, false otherwise
     */
    public boolean checkTicket(String ticket) {
        Long isValid = listTickets.getOperations().boundListOps("ticketStore").remove(0, ticket);
        return (isValid > 0);
    }

    /**
     * Placeholder method for ticket eviction. This method is intended to delete tickets
     * after a timeout or include this functionality in checkTicket.
     */
    private void eviction() {
        // Delete tickets after timeout or include this functionality in checkTicket
    }
}

