package ru.netology.statistic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);
    TicketTimeComporator timeComporator = new TicketTimeComporator();
    Ticket ticket1 = new Ticket(1, 5000, "LED", "GOJ", 120);
    Ticket ticket2 = new Ticket(2, 5700, "LED", "OGZ", 360);
    Ticket ticket3 = new Ticket(3, 4700, "MOW", "LED", 90);
    Ticket ticket4 = new Ticket(4, 5800, "MOW", "LED", 60);
    Ticket ticket5 = new Ticket(5, 3000, "MOW", "LED", 120);
    Ticket ticket6 = new Ticket(6, 4000, "MOW", "LED", 85);
    Ticket ticket7 = new Ticket(7, 4000, "MOW", "LED", 85);
    Ticket ticket8 = new Ticket(8, 3500, "MOW", "LED", 115);

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
    }

    @Test
    public void shouldFindSomeResult() {
        Ticket[] expected = {ticket5, ticket3, ticket4};
        Ticket[] actual = manager.findAllFromToCheaper("MOW", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindLowerCase() {
        Ticket[] expected = {ticket5, ticket3, ticket4};
        Ticket[] actual = manager.findAllFromToCheaper("mow", "led");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneResult() {
        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.findAllFromToCheaper("led", "OGZ");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNoResult() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAllFromToCheaper("MOW", "OGZ");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldEquals() {
        manager.remove();
        manager.add(ticket6);
        manager.add(ticket7);
        Ticket[] expected = {ticket6, ticket7};
        Ticket[] actual = manager.findAllFromToCheaper("mow", "led");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSomeEquals() {
        manager.remove();
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        Ticket[] expected = {ticket8, ticket6, ticket7};
        Ticket[] actual = manager.findAllFromToCheaper("mow", "led");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindFaster() {
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        Ticket[] expected = {ticket4, ticket6, ticket7, ticket3, ticket8, ticket5};
        Ticket[] actual = manager.findAllFromToFaster("MOW", "led", timeComporator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindFaster() {
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        Ticket[] expected = {};
        Ticket[] actual = manager.findAllFromToFaster("MOW", "GOJ", timeComporator);
        Assertions.assertArrayEquals(expected, actual);
    }



}
