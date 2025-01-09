package com.packt.ordermanagementapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.CustomerDto;
import com.packt.ordermanagementapi.adapter.inbound.rest.dto.PersonCustomerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParsePolymorhicTest {

    @Test
    public void testParsePolymorphic() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDto customer = objectMapper.readValue("""
                {
                    "customerType": "person",
                    "firstName": "John",
                    "lastName": "Doe",
                    "streetAddress": "1234, Rest Street",
                    "city": "Javatown",
                    "postalCode": "12345"
                }
                """, CustomerDto.class);
        Assertions.assertThat(customer).isInstanceOf(PersonCustomerDto.class);
    }
}
