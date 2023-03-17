package com.example.photogalery.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photogalery.R
import com.example.photogalery.adapters.CategoryItemAdapters
import com.example.photogalery.databinding.FragmentCategoryBinding
import com.example.photogalery.models.Photo
import com.example.photogalery.models.User
import com.example.photogalery.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Category : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter : CategoryItemAdapters
    private var page : Int = 3
    private var user : MutableList<Photo> = ArrayList()
    private var sort : String = "pop"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category,container,false)
        binding = FragmentCategoryBinding.bind(view)
        initRecyclerView()
        getImages()
        return view
    }

    private fun getImages() {
        page = 3
        val getPost = RetrofitInstance.api.getRecentPhotos(page,30,sort)
        getPost.enqueue(object : Callback<MutableList<Photo>> {
            override fun onResponse(
                call: Call<MutableList<Photo>>,
                response: Response<MutableList<Photo>>
            ) {
                if(response.isSuccessful)
                {
                    user.clear()
                    Log.d("response",response.body().toString())
                    response.body()?.let { user.addAll(it)}
                    adapter.notifyDataSetChanged()
                }
                else
                    Log.d("response",response.body().toString())
            }

            override fun onFailure(call: Call<MutableList<Photo>>, t: Throwable) {
                Log.d("Response","Failed")
            }

        })

    }

    private fun initRecyclerView() {
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CategoryItemAdapters(user,requireContext())
        binding.categoryRecyclerView.adapter = adapter
    }
}