package br.com.api.weplant.services;

import br.com.api.weplant.entities.Phone;
import br.com.api.weplant.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public Phone insert(Phone phone) {
        return phoneRepository.save(phone);
    }

}
