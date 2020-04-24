package ru.radiationx.anilibria.screen.catalog

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import ru.radiationx.anilibria.common.BaseCardsViewModel
import ru.radiationx.anilibria.common.CardsDataConverter
import ru.radiationx.anilibria.common.LibriaCard
import ru.radiationx.anilibria.screen.SearchScreen
import ru.radiationx.data.entity.app.release.GenreItem
import ru.radiationx.data.entity.app.release.SeasonItem
import ru.radiationx.data.entity.app.search.SearchForm
import ru.radiationx.data.repository.SearchRepository
import ru.terrakok.cicerone.Router
import toothpick.InjectConstructor

@InjectConstructor
class CatalogViewModel(
    private val searchRepository: SearchRepository,
    private val converter: CardsDataConverter,
    private val router: Router,
    private val catalogController: CatalogController
) : BaseCardsViewModel() {

    private var searchForm = SearchForm()

    override val loadOnCreate: Boolean = false

    override fun onColdCreate() {
        super.onColdCreate()

        catalogController.applyFormEvent.lifeSubscribe {
            searchForm = it
            onRefreshClick()
        }
    }

    override fun getLoader(requestPage: Int): Single<List<LibriaCard>> = searchRepository
        .searchReleases(searchForm, requestPage)
        .map { it.data.map { converter.toCard(it) } }

    fun onSearchClick() {
        router.navigateTo(SearchScreen())
    }
}