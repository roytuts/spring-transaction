package com.roytuts.spring.programmatic.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roytuts.spring.programmatic.transaction.model.Item;
import com.roytuts.spring.programmatic.transaction.service.ItemService;

@SpringBootApplication
public class SpringProgrammaticTransactionApp implements CommandLineRunner {

    @Autowired
    private ItemService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringProgrammaticTransactionApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----Available Items----");

        List<Item> items = service.getItems();
        items.forEach(i -> System.out.println(i));

        System.out.println();

        try {
            Item delItem = new Item();
            delItem.setItemId(6l);
            service.deleteItem(delItem);

            System.out.println("Item Successfully Deleted.");
        } catch (Exception ex) {
            System.out.println("Item was not deleted.");
            System.out.println("Transaction rolled back due to Exception.");
        }

        System.out.println();
    }

}
