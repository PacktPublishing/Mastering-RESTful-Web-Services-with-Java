package com.packt.productapi.adapter.inbound.rest.configuration;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotBlank
@Constraint(validatedBy = {})
@Pattern(regexp = "[A-Za-z]{2}[0-9]{5}", message = "SKU must follow the pattern AA99999")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSku {
    String message() default "Invalid SKU";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
