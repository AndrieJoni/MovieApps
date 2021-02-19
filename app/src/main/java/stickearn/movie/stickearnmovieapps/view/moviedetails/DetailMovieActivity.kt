package stickearn.movie.stickearnmovieapps.view.moviedetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.home.MovieHomeModel
import com.example.util.PaginationStatus
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import stickearn.movie.stickearnmovieapps.R
import stickearn.movie.stickearnmovieapps.databinding.ActivityDetailMovieBinding
import stickearn.movie.stickearnmovieapps.view.moviedetails.adapter.ReviewsMovieAdapter


@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModels()

    private lateinit var binding: ActivityDetailMovieBinding

    private var reviewsMovieAdapter = ReviewsMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initBinding())
        initView()
        initObserver()
        initEventListener()
    }

    private fun initBinding(): View {

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initView() {

        binding.rvMovieReviews.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.rvMovieReviews.adapter = reviewsMovieAdapter
    }

    private fun initObserver() {

        detailMovieViewModel.showMovieData.observe(this, {
            renderMovieData(it)
        })

        detailMovieViewModel.initializeReviewsMovieLiveData().observe(this, {
            renderMovieReviews(it)
        })

        detailMovieViewModel.reviewsMovieDataSourceFactory?.paginationStatus?.observe(this, {
            checkMovieReviewsPaginationStatus(it)
        })

        detailMovieViewModel.changeFavoriteIconColorEvent.observe(this, {
            renderFavoriteIcon(it)
        })

        detailMovieViewModel.saveFavoriteMovieEvent.observe(this, {
            renderSaveFavoriteSnackBar(it)
        })

        detailMovieViewModel.shareLinkEvent.observe(this, {
            shareMovie(it)
        })
    }

    private fun initEventListener() {

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.ivFavoriteMovie.setOnClickListener {
            favoriteIconClicked()
        }

        binding.ivShareMovie.setOnClickListener {
            shareIconClicked()
        }
    }

    private fun favoriteIconClicked() {
        detailMovieViewModel.favoriteIconClicked()
    }

    private fun shareIconClicked() {
        detailMovieViewModel.shareIconClicked()
    }

    private fun renderMovieData(movieData: MovieHomeModel) {

        binding.cToolbarDetailMovie.title = movieData.title

        binding.tvMovieReleaseDate.text = LocalDate.parse(
            movieData.releaseDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ).format(DateTimeFormatter.ofPattern("LLL dd,yyyy"))

        Picasso
            .get()
            .load(
                String.format(
                    "%s/t/p/w500/%s",
                    com.example.commondata.BuildConfig.BASE_TMDB_IMAGE_URL,
                    movieData.backdropImage
                )
            )
            .fit()
            .into(binding.ivMovieToolbar)

        binding.tvMovieName.text = movieData.title

        binding.tvMovieDescription.text = movieData.overview

    }

    private fun renderMovieReviews(movieReviews: PagedList<MovieReviewModel>) {
        reviewsMovieAdapter.submitList(movieReviews)
    }

    private fun changeFavoriteIcon(drawableId: Int) {
        binding.ivFavoriteMovie.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                drawableId
            )
        )
    }

    private fun renderFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) changeFavoriteIcon(R.drawable.ic_twotone_favorite_24)
        else changeFavoriteIcon(R.drawable.ic_twotone_unfavorite_24)
    }

    private fun showSnackBar(message: String) {

        Snackbar
            .make(
                binding.coordinatorLayout,
                message,
                Snackbar.LENGTH_SHORT
            ).setAnchorView(binding.clMenuDetailMovie)
            .show()
    }

    private fun renderSaveFavoriteSnackBar(isFavorite: Boolean) {
        if (isFavorite) {
            showSnackBar(getString(R.string.marked_favorite_movies))
        } else {
            showSnackBar(getString(R.string.unmarked_favorite_movies))
        }
    }

    private fun shareMovie(message: String) {
        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)

        startActivity(Intent.createChooser(intent, "Share"))
    }

    private fun checkMovieReviewsPaginationStatus(paginationStatus: PaginationStatus) {
        when (paginationStatus) {
            is PaginationStatus.Empty -> binding.tvNoReviewsMovie.isVisible = true
            else -> {
            }
        }
    }

    companion object {
        const val MOVIE_DATA = "MovieData"
    }
}