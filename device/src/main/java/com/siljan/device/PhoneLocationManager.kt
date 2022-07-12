package com.siljan.device

import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.jakewharton.rx.ReplayingShare
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

private const val LOCATION_INTERVAL = 4000L
private const val LOCATION_FASTEST_INTERVAL = 1000L

class PhoneLocationManager(context: Context) {

    private val locationSubject = PublishSubject.create<Location>()
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationSettingsRequest: LocationSettingsRequest

    private val locationRequest: LocationRequest = LocationRequest.create()
        .apply {
            priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
            interval = LOCATION_INTERVAL
            fastestInterval = LOCATION_FASTEST_INTERVAL
        }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                locationSubject.onNext(location)
            }
        }
    }

    init {
        locationSettingsRequest = LocationSettingsRequest.Builder()
            .setAlwaysShow(true)
            .addLocationRequest(locationRequest)
            .build()
    }

    /**
     * Each class interested in receiving location updates should subscribe to this stream
     *
     */
    val location: Observable<Location> = locationSubject
        .doOnSubscribe { startLocationUpdates() }
        .doOnComplete { stopLocationUpdates() }
        .doOnError { stopLocationUpdates() }
        .doOnDispose { stopLocationUpdates() }
        .compose(ReplayingShare.instance())

    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
