package com.logistics.warehouse.services.product;


import com.logistics.warehouse.DTO.product.ProductRequest;
import com.logistics.warehouse.DTO.product.ProductResponse;
import com.logistics.warehouse.models.Product;
import com.logistics.warehouse.models.Warehouse;
import com.logistics.warehouse.repositories.ProductRespository;
import com.logistics.warehouse.repositories.WarehouseRespository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRespository productRespository;
    private final WarehouseRespository warehouseRespository;

    public ProductService(ProductRespository productRespository, WarehouseRespository warehouseRespository) {
        this.productRespository = productRespository;
        this.warehouseRespository = warehouseRespository;
    }

    public ProductResponse createProduct(ProductRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setSku(request.getSku());
        product.setCategory(request.getCategory());
        product.setUnitPrice(request.getUnitPrice());
        product.setCreatedAt(LocalDateTime.now());

        Warehouse warehouse = warehouseRespository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        product.setWarehouse(warehouse);

        Product saved =productRespository.save(product);
        return mapToResponse(saved);
    }

    public List<ProductResponse> getAllProducts(){
        return productRespository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProduct(Long id) {
        Product product = productRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setSku(request.getSku());
        product.setCategory(request.getCategory());
        product.setUnitPrice(request.getUnitPrice());

        if( request.getWarehouseId() != null){
            Warehouse warehouse = warehouseRespository.findById(request.getWarehouseId())
                    .orElseThrow(()-> new RuntimeException("Warehouse not found"));
            product.setWarehouse(warehouse);
        }


        Product updated = productRespository.save(product);
        return mapToResponse(updated);

    }

    public void deleteProduct(Long id){
        productRespository.deleteById(id);
    }




    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getProductid());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setSku(product.getSku());
        response.setCategory(product.getCategory());
        response.setUnitPrice(product.getUnitPrice());
        response.setCreatedAt(product.getCreatedAt());
        response.setWarehouseId(product.getWarehouse().getWarehouseId());
        return response;
    }
}
