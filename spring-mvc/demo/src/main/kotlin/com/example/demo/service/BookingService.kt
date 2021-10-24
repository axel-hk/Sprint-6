package com.example.demo.service


import com.example.demo.dao.Address
import java.util.concurrent.ConcurrentHashMap

interface BookingService {

    fun addAddress(address: Address)

    fun getAddresses(): ConcurrentHashMap<Int, Address>

    fun getAddress(id: Int): Address?

    fun updateAddress(address: Address, id: Int)

    fun deleteAddress(id: Int)

}