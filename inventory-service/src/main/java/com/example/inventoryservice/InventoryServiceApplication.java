package com.example.inventoryservice;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class);
    }

    //TODO
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
        return args -> {
                Inventory inventory = new Inventory();
                inventory.setSkuCode("iphone-13");
                inventory.setQuantity(100);

                Inventory inventory2 = new Inventory();
                inventory2.setSkuCode("iphone-12");
                inventory2.setQuantity(100);

                inventoryRepository.save(inventory);
                inventoryRepository.save(inventory2);

        };
    }
}
