import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Item(
    val id: Int,
    val userId:Int,
    val name:String,
    val price: Double
)

@Composable
fun Domain(){
    Card() {
        Row {
            Column {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Telur 1.5 KG"
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Rp. 12.000"
                )
            }
            IconButton(onClick = {  }) {

            }
        }
    }
}