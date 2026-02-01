package com.backend.v1.controller;

import com.backend.v1.model.Address;
import com.backend.v1.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Address")
@CrossOrigin
public class AddressController {
    private  final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    // CREATE
    @PostMapping
    public Address create(@RequestBody Address address) {
        return addressService.save(address);
    }

    // READ ALL
    @GetMapping
    public List<Address> getAll() {
        return addressService.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Address getById(@PathVariable Long id) {
        return addressService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Address update(
            @PathVariable Long id,
            @RequestBody Address address) {
        return addressService.update(id, address);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        addressService.delete(id);
        return "Address deleted successfully";
    }
}
