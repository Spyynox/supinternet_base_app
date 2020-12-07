package com.supinternet.aqi.ui.screens.main.tabs.travel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.supinternet.aqi.R
import com.supinternet.aqi.data.network.AQIAPI
import com.supinternet.aqi.data.network.model.ranking.Rankings
import kotlinx.coroutines.*

class TravelTab : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO 1) Modifier pour donner le layout contenant une recherche + RecyclerView
        return inflater.inflate(R.layout.fragment_travel, container, false)
        // return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO 2) Faire la requête pour récupérer les données par pays
        GlobalScope.launch {
            val countries = AQIAPI.getInstance().listCountries().await()

            withContext(Dispatchers.Main) {
                // TODO 3) Ordonner les résultats reçus
                countries.countries.sortedByDescending { it.aqi }

                // TODO 4) Créer un Adapter pour la RecyclerView afin d'afficher dans la liste les résultats
                val adapter = CountriesAdapter(countries.countries)
                val recyclerView = view.findViewById<RecyclerView>(R.id.listCountries)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = adapter

                // TODO 5) Ajouter un listener sur la recherche pour filtrer les résultats en fonction de la saisie
            }
        }
    }
}