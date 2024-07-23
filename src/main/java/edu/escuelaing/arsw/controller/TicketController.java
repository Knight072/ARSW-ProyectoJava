package edu.escuelaing.arsw.controller;

import edu.escuelaing.arsw.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The TicketController class is a REST controller that handles HTTP requests
 * related to tickets. It provides an endpoint for retrieving a ticket.
 *
 * <p>It is marked with the @RestController annotation to indicate that it is a
 * Spring REST controller.
 */
@RestController
public class TicketController {

    /**
     * The repository used to manage tickets.
     */
    @Autowired
    private TicketRepository ticketRepo;

    /**
     * Retrieves a ticket.
     *
     * @return a JSON string containing the ticket
     */
    @GetMapping("/getticket")
    public String getTicket() {
        return "{\"ticket\":\"" + ticketRepo.getTicket() + "\"}";
    }
}

