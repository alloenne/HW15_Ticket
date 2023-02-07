package ru.netology.statistic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketRepoTest {
    Ticket ticket1 = new Ticket(1, 5000, "LED", "GOJ", 120);
    Ticket ticket2 = new Ticket(2, 5700, "LED", "OGZ", 360);
    Ticket ticket3 = new Ticket(3, 4700, "MOW", "LED", 90);
    Ticket ticket4 = new Ticket(4, 5800, "MOW", "LED", 60);
    Ticket ticket5 = new Ticket(5, 3000, "MOW", "LED", 120);

    @Test
    public void shouldSave() {
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        Ticket[] exp = {ticket1, ticket2};
        Ticket[] act = repo.findAll();
        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldExistEmpty() {
        TicketRepository repo = new TicketRepository();
        Ticket[] expected = {};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldOneTicket() {
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        Ticket[] expected = {ticket1};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldRemoveByIdExist() {
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
        repo.removeById(4);
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket5};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdNoExist() {
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(10);
        });
    }

    @Test
    public void shouldFindById() {
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        Ticket expected = ticket1;
        Ticket actual = repo.findById(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindById() {
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        Ticket expected = null;
        Ticket actual = repo.findById(7);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAll() {
        TicketRepository repo = new TicketRepository();
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.removeAll();
        Ticket[] expected = {};
        Ticket[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }



}
