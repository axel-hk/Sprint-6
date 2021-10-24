package com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import com.example.demo.dao.Address
import com.example.demo.service.BookingService
import java.util.concurrent.ConcurrentHashMap

@Controller
@RequestMapping("/app")
class Controller @Autowired constructor(val bookingService: BookingService) {

    @RequestMapping("/add", method = [RequestMethod.GET])
    fun addAddressGetPage(): String {
        return "add"
    }

    @RequestMapping("/add", method = [RequestMethod.POST])
    fun addAddress(@ModelAttribute form: Address, model: Model): String {
        bookingService.addAddress(
            Address(
                name = form.name,
                surname = form.surname,
                address = form.address,
                telephoneNumber = form.telephoneNumber
            )
        )
        model.addAttribute("action", "Вы успешно добавили запись")
        return "result"
    }

    @RequestMapping("/list", method = [RequestMethod.GET])
    fun getAddresses(model: Model): String {
        val addresses = bookingService.getAddresses()
        model.addAttribute("addresses", addresses)
        return "list"
    }

    @RequestMapping("/{id}/view", method = [RequestMethod.GET])
    fun getAddress(@PathVariable("id") id: Int, model: Model): String {
        val addresses = ConcurrentHashMap<Int, Address?>()
        bookingService.getAddress(id)?.let { addresses.put(id, it) }
        model.addAttribute("addresses", addresses)
        return "list"
    }

    @RequestMapping("/{id}/edit", method = [RequestMethod.GET])
    fun updateAddressGetPage(@PathVariable("id") id: Int, model: Model): String {
        model.addAttribute("id", id.toString())
        return "edit"
    }

    @RequestMapping("/{id}/edit")
    fun updateAddress(@PathVariable("id") id: Int, @ModelAttribute form: Address, model: Model): String {
        bookingService.updateAddress(id = id, address = Address(
            name = form.name,
            surname = form.surname,
            address = form.address,
            telephoneNumber = form.telephoneNumber
        )
        )
        model.addAttribute("action", "Вы успешно обновили запись")
        return "result"
    }

    @RequestMapping("/{id}/delete")
    fun deleteAddress(@PathVariable("id") id: Int, model: Model): String {
        model.addAttribute("action", "Вы успешно удалили запись")
        bookingService.deleteAddress(id)
        return "result"
    }
}