package com.packt.productapi;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.packt.productapi.adapter.inbound.rest.ProductsApiController;
import com.packt.productapi.adapter.inbound.rest.dto.ProductDescriptionInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductOutput;
import com.packt.productapi.adapter.inbound.rest.mapper.ProductMapper;
import com.packt.productapi.adapter.outbound.database.entity.Product;
import com.packt.productapi.usecase.ProductsCommandUseCase;
import com.packt.productapi.usecase.ProductsQueryUseCase;
import com.packt.productapi.usecase.dto.CreatedProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductsApiControllerTest {

    @Mock
    private ProductsQueryUseCase productsQueryUseCase;

    @Mock
    private ProductsCommandUseCase productsCommandUseCase;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductsApiController productsApiController;

    @BeforeEach
    void setUp() {
        productsApiController = new ProductsApiController(productsQueryUseCase, productsCommandUseCase, productMapper);
    }

    @Test
    void testCreateOrUpdateProduct() {
        // Arrange
        String productId = "123";
        ProductInput input = new ProductInput("test","test product", new BigDecimal(53));
        ProductOutput expectedOutput = new ProductOutput("test","123","test product", new BigDecimal(53));

        when(productsCommandUseCase.createProduct(any()))
                .thenReturn(new CreatedProduct(new Product("test","123","test product", new BigDecimal(53)),true));
        when(productMapper.toProductOutput(any())).thenReturn(expectedOutput);

        // Act
        ResponseEntity<ProductOutput> response = productsApiController.createOrUpdateProduct(productId, input);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
    }

    @Test
    void testGetProductById() {
        // Arrange
        String productId = "123";
        ProductOutput expectedOutput = new ProductOutput("test","123","test product", new BigDecimal(53));

        when(productsQueryUseCase.getProductById(productId)).thenReturn(new Product("test","123","test product", new BigDecimal(53)));
        when(productMapper.toProductOutput(any())).thenReturn(expectedOutput);

        // Act
        ResponseEntity<ProductOutput> response = productsApiController.getProductById(productId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        final var products = List.of(new Product("test","123","test product", new BigDecimal(53)));
        final var expectedOutputs = List.of(new ProductOutput("test","123","test product", new BigDecimal(53)));

        when(productsQueryUseCase.getAllProducts()).thenAnswer(invocation -> products);
        when(productMapper.toProductOutput(any())).thenReturn(expectedOutputs.getFirst());

        // Act
        ResponseEntity<List<ProductOutput>> response = productsApiController.getProducts();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }
    @Test
    void testDeleteProduct() {
        // Arrange
        String productId = "123";

        doNothing().when(productsCommandUseCase).deleteProduct(productId);

        // Act
        ResponseEntity<Void> response = productsApiController.deleteProduct(productId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
    }

    @Test
    void testEditProductDescription() {
        // Arrange
        String productId = "123";
        String newDescription = "Updated description";

        when(productsCommandUseCase.updateProductDescription(productId, newDescription))
                .thenReturn(new Product("",productId,newDescription, new BigDecimal(53)));
        when(productMapper.toProductOutput(any())).thenReturn(new ProductOutput("",productId,newDescription, new BigDecimal(53)));
        // Act
        ResponseEntity<ProductOutput> response = productsApiController.editProductDescription(productId, new ProductDescriptionInput(newDescription));

        // Assert
        assertNotNull(response);
        assertEquals(newDescription, response.getBody().description());
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

}
