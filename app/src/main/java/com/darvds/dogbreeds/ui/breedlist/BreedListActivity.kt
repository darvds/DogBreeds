package com.darvds.dogbreeds.ui.breedlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.darvds.dogbreeds.R
import com.darvds.dogbreeds.application.BaseApplication
import com.darvds.dogbreeds.models.DogBreed
import com.darvds.dogbreeds.ui.viewholders.BreedViewHolder
import kotlinx.android.synthetic.main.activity_breed_list.*
import javax.inject.Inject

class BreedListActivity : AppCompatActivity(), BreedListPresenter.View {

    @Inject
    lateinit var presenter: BreedListPresenter

    var adapter: BreedListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_breed_list)

        initUi()

        (application as? BaseApplication)?.appComponent?.inject(this)

        presenter.attachView(this)
        presenter.start()
    }

    /**
     * Initialise the UI with the required settings for the recycler and hide the loading
     * indicator
     */
    private fun initUi() {
        breed_list_recyclerview.layoutManager = LinearLayoutManager(this)
        setIsLoading(false)
    }

    /**
     * Set the list of breeds to the view. Create or update an existing adapter
     */
    override fun setBreeds(breeds: List<DogBreed>?) {
        if (adapter == null) {
            adapter = BreedListAdapter(this, object : BreedListAdapter.ItemClickListener {
                override fun onItemClick(position: Int, viewHolder: BreedViewHolder) {
                    val breed = presenter.getBreed(position)
                    if (breed != null) {
                        viewBreedDetails(breed, viewHolder)
                    }
                }
            })
        }
        adapter!!.setBreeds(breeds)

        breed_list_recyclerview.adapter = adapter
    }

    /**
     * Go to a details activity for a specific breed
     */
    private fun viewBreedDetails(breed: DogBreed, viewHolder: BreedViewHolder) {

    }

    /**
     * Show or hide the loading indicator
     */
    override fun setIsLoading(isLoading: Boolean) {
        breed_list_progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    /**
     * Show a simple error message to the user
     */
    override fun showLoadingError() {
        Toast.makeText(this, R.string.loading_error, Toast.LENGTH_SHORT).show()
    }
}