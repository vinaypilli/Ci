package com.teamcomputers.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import com.teamcomputers.model.OrderDetails;

public interface OrderDetailsRepository extends JpaAttributeConverter<OrderDetails, Long>{

}
