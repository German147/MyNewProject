package org.barreragerman.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barreragerman.entity.Address;
import org.barreragerman.exceptions.DAO_exception;
import org.barreragerman.repositoryDAO.daoImpl.AddressDAOImpl;
import org.barreragerman.service.IAddressService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements IAddressService {

    private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);
    private static final AddressDAOImpl addressDAOImpl = new AddressDAOImpl();

    @Override
    public List<Address> findAllAddresss() {
        List<Address> addressList = new ArrayList<>();
        addressList = addressDAOImpl.list();
        return addressList;
    }

    @Override
    public Address getAddressById(Integer integer) {
        Address address = new Address();
        try {
            address = addressDAOImpl.getById(integer);
        } catch (DAO_exception e) {
            LOGGER.error(e.getMessage());
        }
        return address;
    }

    @Override
    public void updateAddress(Address address) {
        try {
            addressDAOImpl.update(address);
        } catch (DAO_exception e) {
           LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void insertAddress(Address address) {
        try {
            addressDAOImpl.insert(address);
        } catch (DAO_exception | SQLException e) {
         LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteAddress(Integer id) {
        try {
            addressDAOImpl.deleteByID(id);
        } catch (DAO_exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
