package com.devdaph.ecommerce.mapper;

import com.devdaph.ecommerce.dto.*;
import com.devdaph.ecommerce.entity.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityDtoMapper {

    //User Entity to user Dto

    public UserDto mapUSerToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole().name());
        return userDto;
    }

    //Address to Dto basic
    public AddressDto mapAddressToDtoBasic(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }

    //Category to Dto basic

    public CategoryDto mapCategoryToDtoBasic(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    //OrderItem To dto basic
    public OrderItemDto mapOrderItemToDtoBasic(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setStatus(orderItem.getStatus().name());
        orderItemDto.setCreatedAt(orderItem.getCreatedAt());
        return orderItemDto;
    }

    // Product To Dto Basic
    public ProductsDto mapProductToDtoBasic(Product product) {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setId(product.getId());
        productsDto.setName(product.getName());
        productsDto.setDescription(product.getDescription());
        productsDto.setPrice(product.getPrice());
        productsDto.setImageUrl(product.getImageUrl());
        return productsDto;
    }

    //UserDto to Address
    public UserDto mapUserToDtoPlusAddress(User user) {
        UserDto userDto = new UserDto();
        if (user.getAddress() != null) {
            AddressDto addressDto = mapAddressToDtoBasic(user.getAddress());
            userDto.setAddress(addressDto);
        }
        return userDto;
    }

    //OrderItem to Dto Plus product

    public OrderItemDto mapOrderItemToDtoPlusProduct(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        if (orderItem.getProduct() != null) {
            ProductsDto productsDto = mapProductToDtoBasic(orderItem.getProduct());
            orderItemDto.setProducts(productsDto);
        }
        return orderItemDto;
    }

    // OrderItem to Dto plus product and user
    public OrderItemDto mapOrderItemToDtoPlusProductAndUser(OrderItem orderItem) {
        OrderItemDto orderItemDto = mapOrderItemToDtoPlusProduct(orderItem);
        if (orderItem.getUser() != null) {
            UserDto userDto = mapUserToDtoPlusAddress(orderItem.getUser());
            orderItemDto.setUser(userDto);
        }
        return orderItemDto;
    }

    // user to dto with address and orderItems history

    public UserDto mapUserDtoToDtoAddressAndOrderHistory(User user) {
        UserDto userDto = mapUserToDtoPlusAddress(user);

        if (userDto.getOrderItemList() != null && !userDto.getOrderItemList().isEmpty()) {
            userDto.setOrderItemList(user.getOrderItemList()
                    .stream()
                    .map(this::mapOrderItemToDtoPlusProduct)
                    .collect(Collectors.toList()));
        }
        return userDto;
    }
}
