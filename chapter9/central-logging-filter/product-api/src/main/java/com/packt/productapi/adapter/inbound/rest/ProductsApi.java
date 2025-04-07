package com.packt.productapi.adapter.inbound.rest;

import com.packt.productapi.adapter.inbound.rest.configuration.ValidSku;
import com.packt.productapi.adapter.inbound.rest.dto.PaginatedProducts;
import com.packt.productapi.adapter.inbound.rest.dto.ProductDescriptionInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "products", description = "the products API")
public interface ProductsApi {

    @Operation(
            operationId = "createProduct",
            summary = "Create or update a product",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOutput.class))
                    }),
                    @ApiResponse(responseCode = "200", description = "Product updated successfully", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOutput.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(schema = @Schema(implementation = ProblemDetail.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Validation Error",
                                                    summary = "Example of validation error",
                                                    value = """
                                                            {
                                                              "type": "about:blank",
                                                              "title": "Bad Request",
                                                              "status": 400,
                                                              "detail": "Invalid request content.",
                                                              "instance": "/api/products/AK21109",
                                                              "errors": [
                                                                "name: cannot be blank"
                                                              ]
                                                            }
                                                            """
                                            )
                                    })),
            }
    )
    ResponseEntity<ProductOutput> createOrUpdateProduct(
            @Parameter(name = "productId", description = "ID of the product", required = true, in = ParameterIn.PATH, example = "AK21109") @ValidSku String productId,
            @Parameter(name = "ProductInput", description = "The product data", required = true) @Valid ProductInput productInput
    );

    @Operation(
            operationId = "deleteProduct",
            summary = "Logical remove a product by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Product removed successfully"),
            }
    )
    ResponseEntity<Void> deleteProduct(
            @Parameter(name = "productId", description = "ID of the product", required = true, in = ParameterIn.PATH, example = "AK21109") @ValidSku String productId
    );

    @Operation(
            operationId = "editProduct",
            summary = "Edit product information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product updated successfully", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOutput.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Product not found",
                            content = @Content(schema = @Schema(implementation = ProblemDetail.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Not found Error",
                                                    summary = "Example of not found error",
                                                    value = """
                                                            {
                                                              "type": "about:blank",
                                                              "title": "Not Found",
                                                              "status": 404,
                                                              "detail": "Product not found with id AK21102",
                                                              "instance": "/api/products/AK21102"
                                                            }
                                                            """
                                            )
                                    })),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(schema = @Schema(implementation = ProblemDetail.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Validation Error",
                                                    summary = "Example of validation error",
                                                    value = """
                                                            {
                                                              "type": "about:blank",
                                                              "title": "Bad Request",
                                                              "status": 400,
                                                              "detail": "Invalid request content.",
                                                              "instance": "/api/products/AK21109",
                                                              "errors": [
                                                                "productId: SKU must follow the pattern AA99999"
                                                               ]
                                                            }
                                                            """
                                            )
                                    })),
            }
    )
    ResponseEntity<ProductOutput> editProduct(
            @Parameter(name = "productId", description = "ID of the product", required = true, in = ParameterIn.PATH, example = "AK21109") @ValidSku String productId,
            @Parameter(name = "description", description = "Description of the product", required = true) @Valid ProductDescriptionInput description
    );

    @Operation(
            operationId = "getProductById",
            summary = "Retrieve a product by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOutput.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Product not found",
                            content = @Content(schema = @Schema(implementation = ProblemDetail.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Not found Error",
                                                    summary = "Example of not found error",
                                                    value = """
                                                            {
                                                              "type": "about:blank",
                                                              "title": "Not Found",
                                                              "status": 404,
                                                              "detail": "Product not found with id AK21102",
                                                              "instance": "/api/products/AK21102"
                                                            }
                                                            """
                                            )
                                    })),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(schema = @Schema(implementation = ProblemDetail.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Validation Error",
                                                    summary = "Example of validation error",
                                                    value = """
                                                            {
                                                              "type": "about:blank",
                                                              "title": "Bad Request",
                                                              "status": 400,
                                                              "detail": "Invalid request content.",
                                                              "instance": "/api/products/AK21109",
                                                              "errors": [
                                                                "productId: SKU must follow the pattern AA99999"
                                                               ]
                                                            }
                                                            """
                                            )
                                    })),
            }
    )
    ResponseEntity<ProductOutput> getProductById(
            @Parameter(name = "productId", description = "ID of the product", required = true, in = ParameterIn.PATH, example = "AK21109") @ValidSku String productId
    );

    @Operation(
            operationId = "getProducts",
            summary = "Retrieve all products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "A list of products", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductOutput.class)))
                    })
            }
    )
    ResponseEntity<List<ProductOutput>> getProducts();

    @Operation(
            operationId = "getProducts",
            summary = "Retrieve all products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "A list of products", content = {
                            @Content(mediaType = "application/vnd.packt-v2+json", array = @ArraySchema(schema = @Schema(implementation = PaginatedProducts.class)))
                    })
            }
    )
    ResponseEntity<PaginatedProducts> getProductsV2(
            @Parameter(name = "page", description = "Number of current page", in = ParameterIn.QUERY, example = "0") Integer page,
            @Parameter(name = "limit", description = "Size of elements per page", in = ParameterIn.QUERY, example = "10") Integer limit);


    @Operation(
            operationId = "uploadProductPhoto",
            summary = "Upload photo of the product",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Upload successful"),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "413", description = "File too large", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "415", description = "Invalid file type", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    ResponseEntity<String> uploadProductPhoto(
            @Parameter(name = "productId", description = "ID of the product", required = true, in = ParameterIn.PATH, example = "AK21109") @ValidSku String productId,
            @RequestPart(name = "file", required = true) MultipartFile file);

    @Operation(
            operationId = "downloadProductPhoto",
            summary = "Download a product photo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product photo found"),
                    @ApiResponse(responseCode = "404", description = "Product or its photo not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            }
    )
    ResponseEntity<byte[]> downloadProductPhoto(
            @Parameter(name = "productId", description = "ID of the product", required = true, in = ParameterIn.PATH, example = "AK21109") @ValidSku String productId
    );

}
