package hr.santolin.jetpackflowerpower.models.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity(tableName = "climbed_note")
data class ClimbedNote(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "mountain") var mountain: String? = null,
    @ColumnInfo(name = "location") var location: String? = null,
    @ColumnInfo(name = "route_name") var routeName: String? = null,
    @ColumnInfo(name = "route_grade") var routeGrade: String? = null,
    @ColumnInfo(name = "date_climbed_str") var dateClimbedStr: String? = null,
    @ColumnInfo(name = "is_favourite_route") var isFavouriteRoute: Boolean? = false,
):Parcelable {
}

