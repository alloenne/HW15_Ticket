package ru.netology.statistic;

import java.util.Comparator;

public class TicketTimeComporator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getTravelMinute() - o2.getTravelMinute();
    }
}
