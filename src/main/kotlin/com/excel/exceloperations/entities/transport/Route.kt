//package com.urskool.entities.transport
//
//import lombok.Data
//import org.hibernate.annotations.OnDelete
//import org.hibernate.annotations.OnDeleteAction
//import javax.persistence.*
//
//@Data
//@Entity
//@Table(name = "transport_routes")
//class Route(
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        val id: Long? = -1,
//
//        var code: String,
//        var startPlace: String,
//        var stopPlace: String,
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "vehicle_id", nullable = false)
//        @OnDelete(action = OnDeleteAction.CASCADE)
//        private val vehicle: Vehicle? = null
//) {
//    fun getVehicle(): Vehicle? {
//        return vehicle
//    }
//
//    fun getVehicleId(): Long? {
//        return vehicle?.id
//    }
//}