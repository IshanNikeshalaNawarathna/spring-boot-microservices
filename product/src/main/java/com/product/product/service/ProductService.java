package com.product.product.service;


import com.product.product.dto.ProductDTO;
import com.product.product.model.Product;
import com.product.product.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {
        }.getType());
    }

    public ProductDTO productItemId(int productItemId) {
        Product product = productRepo.getProductItemId(productItemId);
        return modelMapper.map(product, ProductDTO.class);
    }

    public String addProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productRepo.save(product);
        return "Product added Successfully";
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productRepo.save(product);
        return productDTO;
    }

    public String deleteProduct(int productId) {
        productRepo.deleteById(productId);
        return "Product deleted Successfully";
    }

}
