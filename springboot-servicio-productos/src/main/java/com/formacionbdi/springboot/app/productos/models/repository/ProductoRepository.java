package com.formacionbdi.springboot.app.productos.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formacionbdi.springboot.app.commons.models.entity.ProductoEntity;

@Repository//Al extender de JpaRepository no seria necesario ponerle la anotacion (estereotipo) @Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

}
