package me.androidbox.stockmarket.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import stockmarket.composeapp.generated.resources.Res
import stockmarket.composeapp.generated.resources.criclearrowup

@Composable
fun StockItemView(
    symbol: String,
    currentPrice: Float,
    currentDate: String,
    currency: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier.fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 8.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = symbol,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    color = Color(0xFF2e3642)
                )
                Text(
                    text = currentDate,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    color = Color(0xFF66707F)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.criclearrowup),
                    contentDescription = "price indicator",
                    tint = Color.Unspecified
                )

                Text(
                    text = currentPrice.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    color = Color(0xFF2e3642)
                )

                Text(
                    text = currency,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    color = Color(0xFF2e3642)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewStockItem() {
    MaterialTheme {
        StockItemView(
            symbol = "GOOG",
            currentPrice = 178.54f,
            currentDate = "June 12, 2024",
            currency = "USD"
        )
    }
}