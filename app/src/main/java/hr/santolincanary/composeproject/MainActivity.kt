package hr.santolincanary.composeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import hr.santolin.jetpackflowerpower.models.entities.ClimbedNote
import hr.santolin.jetpackflowerpower.viewmodel.ClimbedNoteViewModel
import hr.santolin.jetpackflowerpower.viewmodel.ClimbedNoteViewModelFactory
import hr.santolincanary.composeproject.models.App
import hr.santolincanary.composeproject.views.theme.*


class MainActivity : ComponentActivity() {
    private val mClimbedNoteViewModel: ClimbedNoteViewModel by viewModels {
        ClimbedNoteViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectTheme {
                val bla: List<ClimbedNote>? by mClimbedNoteViewModel.allNotes.observeAsState()
                NotesScreen(
                    notes = bla,
                    onToggleFavorite = { mClimbedNoteViewModel.onFavouriteChecked(it) },
                )
            }
        }
    }

    @Composable
    private fun NotesScreen(
        notes: List<ClimbedNote>?,
        onToggleFavorite: (Long?) -> Unit,
        modifier: Modifier = Modifier
    ) {


        LazyColumn(
            modifier = modifier
        ) {
            item{Button(onClick = { mClimbedNoteViewModel.populateDatabase() }) { Text("populate database") }}
            if (notes != null && notes?.size != 0) {
                    notes?.let {
                        itemsIndexed(it) { index, item ->
                            PostCardPopular(
                                item,
                                onToggleFavorite,
                                Modifier.padding(start = 16.dp, bottom = 16.dp)
                            )
                        }
                    }
                }

         //   item { NoteListSimpleSection(notes, onToggleFavorite) }
            //  item { NoteListPopularSection(notesBottom, onToggleFavorite) }
        }
    }

    @Composable
    private fun NoteListSimpleSection(
        notes: List<ClimbedNote>?,
        onToggleFavorite: (Long?) -> Unit
    ) {
        if (notes != null && notes?.size != 0) {
            LazyColumn {
                notes?.let {
                    itemsIndexed(it) { index, item ->
                        PostCardPopular(
                            item,
                            onToggleFavorite,
                            Modifier.padding(start = 16.dp, bottom = 16.dp)
                        )
                    }
                }

            }
        }

    }

    //Column {
//                posts?.forEach { post ->
//                    PostCardSimple(
//                        post = post,
//                        isFavorite = post.isFavouriteRoute,
//                        onToggleFavorite = { mClimbedNoteViewModel.onFavouriteChecked(post.id) }
//                    )
//                    PostListDivider()
//                }
//            }
    @Composable
    private fun PostListTopSection(post: ClimbedNote?) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            text = "Top stories for you",
            style = MaterialTheme.typography.subtitle1
        )
        PostCardTop(
            post = post
        )
        PostListDivider()
    }

    @Composable
    private fun NoteListPopularSection(
        posts: List<ClimbedNote>?,
        onToggleFavorite: (Long?) -> Unit
    ) {
        // Column {
        //            Text(
        //                modifier = Modifier.padding(16.dp),
        //                text = "Popular on Jetnews",
        //                style = MaterialTheme.typography.subtitle1
        //            )
        //
        //            LazyRow(modifier = Modifier.padding(end = 16.dp)) {
        //                itemsIndexed(posts!!) { index, post ->
        //                    PostCardPopular(
        //                        post,
        //                        onToggleFavorite,
        //                        Modifier.padding(start = 16.dp, bottom = 16.dp)
        //                    )
        //                }
        //            }
        //            PostListDivider()
        //        }
    }

    @Composable
    private fun PostListDivider() {
        Divider(
            modifier = Modifier.padding(horizontal = 14.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        )
    }

    @Composable
    fun PostCardTop(post: ClimbedNote?, modifier: Modifier = Modifier) {
        val typography = MaterialTheme.typography
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val imageModifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)
            Image(
                painter = painterResource(R.drawable.placeholder),
                contentDescription = null, // decorative
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(16.dp))

            Text(
                text = post?.routeName ?: "Route name",
                style = typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = post?.location ?: "Route location",
                style = typography.subtitle2,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${post?.dateClimbedStr} - date",
                    style = typography.subtitle2
                )
            }
        }
    }

    @Composable
    fun PostCardPopular(
        post: ClimbedNote?,
        onToggleFavorite: (Long?) -> Unit,
        modifier: Modifier = Modifier
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = modifier.size(280.dp, 240.dp)
        ) {
            Column(modifier = Modifier.clickable(onClick = { onToggleFavorite(post?.id ?: 1L) })) {

                Image(
                    painter = painterResource(R.drawable.placeholder),//TODO
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = post?.routeName ?: "Sive jeze",
                        style = MaterialTheme.typography.h6,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = post?.mountain ?: "Biokovo",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2
                    )

                    Text(
                        text = "${post?.dateClimbedStr} - date",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }


}
