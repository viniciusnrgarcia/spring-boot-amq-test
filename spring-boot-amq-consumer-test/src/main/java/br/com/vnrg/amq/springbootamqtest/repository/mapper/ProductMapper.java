package br.com.vnrg.amq.springbootamqtest.repository.mapper;

import br.com.vnrg.amq.springbootamqtest.domain.Product;
import br.com.vnrg.amq.springbootamqtest.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(imports = UUID.class, componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductEntity toEntity(Product order);

}
