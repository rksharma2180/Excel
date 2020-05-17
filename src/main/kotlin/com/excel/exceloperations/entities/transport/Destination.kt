//package com.urskool.entities.transport
//
//import lombok.Data
//import org.hibernate.annotations.OnDelete
//import org.hibernate.annotations.OnDeleteAction
//import java.sql.Time
//import javax.persistence.*
//
//@Data
//@Entity
//@Table(name = "transport_pd_points")
//class Destination(
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        val id: Long? = null,
//
//        var name: String,
//        var stopTime: Time,
//        var amount: Int,
//        var oneWayAmount: Int,
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "route_id", nullable = false)
//        @OnDelete(action = OnDeleteAction.CASCADE)
//        private val route: Route? = null
//) {
//    fun getRoute(): Route? {
//        return route
//    }
//
//    fun getRouteId(): Long? {
//        return route?.id
//    }
//}