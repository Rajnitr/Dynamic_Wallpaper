package com.example.myscreensaver

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.myscreensaver.Worker.MyWorker
import com.example.myscreensaver.adapters.MyAdapter
import com.example.myscreensaver.network.PexelApiService
import com.example.myscreensaver.network.RetrofitClientPexel
import com.example.myscreensaver.network.RetrofitClientPixabay
import com.example.myscreensaver.network.data.Images
import com.example.myscreensaver.service.MyService
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val TAG = "raj_dynamic_wallpaper"
    var menuIcon: ImageView? = null
     var images: Images? = null

    var imageResultsLiveData= MutableLiveData<Images>()
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    lateinit var mainFragmentAdapter: MainFragmentAdapter
    lateinit var pexelApiService: PexelApiService
    lateinit var mainRepository: MainRepository
    lateinit var mainViewModel: MainViewModel
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout= findViewById(R.id.tab_layout)
        viewPager= findViewById(R.id.main_viewpager)
        drawerLayout= findViewById(R.id.drawer_layout)
        toolbar= findViewById(R.id.top_toolbar)

        val toggle= ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //add all tabs/categories
        for (item in Utils.getCategories()) {
            tabLayout.addTab(tabLayout.newTab().setText(item))
        }

        val fragmentManager= supportFragmentManager
        mainFragmentAdapter= MainFragmentAdapter(fragmentManager, lifecycle, this)
        viewPager.adapter= mainFragmentAdapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { viewPager.setCurrentItem(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

         pexelApiService= RetrofitClientPexel.INSTANCE
         mainRepository= MainRepository(pexelApiService)
         mainViewModel= ViewModelProvider(this, MainViewModelFactory(mainRepository = mainRepository)).get(MainViewModel::class.java)
        //startServiceViaWorker()

    }

    fun onStopServiceClick(view: View) {
        stopMyService()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onStartServiceClick(view: View) {
       startMyService()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyService() {
        Log.d(TAG, "startMyService: MainActivity invoked")
        if(!MyService.isServiceRunning) {
            val intent= Intent(this, MyService::class.java)
            startForegroundService(intent)
        }
    }

    private fun stopMyService() {
        Log.d(TAG, "stopMyService: MainActivity invoked")
        //if service running stop it
        if(MyService.isServiceRunning) {
            val serviceIntent= Intent(this, MyService::class.java)
            stopService(serviceIntent)
        }
    }

    private fun startServiceViaWorker () {
        Log.d(TAG, "startServiceViaWorker: invoked")
        val UNIQUE_WORK_NAME= "StartMyServiceViaWorker"
        val workManager= WorkManager.getInstance(this)
        val periodicWorkRequest= PeriodicWorkRequest.Builder(MyWorker::class.java, 20, TimeUnit.MINUTES).build()

        // below method will schedule a new work, each time app is opened
        //workManager.enqueue(request);

        // to schedule a unique work, no matter how many times app is opened i.e. startServiceViaWorker gets called
        // https://developer.android.com/topic/libraries/architecture/workmanager/how-to/unique-work
        // do check for AutoStart permission
        workManager.enqueueUniquePeriodicWork(UNIQUE_WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: MainActivity invoked")
        stopMyService()
        super.onDestroy()
    }

    private fun getImages() {
        CoroutineScope(Dispatchers.IO).launch {
            val results = RetrofitClientPixabay.INSTANCE.getImages("39075471-061c40a0b606c525ec8686d8d"/*, "flowers", "nature"*/)
            if(results.isSuccessful) {
                Log.d(TAG, "getImages: msg=" +results.body())
                images= results.body()!!
                imageResultsLiveData.postValue(images)
            }
            else {
                Log.d(TAG, "getImages: failure")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "onCreateOptionsMenu: invoked")
        menuInflater.inflate(R.menu.main_menu, menu)
        return true/*super.onCreateOptionsMenu(menu)*/
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.start_service -> {
                startMyService()
            }
            R.id.stop_service -> {
                stopMyService()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun getContext(): Context {
        return this
    }


}