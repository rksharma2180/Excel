//package com.urskool.entities.transport
//
//import lombok.Data
//import org.hibernate.annotations.OnDelete
//import org.hibernate.annotations.OnDeleteAction
//import java.io.Serializable
//import javax.persistence.*
//
//
//@Data
//@Entity
//@Table(name = "transport_drivers")
////class Driver(
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        val id: Long? = -1,
//
//        var name: String,
//        var address: String,
//        var permanentAddress: String,
//        var phone: String,
//        var licenseNumber: String,
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "vehicle_id", nullable = false)
//        @OnDelete(action = OnDeleteAction.CASCADE)
//        val vehicle: Vehicle? = null
//) : Serializable {
//    fun getVehicleId(): Long? {
//        return vehicle?.id
//    }
//}