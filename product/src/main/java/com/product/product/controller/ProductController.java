package com.product.product.controller;

import com.product.product.dto.ProductDTO;
import com.product.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getallproduct")
    private List<ProductDTO> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/productitemid/{productId}")
    private ProductDTO getProductById(@PathVariable int productId) {
        return productService.productItemId(productId);
    }

    @PostMapping(value = "/addproduct")
    private String addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PutMapping(value = "/updateproduct")
    private ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping(value = "/deleteproduct/{productId}")
    private String deleteProduct(@PathVariable int productId) {
        return productService.deleteProduct(productId);
    }

}
