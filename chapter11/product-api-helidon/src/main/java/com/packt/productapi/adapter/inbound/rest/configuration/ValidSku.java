package com.packt.productapi.adapter.inbound.rest.configuration;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@NotNull
@Constraint(validatedBy = {})
@Pattern(regexp = "[A-Za-z]{2}[0-9]{5}", message = "SKU must follow the pattern AA99999")
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSku {
    String message() default "Invalid SKU";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
