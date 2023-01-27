package com.example.weatherdetails.presentation

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.common.base.BaseFragment
import com.example.common.di.assistedViewModels
import com.example.common.extension.observe
import com.example.common.extension.setText
import com.example.weatherdetails.R
import com.example.weatherdetails.databinding.WeatherDetailsFragmentBinding
import com.example.weatherdetails.di.WeatherDetailsComponent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.Lazy
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

internal class WeatherDetailsFragment :
    BaseFragment<WeatherDetailsViewModel>(R.layout.weather_details_fragment),
    IHasComponent<WeatherDetailsComponent>,
    OnMapReadyCallback {

    private val args by navArgs<WeatherDetailsFragmentArgs>()

    private val binding by viewBinding(WeatherDetailsFragmentBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<WeatherDetailsViewModel.Factory>
    override val viewModel: WeatherDetailsViewModel by assistedViewModels {
        viewModelFactory.get().create(args.coord)
    }

    override fun getComponent() = WeatherDetailsComponent.Initializer.init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XInjectionManager.bindComponent(this).inject(this)
    }

    override fun onInitView() {
        super.onInitView()

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onObserveData() {
        super.onObserveData()

        viewModel.currentWeather.observe(viewLifecycleOwner) { currentWeather ->
            with(binding) {
                description.setText(currentWeather.description)
                currentAirTemperature.setText(currentWeather.currentAirTemperature)
                minMaxTemp.setText(currentWeather.minMaxTemp)
                airHumidity.setText(currentWeather.airHumidity)
                windSpeed.setText(currentWeather.windSpeed)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val coordinates = LatLng(args.coord.lat, args.coord.lon)

        with(googleMap) {
            uiSettings.setAllGesturesEnabled(false)
            animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, ZOOM))
            addMarker(MarkerOptions().position(coordinates))
        }
    }

    companion object {
        private const val ZOOM = 8f
    }
}
