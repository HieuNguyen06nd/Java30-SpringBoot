package org.example.lesson1.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class TicketBook {
    // id, bookId, personId, borrowDate, returnDate, note
    private int id;
    private int bookId;
    private int personId;
    private String note;
    private Date borrowDate;
    private Date returnDate;
}
