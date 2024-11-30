package com.bidding.vendor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bidding.vendor.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByUserId(Long userId);
    Optional<Vendor> findByVendorId(Long vendorId);
}
