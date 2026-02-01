package com.backend.v1.service.serviceImpl;

import com.backend.v1.model.Address;
import com.backend.v1.repository.AddressRepository;
import com.backend.v1.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address){
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found with id : " + id));
    }

    @Override
    public void delete(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found with id: " + id);
        }
        addressRepository.deleteById(id);
    }

    @Override
    public Address update(Long id, Address address) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        existing.setCity(address.getCity());
        existing.setTownship(address.getTownship());
        existing.setRoad(address.getRoad());
        existing.setStreet(address.getStreet());

        return addressRepository.save(existing);
    }


}
