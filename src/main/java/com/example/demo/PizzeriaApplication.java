package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private OffertaService offertaService;
    @Autowired
    private IngredienteService ingredienteService;
    
    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
         
          
        /*  Ingrediente i1 = new Ingrediente("pomodoro");
          Ingrediente i2 = new Ingrediente ("mozzarella");
          ingredienteService.save(i1);
          ingredienteService.save(i2);
          
          pizzaService.createAndSavePizza();
          Pizza p1 = new Pizza("Pizza 1", "buona", "www.ciao.it", 13.99f, i1, i2);
          Pizza p2 = new Pizza("Pizza 2", "buona", "www.ciao.it", 10.99f, new Ingrediente[] {i2, i1});
          pizzaService.save(p1);
          pizzaService.save(p2);
          
          Offerta o1 = new Offerta(LocalDate.of(2023, 2, 2), LocalDate.of(2023, 4, 18), "Special", 20, p1);
          Offerta o2 = new Offerta(LocalDate.of(2023, 10, 9), LocalDate.of(2023, 10, 30), "Special", 20, p1);
          Offerta o3 = new Offerta(LocalDate.of(2023, 3, 9), LocalDate.of(2023, 5, 9), "Special", 20, p2);
          Offerta o4 = new Offerta(LocalDate.of(2023, 7, 8), LocalDate.of(2023, 8, 9), "Special", 20, p1);
          offertaService.save(o1);
          offertaService.save(o2);
          offertaService.save(o3);
          offertaService.save(o4); 
          */
    } 

}
