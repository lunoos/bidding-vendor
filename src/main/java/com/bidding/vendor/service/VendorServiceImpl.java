package com.bidding.vendor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.vendor.entity.Vendor;
import com.bidding.vendor.exception.VendorAlreadyExistsException;
import com.bidding.vendor.repository.VendorRepository;
import com.bidding.vendor.restCallHandler.UserClient;

import jakarta.transaction.Transactional;

@Service
public class VendorServiceImpl implements VendorService{
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private UserClient userClient;

	@Transactional
	@Override
    public Vendor registerVendor(Vendor vendor) {
		userClient.getUserById(vendor.getUserId());
//		if(vendorRepository.findByUserId(vendor.getUserId()).isPresent())
//			throw new VendorAlreadyExistsException("Vendor already exit for given userId.");
        return vendorRepository.save(vendor);
    }

	@Transactional
	@Override
    public void deleteVendor(Long vendorId) {
        vendorRepository.deleteById(vendorId);
    }

	@Transactional
	@Override
    public Vendor updateVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

	@Override
    public Optional<Vendor> getVendorByUserId(Long userId) {
        return vendorRepository.findByUserId(userId);
    }
	
	@Override
    public Optional<Vendor> getVendorById(Long vendorId) {
        return vendorRepository.findByVendorId(vendorId);
    }
	
	
}
