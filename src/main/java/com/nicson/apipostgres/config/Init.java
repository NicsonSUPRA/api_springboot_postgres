package com.nicson.apipostgres.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nicson.apipostgres.models.Order;
import com.nicson.apipostgres.models.Product;
import com.nicson.apipostgres.models.User;
import com.nicson.apipostgres.models.enums.OrderStatus;
import com.nicson.apipostgres.repositories.OrderRepository;
import com.nicson.apipostgres.repositories.ProductRepository;
import com.nicson.apipostgres.repositories.UserRepository;

@Configuration
public class Init implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(0, new Date(), OrderStatus.PAID, u1);
        Order o2 = new Order(0, new Date(), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(0, new Date(), OrderStatus.SHIPPED, u1);

        Product p1 = new Product(0, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(0, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(0, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(0, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(0, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p4));
    }

}
