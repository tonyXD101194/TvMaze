package com.app.tvmaze.interfaces

import androidx.fragment.app.Fragment

interface NavigationInterface {

    fun pushFragment(fragment: Fragment)

    fun popFragment()
}