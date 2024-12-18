package com.packt.productapi.adapter.exception;

import com.packt.productapi.adapter.inbound.rest.dto.ProblemDetail;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException validationException) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ProblemDetail(
                        Response.Status.BAD_REQUEST.getReasonPhrase(),
                        Response.Status.BAD_REQUEST.getStatusCode(),
                        validationException.getMessage()))
                .build();
    }
}
