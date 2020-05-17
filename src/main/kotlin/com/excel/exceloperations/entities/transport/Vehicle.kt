//package com.urskool.entities.transport
//
//import com.fasterxml.jackson.annotation.JsonIgnore
//import lombok.Data
//import javax.persistence.*
//
//@Data
//@Entity
//@Table(name = "transport_vehicles")
//class Vehicle(
//        var number: String,
//        var seats: Int,
//        var maxAllowedSeats: Int,
//        var careTakerName: String,
//        var careTakerPhone: String,
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        val id: Long? = null,
//
//        @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
//        @JsonIgnore
//        val drivers: MutableList<Driver> = mutableListOf(),
//
//        @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
//        @JsonIgnore
//        val routes: MutableList<Route> = mutableListOf()
//)