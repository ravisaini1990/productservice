package com.ravi.productservice.controller;

import com.ravi.productservice.dto.CouponDto;
import com.ravi.productservice.modal.Product;
import com.ravi.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RestController
@RequestMapping("/products_api")
public class ProductsController {

    @Autowired
    ProductRepository productRepository;

    @Value("${couponService.url}")
    private String couponServiceUrl;

    @Autowired
    RestTemplate restTemplate;

    /*@PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        CouponDto coupon = restTemplate.getForObject(couponServiceUrl+product.getCouponCode(), CouponDto.class);
        assert coupon != null;
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return productRepository.save(product);
    }*/

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product, @RequestHeader("Authorization") String authorizationHeader) {
        String encodedCredentials = authorizationHeader.replace("Basic ", "");
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedCredentials = new String(decodedBytes);
        String[] credentials = decodedCredentials.split(":");
        String username = credentials[0];
        String password = credentials[1];

        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));

        CouponDto coupon = restTemplate.getForObject(couponServiceUrl + product.getCouponCode(), CouponDto.class);
        assert coupon != null;
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return productRepository.save(product);
    }

   /* @PostMapping("/products")
    public Product createProduct(@RequestBody Product product, @RequestHeader("Authorization") String authorizationHeader) {
        String encodedCredentials = authorizationHeader.replace("Basic ", "");
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedCredentials = new String(decodedBytes);
        String[] credentials = decodedCredentials.split(":");

        String username = credentials[0];
        String password = credentials[1];

        // Forward the credentials to the coupon API
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password); // Set Basic Auth header
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CouponDto> response = restTemplate.exchange(
                couponServiceUrl+product.getCouponCode(),
                HttpMethod.GET,
                entity,
                CouponDto.class
        );

        // Update product details with coupon information
        product.setPrice(product.getPrice().subtract(response.getBody().getDiscount()));
        return productRepository.save(product);
    }
*/
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return  productRepository.findById(id).orElse(null);
    }

}
