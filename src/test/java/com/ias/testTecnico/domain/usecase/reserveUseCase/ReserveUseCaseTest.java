package com.ias.testTecnico.domain.usecase.reserveUseCase;

import com.ias.testTecnico.domain.model.editreserve.EditReserve;
import com.ias.testTecnico.domain.model.editreserve.EditReserveResponse;
import com.ias.testTecnico.domain.model.editreserve.ReserveEntity;
import com.ias.testTecnico.domain.model.repository.ReserveRepository;
import com.ias.testTecnico.infrastructure.drivenadapters.ticketAdapters.mapper.TicketData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ReserveUseCaseTest {

    @Mock
    private ReserveUseCase reserveUseCase;

    @Mock
    private ReserveRepository reserveRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reserveUseCase = new ReserveUseCase(reserveRepository);
    }

    @Test
    void search(){
        //AAS
        //Arrange
        TicketData data = new TicketData();
        EditReserve editReserve = EditReserve.builder().date("15/03/2025").id_travel("123").build();
        ReserveEntity entity = new ReserveEntity(new EditReserveResponse(), "SUCCESS",
                "Reserve created successfully");

        //act
        when(reserveRepository.EditReserve(data)).thenReturn(entity.getData());

        //Assert
        assertEquals(entity.getData(), reserveRepository.EditReserve(data));
    }
}