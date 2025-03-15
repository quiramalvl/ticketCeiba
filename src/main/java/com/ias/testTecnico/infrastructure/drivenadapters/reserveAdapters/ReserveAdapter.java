package com.ias.testTecnico.infrastructure.drivenadapters.reserveAdapters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ias.testTecnico.domain.model.editreserve.EditReserveResponse;
import com.ias.testTecnico.domain.model.repository.ReserveRepository;
import com.ias.testTecnico.infrastructure.drivenadapters.ticketAdapters.mapper.TicketData;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Configuration
public class ReserveAdapter implements ReserveRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final DynamoDBMapperConfig dynamoDBMapperConfig;

    public ReserveAdapter(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.dynamoDBMapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride("testCeiba")).build();
    }


    @Override
    public TicketData searchReserve(String id) {
        log.info("Entrando al adaptador de reserva para el buscar");
        Map<String, AttributeValue> map = new HashMap<>();
        map.put(":id", new AttributeValue(id));

        DynamoDBQueryExpression<TicketData> queryExpression = new DynamoDBQueryExpression<TicketData>()
                .withHashKeyValues(TicketData.builder().pk("TRAVEL#123").build())
                .withFilterExpression("id = :id")
                .withExpressionAttributeValues(map);

        return dynamoDBMapper.query(TicketData.class, queryExpression).get(0);
    }

    @Override
    public EditReserveResponse EditReserve(TicketData ticket) {

        log.info("Entrando al adaptador de reserva para el editar");
        dynamoDBMapper.save(ticket, dynamoDBMapperConfig);
        EditReserveResponse editReserveResponse = new EditReserveResponse().builder()
                .id_reserve(ticket.getId())
                .date(ticket.getDate())
                .destination(ticket.getDestination())
                .origin(ticket.getOrigin())
                .build();
        return editReserveResponse;
    }
}
