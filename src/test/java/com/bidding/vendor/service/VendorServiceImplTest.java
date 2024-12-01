package com.bidding.vendor.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bidding.vendor.entity.Vendor;
import com.bidding.vendor.exception.VendorAlreadyExistsException;
import com.bidding.vendor.repository.VendorRepository;
import com.bidding.vendor.restCallHandler.UserClient;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorServiceImpl vendorService;

    private Vendor vendor;
    
    @Mock
	private UserClient userClient;

    @BeforeEach
    void setUp() {
        vendor = new Vendor();
        vendor.setVendorId(1L);
        vendor.setUserId(1L);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorDescription("Test Description");
    }

    @Test
    void testRegisterVendor_Success() {
       when(vendorRepository.save(vendor)).thenReturn(vendor);
       when(userClient.getUserById(vendor.getUserId())).thenReturn(new ResponseEntity<>(vendor, HttpStatus.OK) );
       
        Vendor result = vendorService.registerVendor(vendor);

        assertNotNull(result);
        assertEquals(vendor.getVendorId(), result.getVendorId());
        assertEquals(vendor.getUserId(), result.getUserId());
        assertEquals(vendor.getVendorName(), result.getVendorName());
        assertEquals(vendor.getVendorDescription(), result.getVendorDescription());
        verify(vendorRepository).save(vendor);
    }

    

    @Test
    void testDeleteVendor() {
        vendorService.deleteVendor(1L);

        verify(vendorRepository).deleteById(1L);
    }

    @Test
    void testUpdateVendor() {
        when(vendorRepository.save(vendor)).thenReturn(vendor);

        Vendor result = vendorService.updateVendor(vendor);

        assertNotNull(result);
        assertEquals(vendor.getVendorId(), result.getVendorId());
        assertEquals(vendor.getUserId(), result.getUserId());
        assertEquals(vendor.getVendorName(), result.getVendorName());
        assertEquals(vendor.getVendorDescription(), result.getVendorDescription());
        verify(vendorRepository).save(vendor);
    }

    @Test
    void testGetVendorByUserId_Found() {
        when(vendorRepository.findByUserId(1L)).thenReturn(Optional.of(vendor));

        Optional<Vendor> result = vendorService.getVendorByUserId(1L);

        assertTrue(result.isPresent());
        assertEquals(vendor.getVendorId(), result.get().getVendorId());
        assertEquals(vendor.getUserId(), result.get().getUserId());
        assertEquals(vendor.getVendorName(), result.get().getVendorName());
        assertEquals(vendor.getVendorDescription(), result.get().getVendorDescription());
    }

    @Test
    void testGetVendorByUserId_NotFound() {
        when(vendorRepository.findByUserId(1L)).thenReturn(Optional.empty());

        Optional<Vendor> result = vendorService.getVendorByUserId(1L);

        assertFalse(result.isPresent());
    }
}