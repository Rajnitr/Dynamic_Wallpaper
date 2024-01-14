package com.example.myscreensaver

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myscreensaver.adapters.MyAdapter
import com.example.myscreensaver.network.pexel_model.ImagesPexel


/**
 * A simple [Fragment] subclass.
 * Use the [MainViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainViewFragment(activity: Activity, var position: Int) : Fragment() {

    lateinit var mainActivity: MainActivity
    var images_recyclerview: RecyclerView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        images_recyclerview= view.findViewById(R.id.my_recyclerview)
        //fetch pexel images
        mainActivity.mainViewModel.imagesLiveData.observe(mainActivity, Observer {
            updateAdapter(it)
        })
        if (position==0) {
            if (mainActivity.mainViewModel.categoryImageListMap.contains("All")) {
                var images= mainActivity.mainViewModel.categoryImageListMap.getValue("All")
                updateAdapter(images)
            }
            else {
                mainActivity.mainViewModel.getPexelImages()
            }
        }
        else {
            var query= Utils.getCategories().get(position)
            if (mainActivity.mainViewModel.categoryImageListMap.containsKey(query)) {
                var images= mainActivity.mainViewModel.categoryImageListMap.getValue(query)
                updateAdapter(images)
            }
            else {
                mainActivity.mainViewModel.getPexelImageForSearch(query)
            }
        }
    }

    fun updateAdapter(images: ImagesPexel) {
        images_recyclerview?.layoutManager= GridLayoutManager(mainActivity, 2)
        //images_recyclerview?.layoutManager.
        images_recyclerview?.adapter= MyAdapter(images.photos, mainActivity.applicationContext)
        images_recyclerview?.adapter!!.notifyDataSetChanged()
    }
}