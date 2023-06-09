package br.com.api.weplant.services;

import br.com.api.weplant.entities.Address;
import br.com.api.weplant.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address insert(Address address) {
        return addressRepository.save(address);
    }
}
