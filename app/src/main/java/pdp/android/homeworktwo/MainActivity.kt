package pdp.android.homeworktwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pdp.android.homeworktwo.adapter.DataAdapter
import pdp.android.homeworktwo.databinding.ActivityMainBinding
import pdp.android.homeworktwo.model.Data
import pdp.android.homeworktwo.utils.NetworkHelper

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var networkHelper: NetworkHelper
    private lateinit var requestQueue: RequestQueue
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkHelper = NetworkHelper(this)
        if (networkHelper.isNetworkConnected()){
            requestQueue = Volley.newRequestQueue(this)

            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET,
                "http://cbu.uz/uzc/arkhiv-kursov-valyut/json/",
                null, { response ->
                    val type = object : TypeToken<List<Data>>() {}.type
                    var list: List<Data> = Gson().fromJson(response.toString(),type)
                    println(response.toString())
                    adapter = DataAdapter(list,this)
                    binding.recyclerView.adapter = adapter
                }
            ) {

            }
            requestQueue.add(jsonArrayRequest)
        }

    }

}