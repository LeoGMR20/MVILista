package com.example.mvilista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvilista.api.CartoonService
import com.example.mvilista.databinding.ActivityMainBinding
import com.example.mvilista.view.CartoonListAdapter
import com.example.mvilista.view.MainState
import com.example.mvilista.viewmodel.MainViewModel
import com.example.mvilista.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = CartoonListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        initializeViewModel()
        //la vista recibe un estado luego de la interacción desde viewModel
        //la vista tiene que en base a ese estado cambiar algo visual
        setupStatesToView()
    }

    private fun setupStatesToView() {
        lifecycleScope.launch {
            viewModel.state.collect { collector ->
                when(collector) {
                    is MainState.Loading -> {
                        btnCartoons.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                        pbProgress.visibility = View.VISIBLE
                    }
                    is MainState.Cartoons -> {
                        btnCartoons.visibility = View.GONE
                        pbProgress.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }
                    else -> {
                        btnCartoons.visibility = View.VISIBLE
                        pbProgress.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setupUI() {
        //Configurar el recyclerView
        //1) Configurar su layout y su forma de distribución
        //2) Configurar que adaptador va a usar el recycler view
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.run {
            //personalización de aspecto, separación entre items
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
    }

    private fun initializeViewModel() {
        //necesita un oriquestador o director para crear este objeto y
        //sus configuraciones
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(CartoonService.api)
        ).get(MainViewModel::class.java)
    }
}