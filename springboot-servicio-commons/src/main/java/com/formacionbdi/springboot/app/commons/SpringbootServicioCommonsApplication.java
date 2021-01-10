package com.formacionbdi.springboot.app.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}) //Si no pongo esto deberia de dejar la dependencia h2 en el pom si pongo la dependencia de jpa hay que añadir una base de datos si o si y en este caso pongo h2 porque spring la autoconfigura
public class SpringbootServicioCommonsApplication {

}
