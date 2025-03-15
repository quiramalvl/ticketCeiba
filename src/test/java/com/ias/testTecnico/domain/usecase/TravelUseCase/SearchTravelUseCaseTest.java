package com.ias.testTecnico.domain.usecase.TravelUseCase;

import com.ias.testTecnico.domain.model.searchTravel.TravelResponse;
import com.ias.testTecnico.domain.model.repository.TravelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SearchTravelUseCaseTest {

    @Mock
    private SearchTravelUseCase searchTravelUseCase;

    @Mock
    private TravelRepository travelRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        searchTravelUseCase = new SearchTravelUseCase(travelRepository);
    }

    @Test
    void search(){
        //AAS
        //Arrange
        String id = "1";
        TravelResponse response = new TravelResponse("1", "2021-10-10", "Bogota", "Medellin");
        //Act
        when(travelRepository.searchTravel(id)).thenReturn(response);

        //Assert
        assertEquals(response, searchTravelUseCase.search(id).getData());
    }

}