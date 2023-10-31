package com.example.user.service;


import com.example.user.model.Device;
import com.example.user.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceService {

    DeviceRepository deviceRepository;

    public List<Device> findAll() {
        List<Device> devices = deviceRepository.findAll();
        return devices;
    }



    public Device findById(Long id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (!deviceOptional.isPresent()) {
            throw new RuntimeException(Device.class.getSimpleName() + " with id: " + id);
        }
        return deviceOptional.get();
    }

    public Device insert(Device device  ) {
        Device newDevice = deviceRepository.save(device);
        if(newDevice==null)
        {
            System.out.println("error");
        }
        return newDevice;
    }

    public Device update(Long id, Device device) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if(deviceOptional.isEmpty()){
            return null;
        }
        Device updateDevice = deviceOptional.get();
        updateDevice.setAddress(device.getAddress());
        updateDevice.setDescription(device.getDescription());
        updateDevice.setMax_hourly_energy_consumption(device.getMax_hourly_energy_consumption());
        updateDevice = deviceRepository.save(updateDevice);
        return updateDevice;
    }

    public Device delete(Long id) {
        Device deleteDevice = findById(id);
        if (deleteDevice!=null)
        {
            deviceRepository.delete(deleteDevice);
        }
        return deleteDevice;
    }
}
