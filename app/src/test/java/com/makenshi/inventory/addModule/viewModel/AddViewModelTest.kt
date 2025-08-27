package com.makenshi.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.makenshi.inventory.entities.Product
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addProductTest() {
        val addViewModel = AddViewModel()
        val product = Product(117, "Xbox", 20, "https://ss423.liverpool.com.mx/xl/1100132318.jpg",
            4.8, 56)
        val observer = Observer<Boolean>{}
        try {
            addViewModel.getResult().observeForever(observer)
            addViewModel.addProduct(product)
            val result = addViewModel.getResult().value

            assertThat(result, `is`(true))
            assertThat(result, not(nullValue()))
        }
        finally {
            addViewModel.getResult().removeObserver(observer)
        }
    }
}