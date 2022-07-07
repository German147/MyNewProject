package org.barreragerman.service;

import org.barreragerman.entity.Address;

import java.util.List;

public interface IAddressService {

    List<Address> findAllAddresss();

    Address getAddressById(Integer integer);

    void updateAddress(Address address);

    void insertAddress(Address address);

    void deleteAddress(Integer address);
}
