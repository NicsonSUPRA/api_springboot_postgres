package com.nicson.apipostgres.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nicson.apipostgres.models.Category;
import com.nicson.apipostgres.models.Order;
import com.nicson.apipostgres.models.OrderItem;
import com.nicson.apipostgres.models.Product;
import com.nicson.apipostgres.models.Role;
import com.nicson.apipostgres.models.User;
import com.nicson.apipostgres.models.enums.OrderStatus;
import com.nicson.apipostgres.repositories.CategoryRepository;
import com.nicson.apipostgres.repositories.OrderItemRepository;
import com.nicson.apipostgres.repositories.OrderRepository;
import com.nicson.apipostgres.repositories.ProductRepository;
import com.nicson.apipostgres.repositories.RoleRepository;
import com.nicson.apipostgres.repositories.UserRepository;
import com.nicson.apipostgres.services.UserService;

@Configuration
public class Init implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Role r1 = new Role("ADMIN");
        Role r2 = new Role("USER");

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        u1.setRoles(Arrays.asList("ADMIN", "USER"));
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        u2.setRoles(Arrays.asList("USER"));
        u1.setRoles(Arrays.asList(r1));
        u2.setRoles(Arrays.asList(r1, r2));

        Order o1 = new Order(0, new Date(), OrderStatus.PAID, u1);
        Order o2 = new Order(0, new Date(), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(0, new Date(), OrderStatus.SHIPPED, u1);

        Category cat1 = new Category(0, "Electronics");
        Category cat2 = new Category(0, "Books");
        Category cat3 = new Category(0, "Computers");

        Product p1 = new Product(0, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(0, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(0, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(0, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(0, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p3.getCategories().addAll(Arrays.asList(cat1, cat3));
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        // userRepository.saveAll(Arrays.asList(u1, u2));
        userService.insert(u1);
        userService.insert(u2);
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p4, p5));
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
        System.out.println(userService.obterPorLogin("maria@gmail.com"));
        roleRepository.saveAll(Arrays.asList(r1, r2));

        System.out.println(u2.getRoles());
    }

}
