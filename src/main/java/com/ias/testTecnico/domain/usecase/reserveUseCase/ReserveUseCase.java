package com.ias.testTecnico.domain.usecase.reserveUseCase;

import com.ias.testTecnico.domain.model.editreserve.EditReserve;
import com.ias.testTecnico.domain.model.editreserve.ReserveEntity;
import com.ias.testTecnico.domain.model.exceptions.BusinessExceptions;
import com.ias.testTecnico.domain.model.repository.ReserveRepository;
import com.ias.testTecnico.infrastructure.drivenadapters.ticketAdapters.mapper.TicketData;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReserveUseCase {

    private final ReserveRepository reserveRepository;

    public ReserveUseCase(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    public ReserveEntity editReserve(EditReserve request) {
        log.info("Editando el siguiente numero de reserva: {}", request);

        TicketData data = reserveRepository.searchReserve(request.getId_travel());

        if (data == null){
            throw new BusinessExceptions("Reserve not found");
        }

        data.setDate(request.getDate());

        return new ReserveEntity(reserveRepository.EditReserve(data), "SUCCESS",
                "Reserve edited successfully");
    }
}
