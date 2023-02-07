package ru.netology.statistic;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    public void save(Ticket ticket) {
        Ticket[] tmp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        tmp[tmp.length - 1] = ticket;
        tickets = tmp;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void removeById(int id) {
        int idCount = 0;
        for (Ticket product : tickets) {
            if (product.getId() == id) {
                idCount++;
            }
        }
        Ticket e = findById(id);
        if (e == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
        Ticket[] tmp = new Ticket[tickets.length - idCount];
        int copyToIndex = 0;
        for (Ticket poster : tickets) {
            if (poster.getId() != id) {
                tmp[copyToIndex] = poster;
                copyToIndex++;
            }
        }
        tickets = tmp;
    }

    public void removeAll() {
        Ticket[] tmp = new Ticket[0];
        tickets = tmp;
    }
}
