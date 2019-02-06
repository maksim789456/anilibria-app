package ru.radiationx.anilibria.navigation

import android.content.Context
import ru.radiationx.anilibria.entity.app.article.ArticleItem
import ru.radiationx.anilibria.entity.app.release.ReleaseItem
import ru.radiationx.anilibria.navigation.BaseAppScreen
import ru.radiationx.anilibria.ui.activities.SettingsActivity
import ru.radiationx.anilibria.ui.activities.auth.AuthActivity
import ru.radiationx.anilibria.ui.activities.main.MainActivity
import ru.radiationx.anilibria.ui.fragments.TabFragment
import ru.radiationx.anilibria.ui.fragments.article.details.ArticleFragment
import ru.radiationx.anilibria.ui.fragments.article.list.ArticlesContainerFragment
import ru.radiationx.anilibria.ui.fragments.auth.AuthFragment
import ru.radiationx.anilibria.ui.fragments.auth.AuthVkFragment
import ru.radiationx.anilibria.ui.fragments.favorites.FavoritesFragment
import ru.radiationx.anilibria.ui.fragments.history.HistoryFragment
import ru.radiationx.anilibria.ui.fragments.other.OtherFragment
import ru.radiationx.anilibria.ui.fragments.page.PageFragment
import ru.radiationx.anilibria.ui.fragments.release.details.ReleaseFragment
import ru.radiationx.anilibria.ui.fragments.release.list.ReleasesFragment
import ru.radiationx.anilibria.ui.fragments.search.SearchFragment
import ru.radiationx.anilibria.ui.fragments.youtube.YoutubeFragment

/**
 * Created by radiationx on 17.11.17.
 */
object Screens {

    class TabScreen(val rootScreen: BaseAppScreen) : BaseAppScreen() {
        override fun getFragment() = TabFragment.newInstance(rootScreen)
    }

    class Auth(val rootScreen: BaseAppScreen? = null) : BaseAppScreen() {
        override fun getActivityIntent(context: Context) = AuthActivity.createIntent(context, rootScreen)
    }

    class AuthMain() : BaseAppScreen() {
        override fun getFragment() = AuthFragment()
    }

    class AuthVk(val url: String) : BaseAppScreen() {
        override fun getFragment() = AuthVkFragment.newInstance(url)
    }

    class Main : BaseAppScreen() {
        override fun getActivityIntent(context: Context) = MainActivity.getIntent(context)
    }

    class Settings : BaseAppScreen() {
        override fun getActivityIntent(context: Context) = SettingsActivity.getIntent(context)
    }

    class Favorites : BaseAppScreen() {
        override fun getFragment() = FavoritesFragment()
    }

    class StaticPage(val pageId: String) : BaseAppScreen() {
        override fun getFragment() = PageFragment.newInstance(pageId)
    }

    class History : BaseAppScreen() {
        override fun getFragment() = HistoryFragment()
    }

    class ArticleDetails(
            val idCode: String? = null,
            val item: ArticleItem? = null
    ) : BaseAppScreen() {
        override fun getFragment() = ArticleFragment.newInstance(idCode, item)
    }

    class ReleaseDetails(
            val id: Int = -1,
            val code: String? = null,
            val item: ReleaseItem? = null
    ) : BaseAppScreen() {
        override fun getFragment() = ReleaseFragment.newInstance(id, code, item)
    }

    class ReleasesSearch(
            val genres: String? = null,
            val years: String? = null
    ) : BaseAppScreen() {
        override fun getFragment() = SearchFragment.newInstance(genres, years)
    }

    class MainReleases : BaseAppScreen() {
        override fun getFragment() = ReleasesFragment()
    }

    class MainArticles : BaseAppScreen() {
        override fun getFragment() = ArticlesContainerFragment()
    }

    class MainYouTube : BaseAppScreen() {
        override fun getFragment() = YoutubeFragment()
    }

    class MainOther : BaseAppScreen() {
        override fun getFragment() = OtherFragment()
    }
}
