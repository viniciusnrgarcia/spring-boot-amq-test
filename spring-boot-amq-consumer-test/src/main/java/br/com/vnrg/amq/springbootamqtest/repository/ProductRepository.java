package br.com.vnrg.amq.springbootamqtest.repository;

import br.com.vnrg.amq.springbootamqtest.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

//    @Modifying
//    @Query(value = "update tb_product p set p.product_items = (p.product_items -1) where p.id = :id and p.product_items > 0 ", nativeQuery = true)
//    @Transactional
//    int updateProductItemReserved(@Param("id") final Long id);
}
