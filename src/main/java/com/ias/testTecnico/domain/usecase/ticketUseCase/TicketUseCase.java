package com.ias.testTecnico.domain.usecase.ticketUseCase;

import com.ias.testTecnico.domain.model.exceptions.BusinessExceptions;
import com.ias.testTecnico.domain.model.repository.TicketRepository;
import com.ias.testTecnico.domain.model.tickets.TicketEntity;
import com.ias.testTecnico.domain.model.tickets.TicketsRequest;
import com.ias.testTecnico.domain.model.tickets.util.Document;
import com.ias.testTecnico.domain.model.tickets.util.Name;
import com.ias.testTecnico.domain.model.tickets.util.Ticket;
import com.ias.testTecnico.domain.model.tickets.util.Travel;
import com.ias.testTecnico.infrastructure.drivenadapters.ticketAdapters.mapper.TicketData;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

@Log4j2
public class TicketUseCase {

    private final TicketRepository ticketRepository;

    public TicketUseCase(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketEntity createTicket(TicketsRequest request){

        log.info("Creando el siguiente ticket: {}", request);
        if (request == null){
            throw new BusinessExceptions("Request is required");
        }

        Ticket ticket = new Ticket().builder()
                .name(new Name(request.getName()))
                .cc(new Document(request.getCc()))
                .id_travel(new Travel(request.getId_travel()))
                .build();

        TicketData data = ticketRepository.searchTicket(ticket.getId_travel().getTravel());

        if (data == null){
            throw new BusinessExceptions("Travel not found");
        }

        data.setDocument(ticket.getCc().getDocument());
        data.setName(ticket.getName().getName());
        data.setId_ticket(UUID.randomUUID().toString());
        data.setId_reserve(UUID.randomUUID().toString());


        return new TicketEntity(ticketRepository.createTicket(data), "SUCCESS",
                "Reserve created successfully");
    }

}
