package com.makenshi.inventory.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.makenshi.inventory.addModule.view.AddProductFragment
import com.makenshi.inventory.entities.Product
import com.makenshi.inventory.mainModule.view.adapters.OnClickListener
import com.makenshi.inventory.mainModule.view.adapters.ProductAdapter
import com.google.android.material.snackbar.Snackbar
import com.makenshi.inventory.R
import com.makenshi.inventory.databinding.ActivityMainBinding
import com.makenshi.inventory.mainModule.viewModel.MainViewModel

/****
 * Project: Inventory base
 * From: com.cursosant.inventorybase.mainModule.view
 * Created by Alain Nicolás Tello on 23/05/23 at 17:22
 * All rights reserved 2023.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupFab()

        setupViewModel()
    }

    private fun setupAdapter(){
        adapter = ProductAdapter(this)
    }

    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity,
                resources.getInteger(R.integer.main_columns))
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupFab(){
        binding.fab.setOnClickListener { //Open add fragment
            AddProductFragment().show(supportFragmentManager, getString(R.string.add_title))
        }
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getProducts().observe(this, { products ->
            setListToAdapter(products)
        })
        viewModel.isWelcome().observe(this, { isWelcome ->
            if (isWelcome){
                Snackbar.make(binding.root, getString(R.string.main_msg_welcome), Snackbar.LENGTH_SHORT).show()
                viewModel.setWelcome(false)
            }
        })
    }

    private fun setListToAdapter(products: List<Product>) {
        adapter.submitList(products)
    }

    /**
     * OnClickListener
     * */
    override fun onClick(product: Product) {
        viewModel.setWelcome(true)
    }

    override fun onLongClick(product: Product) {
        viewModel.deleteProduct(product)
    }
}