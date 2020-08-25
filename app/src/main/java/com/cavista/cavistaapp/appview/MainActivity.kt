package com.cavista.cavistaapp.appview

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cavista.cavistaapp.R
import com.cavista.cavistaapp.appview.imagedetail.view.ImageDetailFragment
import com.cavista.cavistaapp.appview.imagelist.view.ImageListFragment
import com.cavista.cavistaapp.utils.AppFragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var ft: FragmentTransaction? = null
    private val stack = Stack<Fragment>()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(this,ImageListFragment(),null)
    }

    fun addFragment(context: Context,fragment: Fragment, args: Bundle?) {
        ft = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        if (args != null && args is Bundle) {
            fragment.arguments = args
        }
        ft!!.add(R.id.mainContainer, fragment)
        if (!stack.isEmpty()) {
            stack.lastElement().onPause()
            ft!!.hide(stack.lastElement())
        }
        stack.push(fragment)
        ft!!.commit()
    }

    fun popFragment() {
        val fragmentManager = this.supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        if (!stack.isEmpty()) {
            stack.lastElement().onPause()
            ft.remove(stack.pop())
        }
        ft.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("-","----destroy")

    }
    override fun onBackPressed() {
        if(stack.size <= 1) {
            super.onBackPressed()
        }else{
            popFragment()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MainActivity? = null
        fun getInstance(context: Context): MainActivity? {
            if (INSTANCE == null) {
                INSTANCE = MainActivity()
            }
            return INSTANCE
        }
    }
}
