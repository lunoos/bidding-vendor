package com.bidding.vendor.service;

import java.util.Optional;

import com.bidding.vendor.entity.Vendor;

public interface VendorService{
	
	public Vendor registerVendor(Vendor vendor);

    public void deleteVendor(Long vendorId);

    public Vendor updateVendor(Vendor vendor);

    public Optional<Vendor> getVendorByUserId(Long userId);
    
    public Optional<Vendor> getVendorById(Long vendorId);
}
