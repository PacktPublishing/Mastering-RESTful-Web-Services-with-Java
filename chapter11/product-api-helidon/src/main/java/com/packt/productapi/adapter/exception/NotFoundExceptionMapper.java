package com.packt.productapi.adapter.exception;

import com.packt.productapi.adapter.inbound.rest.dto.ProblemDetail;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException entityNotFoundException) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ProblemDetail(
                        Response.Status.NOT_FOUND.getReasonPhrase(),
                        Response.Status.NOT_FOUND.getStatusCode(),
                        entityNotFoundException.getMessage()))
                .build();
    }
}
