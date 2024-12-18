package com.packt.productapi.adapter.inbound.rest;

import com.packt.productapi.adapter.inbound.rest.configuration.ValidSku;
import com.packt.productapi.adapter.inbound.rest.dto.ProductDescriptionInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductOutput;
import com.packt.productapi.adapter.inbound.rest.mapper.ProductMapper;
import com.packt.productapi.domain.Product;
import com.packt.productapi.usecase.ProductsCommandUseCase;
import com.packt.productapi.usecase.ProductsQueryUseCase;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/products")
public class ProductResource {

    private final ProductMapper productMapper;
    private final ProductsCommandUseCase productsCommandUseCase;
    private final ProductsQueryUseCase productsQueryUseCase;

    @Inject
    public ProductResource(ProductMapper productMapper, ProductsCommandUseCase productsCommandUseCase,
                           ProductsQueryUseCase productsQueryUseCase) {
        this.productMapper = productMapper;
        this.productsCommandUseCase = productsCommandUseCase;
        this.productsQueryUseCase = productsQueryUseCase;
    }

    @PUT
    @Path("{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrUpdateProduct(@PathParam("productId") @ValidSku String productId,
                                          @Valid ProductInput productInput) {

        var product = productsCommandUseCase.createProduct(productMapper.toProduct(productInput, productId));
        Response.Status status = product.isNewProduct() ? Response.Status.CREATED : Response.Status.OK;
        return Response.status(status)
                .entity(productMapper.toProductOutput(product.product())).build();
    }

    @DELETE
    @Path("{productId}")
    public Response deleteProduct(@PathParam("productId") @ValidSku String productId) {
        productsCommandUseCase.deleteProduct(productId);
        return Response.noContent().build();
    }

    @PATCH
    @Path("{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductOutput editProduct(@PathParam("productId") @ValidSku String productId,
                                     @Valid ProductDescriptionInput input) {
        Product product = productsCommandUseCase.updateProductDescription(productId, input.description());
        return productMapper.toProductOutput(product);
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductOutput getProductById(@PathParam("productId") @ValidSku String productId) {
        var product = productsQueryUseCase.getProductById(productId);
        return productMapper.toProductOutput(product);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductOutput> getProducts() {
        return productsQueryUseCase.getAllProducts().stream()
                .map(productMapper::toProductOutput)
                .toList();
    }
}
