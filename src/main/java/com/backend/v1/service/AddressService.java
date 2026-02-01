package com.backend.v1.service;

import com.backend.v1.model.Address;
import java.util.List;

public interface AddressService {
    Address save(Address address);

    List<Address> getAll();

    Address getById(Long id);

    void delete(Long id);

    Address update(Long id, Address address);
}
