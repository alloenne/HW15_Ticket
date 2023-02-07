package ru.netology.statistic;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repo;
    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }
    TicketTimeComporator timeComporator = new TicketTimeComporator();

    public void add(Ticket ticket) {
        repo.save(ticket);
    }

    public void remove() {
        repo.removeAll();
    }

    public Ticket[] findAllFromToCheaper(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (ticket.getDepartureAirport().toLowerCase().equals(from.toLowerCase()) && ticket.getArrivalAirport().toLowerCase().equals(to.toLowerCase())) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public Ticket[] findAllFromToFaster(String from, String to, Comparator<Ticket> timeComporator) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            if (ticket.getDepartureAirport().toLowerCase().equals(from.toLowerCase()) && ticket.getArrivalAirport().toLowerCase().equals(to.toLowerCase())) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, timeComporator);
        return result;
    }
}
